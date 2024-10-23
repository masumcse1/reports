package com.property.report.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.property.report.common.dto.*;
import com.property.report.exception.DataNotFoundException;
import com.property.report.model.Country;
import com.property.report.model.Property;
import com.property.report.repository.CountryRepository;
import com.property.report.repository.PropertyRepository;
import com.property.report.service.property.PropertyStorageServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.property.report.common.constant.EmailBodyConverter.convertEmailBody;
import static com.property.report.common.constant.HeaderConstant.*;
import static com.property.report.common.constant.MessageConstant.ACCESS_TOKEN;
import static com.property.report.common.util.DateTimeUtil.convertTimeStampToString;

@Service
@Slf4j
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    PropertyRepository propertyRepository;

    @Autowired
    SupplierService supplierService;

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    EwsService ewsService;

    @Value("${updatePropertyByCountry.cron.flag}")
    boolean enabled;

    @Autowired
    private TokenService tokenService;

    @Value("${supplier.api.url}")
    private String supplierUrl;

    @Override
    @Transactional
    public Integer addPropertyByCountry(String code) {

        List<Country> countryList = countryRepository.findAll();

        if (Objects.nonNull(code)) {
            Country country = countryRepository.findByCode(code);
            savePropertyByCountryId(country);
        } else {
            for (Country country : countryList) {
                savePropertyByCountryId(country);
            }
        }

        return 100;
    }

    private void savePropertyByCountryId(Country country) {

        if (country.getCode().equalsIgnoreCase("DE")) {
            log.info("Save property for germany");
            supplierService.getGermanyData(ACCESS_TOKEN, country);
            return;
        }
        List<Property> propertyList = supplierService.getPaginatedDataByCountryId(ACCESS_TOKEN, country);

        List<Property> propertyListSaved = propertyRepository.saveAll(propertyList);

        log.info("Total property saved for country ={} is : ={}", country.getName(), propertyListSaved.size());
    }

    @Override
    public void getCsvFile(Writer writer, String code) {

        Country country = countryRepository.findByCode(code);

        if (Objects.isNull(country))
            return;

        int batchSize = 10000;
        int page = 0;

        try (CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(HEADERS))) {

            while (true) {
                Page<Property> propertyPage = propertyRepository.findByCountry(country, PageRequest.of(page, batchSize, Sort.by("id")));

                if (propertyPage.isEmpty()) {
                    break;
                }

                List<Property> propertyList = propertyPage.getContent();

                int i = 1;
                for (Property campaignData : propertyList) {

                    String[] allDetails = convertDetailsToString(campaignData, i);
                    printer.printRecord(allDetails);
                    i++;
                }
                page++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void getWorldCsvFile(Writer writer) {

        int batchSize = 10000;
        int page = 0;

        HashMap<Integer, PropertyCount> hashMap = new HashMap<>();

        while (true) {
            Page<Property> propertyPage = propertyRepository.findAll(PageRequest.of(page, batchSize, Sort.by("id")));
            List<Property> propertyList = propertyPage.getContent();

            if (propertyList.isEmpty()) {
                break;
            }

            for (Property property : propertyList) {

                if (hashMap.containsKey(property.getCountry().getId())) {
                    PropertyCount propertyCount = hashMap.get(property.getCountry().getId());
                    setPropertyCount(property, propertyCount);
                } else {
                    PropertyCount propertyCount = new PropertyCount();
                    propertyCount.setCountry(property.getCountry().getName());
                    propertyCount.setCountryCode(property.getCountry().getCode());
                    setPropertyCount(property, propertyCount);
                    hashMap.put(property.getCountry().getId(), propertyCount);
                }

            }
            log.info("Paze size : " + page);
            page++;
        }

        try (CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(WORLD_HEADERS))) {
            int i = 1;
            PropertyCount totalCount = new PropertyCount("Total sum of properties");

            for (Integer key : hashMap.keySet()) {
                setPropertyTotalCount(hashMap.get(key), totalCount);
                String[] allDetails = convertWorldDetailsToString(hashMap.get(key), i);
                printer.printRecord(allDetails);
                i++;
            }

            totalCount.setNetProperty(totalCount.getPropertyId() - totalCount.getIsDeleted() - totalCount.getForTesting());

            String[] allDetails = convertWorldDetailsToString(totalCount, i);
            printer.printRecord(allDetails);

            PropertyCount percentageByProperty = new PropertyCount(totalCount);
            String[] percantageDetails = convertWorldDetailsToStringPercantage(percentageByProperty, i + 1, "%");

            printer.printRecord(percantageDetails);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ListResultRdo<Integer> getPropertyByCountryAndEmail(Integer page, Integer size, String code) {

        Country country = countryRepository.findByCode(code);

        return propertyRepository.getPropertyByCountryAndEmail(page, size, country);

    }

    @Override
    public ListResultRdo<Integer> getPropertyByCountryAndGoogle(Integer page, Integer size, String code) {

        Country country = countryRepository.findByCode(code);

        return propertyRepository.getPropertyByCountryAndGoogle(page, size, country);

    }

    @Transactional
    @Override
    public void updateProperty(Integer propertyId, String token) {

        PropertyDto dataByPropertyId = supplierService.getDataByPropertyId(propertyId, token);

        if (Objects.isNull(dataByPropertyId)) {
            log.error("No property data found for property ID: {}", propertyId);
            throw new DataNotFoundException("No property data found for property ID: " + propertyId);
        }

        FreeGoogleBooking freeGoogleBooking = supplierService.getOnlinePresenceById(propertyId);

        Property byPropertyId = propertyRepository.findByPropertyId(propertyId);

        if (Objects.isNull(byPropertyId)) {
            if (Objects.nonNull(dataByPropertyId.getAddresses()) && !dataByPropertyId.getAddresses().isEmpty()) {
                Country country = countryRepository.findByCode(dataByPropertyId.getAddresses().get(0).getCountry().getCode());
                //propertyRepository.save(new Property(dataByPropertyId, freeGoogleBooking, country));
                Result result = getPropertyIdentifierValues(dataByPropertyId);
                propertyRepository.save(new Property(dataByPropertyId, freeGoogleBooking, country, result.bookingDotComId(), result.bookingDotComUrl(), result.eHotelId()));
                log.info("Data added for property ID :={}", propertyId);
            }
        } else {
//            propertyRepository.save(new Property(dataByPropertyId, freeGoogleBooking, byPropertyId.getCountry()));
            Result result = getPropertyIdentifierValues(dataByPropertyId);
            propertyRepository.save(new Property(dataByPropertyId, freeGoogleBooking, byPropertyId.getCountry(), result.bookingDotComId(), result.bookingDotComUrl(), result.eHotelId()));
            log.info("Data updated for property ID :={}", propertyId);
        }

    }

    private PropertyServiceImpl.Result getPropertyIdentifierValues(PropertyDto propertyDto) {
        AccessTokenSupplier tokenFromSuppliers = tokenService.getTokenFromSuppliers();
        String token = tokenFromSuppliers.getAccessToken();

        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        String apiUrl = supplierUrl
                + "properties/identifier/" + propertyDto.getId();

        String bookingDotComId = "";
        String bookingDotComUrl = "";
        String eHotelId = "";

        try {
            ResponseEntity<?> response = template.exchange (
                    apiUrl,
                    HttpMethod.GET,
                    entity,
                    Map.class);

            // Ensure the response body is not null
            if (response.getBody() != null) {
                Map<String, Object> responseBody = (Map<String, Object>) response.getBody();

                // Validate if 'result' exists and is of Map type
                List<PropertyIdentifier> propertyIdentifierList = new ObjectMapper()
                        .convertValue(responseBody.get("result"), new TypeReference<List<PropertyIdentifier>>() {});

                for (int i = 0; i < propertyIdentifierList.size(); i++) {
                    PropertyIdentifier propertyIdentifier = propertyIdentifierList.get(i);

                    if("Booking.com".equals(propertyIdentifier.getSource().getName())) {
                        bookingDotComId = propertyIdentifier.getIdentifier();
                        bookingDotComUrl = propertyIdentifier.getUrl();
                    } else if("eHotel".equals(propertyIdentifier.getSource().getName())) {
                        eHotelId = propertyIdentifier.getIdentifier();
                    }

                }
            } else {
                throw new IllegalStateException("API response body is null.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        PropertyServiceImpl.Result result = new PropertyServiceImpl.Result(bookingDotComId, bookingDotComUrl, eHotelId);
        return result;
    }

    private record Result(String bookingDotComId, String bookingDotComUrl, String eHotelId) {
    }

    @Override
    public void deleteProperty(Integer propertyId, String token) {

        PropertyDto dataByPropertyId = supplierService.getDataByPropertyId(propertyId, token);

        if (Objects.isNull(dataByPropertyId)) {
            log.error("No property data found for property ID: {}", propertyId);

            Property byPropertyId = propertyRepository.findByPropertyId(propertyId);

            if (!Objects.isNull(propertyId)) {
                propertyRepository.delete(byPropertyId);
                log.info("Data deleted for property ID :={}", propertyId);
            }
        }

    }

    @Scheduled(cron = "0 0 10 * * *")
    public void updatePropertyByCountry() {
        if (enabled) {
            List<Country> countryList = countryRepository.findAll();
            for (Country country : countryList) {
                log.info("Cron started for country :" + country.getName());
                savePropertyByCountryId(country);
            }

        } else {
            log.info("updatePropertyByCountry flag is disabled from application configuration");
        }
    }

    @Scheduled(cron = "0 0 6 * * *")
    public void sendWorldCsvByEmail() {

        log.info("Cron started");

        int batchSize = 10000;
        int page = 0;

        HashMap<Integer, PropertyCount> hashMap = new HashMap<>();

        while (true) {
            Page<Property> propertyPage = propertyRepository.findAll(PageRequest.of(page, batchSize, Sort.by("id")));
            List<Property> propertyList = propertyPage.getContent();

            if (propertyList.isEmpty()) {
                break;
            }

            for (Property property : propertyList) {
                if (hashMap.containsKey(property.getCountry().getId())) {
                    PropertyCount propertyCount = hashMap.get(property.getCountry().getId());
                    setPropertyCount(property, propertyCount);
                } else {
                    PropertyCount propertyCount = new PropertyCount();
                    propertyCount.setCountry(property.getCountry().getName());
                    propertyCount.setCountryCode(property.getCountry().getCode());
                    setPropertyCount(property, propertyCount);
                    hashMap.put(property.getCountry().getId(), propertyCount);
                }
            }

            page++;
        }

        try (StringWriter writer = new StringWriter();
             CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(WORLD_HEADERS))) {

            int i = 1;

            PropertyCount totalCount = new PropertyCount("Total sum of properties");

            for (Integer key : hashMap.keySet()) {
                setPropertyTotalCount(hashMap.get(key), totalCount);
                String[] allDetails = convertWorldDetailsToString(hashMap.get(key), i);
                printer.printRecord(allDetails);
                i++;
            }

            totalCount.setNetProperty(totalCount.getPropertyId() - totalCount.getIsDeleted() - totalCount.getForTesting());

            totalCount.setName(totalCount.getName() - totalCount.getIsDeleted() - totalCount.getForTesting());

            String[] allDetails = convertWorldDetailsToString(totalCount, i);
            printer.printRecord(allDetails);

            PropertyCount percentageByProperty = new PropertyCount(totalCount);
            String[] percantageDetails = convertWorldDetailsToStringPercantage(percentageByProperty, i + 1, "%");

            printer.printRecord(percantageDetails);

            byte[] fileContent = writer.toString().getBytes(StandardCharsets.UTF_8);

            ByteArrayResource fileAsResource = new ByteArrayResource(fileContent) {
                @Override
                public String getFilename() {
                    return convertTimeStampToString() + "_world.csv";
                }

            };

            UploadMediaDto uploadMediaDto = supplierService.uploadMediaFile(1870115, fileAsResource);

            ewsService.sendEmailByEws(new EwsDto(convertEmailBody(totalCount, percentageByProperty), uploadMediaDto.getUrl()));

            log.info("email sent successfully");

        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }

    }

    @Override
    public void updateSentMessageProperty(String token) {

        List<MessageRdo> scheduledMessageData = supplierService.getScheduledMessageData(token);

        for (MessageRdo messageRdo : scheduledMessageData) {
            //find property by id
            Property property = propertyRepository.findById(messageRdo.getPropertyId()).orElse(null);

            if (Objects.isNull(property)) {
                PropertyDto dataByPropertyId = supplierService.getDataByPropertyId(messageRdo.getPropertyId(), token);

                FreeGoogleBooking freeGoogleBooking = supplierService.getOnlinePresenceById(messageRdo.getPropertyId());

                if (Objects.nonNull(dataByPropertyId.getAddresses()) && !dataByPropertyId.getAddresses().isEmpty() &&
                        Objects.nonNull(dataByPropertyId.getAddresses().get(0).getCountry())) {

                    Country country = countryRepository.findByCode(dataByPropertyId.getAddresses().get(0).getCountry().getCode());
                    property = propertyRepository.save(new Property(dataByPropertyId, freeGoogleBooking, country));
                    log.info("Data added for property ID :={}", messageRdo.getPropertyId());

                }
            }
            // set sent message details
            if (Objects.nonNull(property)) {
                property.setSentAt(messageRdo.getSentAt());
                property.setTopic(messageRdo.getTopic());
                propertyRepository.save(property);
                log.info("Sent message data saved for property ID :={}", messageRdo.getPropertyId());
            }
        }
    }

    private void setPropertyCount(Property property, PropertyCount propertyCount) {
        if (Objects.nonNull(property.getPropertyId())) {
            propertyCount.setPropertyId(propertyCount.getPropertyId() + 1);
        }
        if (Objects.nonNull(property.getEmail())) {
            propertyCount.setEmail(propertyCount.getEmail() + 1);
        }
        if (Objects.nonNull(property.getGoogleBusinessID())) {
            propertyCount.setGoogleBusinessID(propertyCount.getGoogleBusinessID() + 1);
        }
        if (Objects.nonNull(property.getScreenshotOfBookingEngineUrl())) {
            propertyCount.setScreenshotOfBookingEngineUrl(propertyCount.getScreenshotOfBookingEngineUrl() + 1);
        }
        if (Objects.nonNull(property.getBrandOfBookingEngineId())) {
            propertyCount.setBrandOfBookingEngineId(propertyCount.getBrandOfBookingEngineId() + 1);
        }
        if (Objects.nonNull(property.getEHotelId())) {
            propertyCount.setEHotelId(propertyCount.getEHotelId() + 1);
        }
        if (Objects.nonNull(property.getName())) {
            propertyCount.setName(propertyCount.getName() + 1);
        }
        if (Objects.nonNull(property.getGoogleAddress())) {
            propertyCount.setGoogleAddress(propertyCount.getGoogleAddress() + 1);
        }
        if (Objects.nonNull(property.getGooglePhoneNumber())) {
            propertyCount.setGooglePhoneNumber(propertyCount.getGooglePhoneNumber() + 1);
        }
        if (Objects.nonNull(property.getGoogleCategory())) {
            propertyCount.setGoogleCategory(propertyCount.getGoogleCategory() + 1);
        }
        if (Objects.nonNull(property.getUsedBookingEngine())) {
            propertyCount.setUsedBookingEngine(propertyCount.getUsedBookingEngine() + 1);
        }
        if (Objects.nonNull(property.getGoogleRating())) {
            propertyCount.setGoogleRating(propertyCount.getGoogleRating() + 1);
        }
        if (Objects.nonNull(property.getCmsUsedInWebsite())) {
            propertyCount.setCmsUsedInWebsite(propertyCount.getCmsUsedInWebsite() + 1);
        }
        if (Objects.nonNull(property.getEmailAddressUnsubscribe()) && property.getEmailAddressUnsubscribe()) {
            propertyCount.setEmailAddressUnsubscribe(propertyCount.getEmailAddressUnsubscribe() + 1);
        }
        if (Objects.nonNull(property.getFreeGoogleBookingLinks()) && !property.getFreeGoogleBookingLinks()) {
            propertyCount.setFreeGoogleBookingLinks(propertyCount.getFreeGoogleBookingLinks() + 1);
        }
        if (Objects.nonNull(property.getTopic())) {
            propertyCount.setTopic(propertyCount.getTopic() + 1);
        }
        if (Objects.nonNull(property.getSentAt())) {
            propertyCount.setSentAt(propertyCount.getSentAt() + 1);
        }
        if (Objects.nonNull(property.getWebsiteURL())) {
            propertyCount.setWebsiteURL(propertyCount.getWebsiteURL() + 1);
        }
        if (Objects.nonNull(property.getIsDeleted()) && property.getIsDeleted()) {
            propertyCount.setIsDeleted(propertyCount.getIsDeleted() + 1);
        }
        if (Objects.nonNull(property.getForTesting()) && property.getForTesting()) {
            propertyCount.setForTesting(propertyCount.getForTesting() + 1);
        }
    }

    private void setPropertyTotalCount(PropertyCount propertyCount, PropertyCount totalCount) {
        totalCount.setPropertyId(propertyCount.getPropertyId() + totalCount.getPropertyId());
        totalCount.setEmail(propertyCount.getEmail() + totalCount.getEmail());
        totalCount.setGoogleBusinessID(propertyCount.getGoogleBusinessID() + totalCount.getGoogleBusinessID());
        totalCount.setScreenshotOfBookingEngineUrl(propertyCount.getScreenshotOfBookingEngineUrl() + totalCount.getScreenshotOfBookingEngineUrl());
        totalCount.setBrandOfBookingEngineId(propertyCount.getBrandOfBookingEngineId() + totalCount.getBrandOfBookingEngineId());
        totalCount.setEHotelId(propertyCount.getEHotelId() + totalCount.getEHotelId());
        totalCount.setName(propertyCount.getName() + totalCount.getName());
        totalCount.setGoogleAddress(propertyCount.getGoogleAddress() + totalCount.getGoogleAddress());
        totalCount.setGooglePhoneNumber(propertyCount.getGooglePhoneNumber() + totalCount.getGooglePhoneNumber());
        totalCount.setGoogleCategory(propertyCount.getGoogleCategory() + totalCount.getGoogleCategory());
        totalCount.setUsedBookingEngine(propertyCount.getUsedBookingEngine() + totalCount.getUsedBookingEngine());
        totalCount.setGoogleRating(propertyCount.getGoogleRating() + totalCount.getGoogleRating());
        totalCount.setCmsUsedInWebsite(propertyCount.getCmsUsedInWebsite() + totalCount.getCmsUsedInWebsite());
        totalCount.setEmailAddressUnsubscribe(propertyCount.getEmailAddressUnsubscribe() + totalCount.getEmailAddressUnsubscribe());
        totalCount.setFreeGoogleBookingLinks(propertyCount.getFreeGoogleBookingLinks() + totalCount.getFreeGoogleBookingLinks());
        totalCount.setTopic(propertyCount.getTopic() + totalCount.getTopic());
        totalCount.setSentAt(propertyCount.getSentAt() + totalCount.getSentAt());
        totalCount.setWebsiteURL(propertyCount.getWebsiteURL() + totalCount.getWebsiteURL());
        totalCount.setForTesting(propertyCount.getForTesting() + totalCount.getForTesting());
        totalCount.setIsDeleted(propertyCount.getIsDeleted() + totalCount.getIsDeleted());
    }

}
