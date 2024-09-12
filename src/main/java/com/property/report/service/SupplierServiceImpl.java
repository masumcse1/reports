package com.property.report.service;

import com.property.report.common.dto.*;
import com.property.report.exception.InvalidCredentialsException;
import com.property.report.model.Country;
import com.property.report.model.Property;
import com.property.report.repository.PropertyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static com.property.report.common.constant.MessageConstant.*;

@Service
@Slf4j
public class SupplierServiceImpl implements SupplierService {

    @Value("${supplier.api.url}")
    String supplierUrl;

    @Autowired
    TokenService tokenService;

    @Autowired
    PropertyRepository propertyRepository;


    @Override
    public List<CountryDto> getAllCountry(String token) {

        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        headers.set("Authorization", "Bearer " + token);

        for (int j = 0; j < 3; j++) {
            try {
                ResponseEntity<SupplierResultDto<List<CountryDto>>> allDataResponse = template.exchange(
                        supplierUrl + "countries",
                        HttpMethod.GET, entity, new ParameterizedTypeReference<SupplierResultDto<List<CountryDto>>>() {
                        });

                if (allDataResponse.getStatusCodeValue() == HttpStatus.OK.value()) {
                    return Objects.requireNonNull(allDataResponse.getBody().getResult());
                } else
                    Thread.sleep(1000 * j);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
        throw new InvalidCredentialsException(INVALID_CREDENTIALS);
    }

    @Override
    public List<Property> getPaginatedDataByCountryId(String token, Country country) {

        int page = 0;
        //fetch data from supplier
        SupplierContentPaginated supplierContentPaginated = getDataByCountryId(page, country.getId());
        //get total pages in supplier API
        int totalPages = supplierContentPaginated.getTotalPages();
        log.info("Total Pages in supplier API:" + totalPages + " with total elements :" + supplierContentPaginated.getTotalElements());

        List<Property> propertyListByCountry = propertyRepository.findByCountry(country);

        if (!propertyListByCountry.isEmpty() && supplierContentPaginated.getTotalElements() <= propertyListByCountry.size()) {
            return new ArrayList<>();
        }
        //get required data a from supplier API
        List<PropertyDto> propertyDtos = supplierContentPaginated.getContent();

        for (int i = 1; i < totalPages; i++) {
            List<PropertyDto> content = getDataByCountryId(i, country.getId()).getContent();
            propertyDtos.addAll(content);
        }

        List<Property> propertyList = new ArrayList<>();

        Set<Integer> propertySet = new HashSet<>();

        for (PropertyDto propertyDto : propertyDtos) {

            Property property = propertyRepository.findById(propertyDto.getId()).orElse(null);

            if (Objects.isNull(property)) {
                FreeGoogleBooking freeGoogleBooking = getOnlinePresenceById(propertyDto.getId());

                if (!propertySet.contains(propertyDto.getId())) {
                    log.info("Property ID added={}", propertyDto.getId());
                    propertyList.add(new Property(propertyDto, freeGoogleBooking, country));
                    propertySet.add(propertyDto.getId());
                }

                if (propertyList.size() > 100000) {
                    break;
                }
            }
        }
        return propertyList;
    }

    @Override
    public SupplierContentPaginated getDataByCountryId(Integer i, Integer countryId) {

        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        for (int j = 0; j < 3; j++) {
            headers.set("Authorization", "Bearer " + ACCESS_TOKEN);
            //get data from supplier API
            try {
                ResponseEntity<SupplierResultDto<SupplierContentPaginated>> allDataResponse = template.exchange(
                        supplierUrl + "properties/paging?233=" + countryId + "&page=" + i + "&size=100&sortBy=id&sortDesc=false",
                        HttpMethod.GET, entity, new ParameterizedTypeReference<SupplierResultDto<SupplierContentPaginated>>() {
                        });
                if (allDataResponse.getStatusCodeValue() == HttpStatus.OK.value()) {
                    return Objects.requireNonNull(allDataResponse.getBody().getResult());
                } else
                    Thread.sleep(1000 * j);

            } catch (Exception e) {
                log.error(e.getMessage());
                if (e.getMessage().contains("401")) {
                    ACCESS_TOKEN = tokenService.getTokenFromSuppliers().getAccessToken();
                    log.info(ACCESS_TOKEN);
                }
            }
        }
        log.error(DATA_NOT_FOUND);
        return null;
    }

    @Override
    public FreeGoogleBooking getOnlinePresenceById(Integer id) {

        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        headers.set("Authorization", "Bearer " + ACCESS_TOKEN);

        //get data from supplier API
        try {
            ResponseEntity<SupplierResultDto<FreeGoogleBooking>> allDataResponse = template.exchange(
                    supplierUrl + "online-presence/" + id,
                    HttpMethod.GET, entity, new ParameterizedTypeReference<SupplierResultDto<FreeGoogleBooking>>() {
                    });
            if (allDataResponse.getStatusCodeValue() == HttpStatus.OK.value()) {
                return Objects.requireNonNull(allDataResponse.getBody().getResult());

            } else
                return null;

        } catch (Exception e) {
            // log.error(e.getMessage());
            if (e.getMessage().contains("401")) {
                ACCESS_TOKEN = tokenService.getTokenFromSuppliers().getAccessToken();
                log.info(ACCESS_TOKEN);
            }
        }

        return null;
    }

    @Override
    public PropertyDto getDataByPropertyId(Integer id, String token) {

        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        headers.set("Authorization", "Bearer " + token);

        for (int j = 0; j < 3; j++) {
            //get data from supplier API
            try {
                ResponseEntity<SupplierResultDto<PropertyDto>> allDataResponse = template.exchange(
                        supplierUrl + "properties/" + id,
                        HttpMethod.GET, entity, new ParameterizedTypeReference<SupplierResultDto<PropertyDto>>() {
                        });
                if (allDataResponse.getStatusCodeValue() == HttpStatus.OK.value())
                    return Objects.requireNonNull(allDataResponse.getBody().getResult());
                else
                    Thread.sleep(1000 * j);

            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
        log.error(DATA_NOT_FOUND);
        return null;
    }

    @Override
    public void getGermanyData(String token, Country country) {

        int page = 0;
        //fetch data from supplier
        SupplierContentPaginated supplierContentPaginated = getDataByCountryId(page, country.getId());
        //get total pages in supplier API
        int totalPages = supplierContentPaginated.getTotalPages();
        log.info("Total Pages in supplier API:" + totalPages + " with total elements :" + supplierContentPaginated.getTotalElements());

        List<Property> propertyListByCountry = propertyRepository.findByCountry(country);

        if (!propertyListByCountry.isEmpty() && supplierContentPaginated.getTotalElements() <= propertyListByCountry.size()) {
            return;
        }

        for (int i = 25000; i < totalPages; i++) {

            List<Property> propertyList = new ArrayList<>();

            Set<Integer> propertySet = new HashSet<>();

            List<PropertyDto> content = Objects.nonNull(getDataByCountryId(i, country.getId()).getContent()) ?
                    getDataByCountryId(i, country.getId()).getContent() : new ArrayList<>();

            for (PropertyDto propertyDto : content) {

                Property property = propertyRepository.findById(propertyDto.getId()).orElse(null);

                if (Objects.isNull(property)) {
                    FreeGoogleBooking freeGoogleBooking = getOnlinePresenceById(propertyDto.getId());

                    if (!propertySet.contains(propertyDto.getId())) {
                        log.info("Property ID added={}", propertyDto.getId());
                        propertyList.add(new Property(propertyDto, freeGoogleBooking, country));
                        propertySet.add(propertyDto.getId());
                    }

                }
            }
            List<Property> propertyListSaved = propertyRepository.saveAll(propertyList);
            log.info("Total property ={} saved for country : ={}", propertyListSaved.size(), country.getName());
        }

    }

    @Override
    public List<MessageRdo> getScheduledMessageData(String token) {

        int page = 0;
        //fetch data from supplier
        MessageContentSupplier messageContentSupplier = getSentMessage(page, token);
        //get total pages in supplier API
        int totalPages = messageContentSupplier.getTotalPages();
        log.info("Total Pages in supplier API:" + totalPages);
        //get required data afrom supplier API
        List<MessageRdo> messageRdos = messageContentSupplier.getContent();
        for (int i = 1; i < totalPages; i++) {
            List<MessageRdo> content = getSentMessage(i, token).getContent();
            messageRdos.addAll(content);
        }
        return messageRdos;
    }

    @Override
    public UploadMediaDto uploadMediaFile(Integer propertyId, ByteArrayResource fileAsResource) {

        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", fileAsResource);
        // Configure a multipart message converter
        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(body, headers);

        ACCESS_TOKEN = tokenService.getTokenFromSuppliers().getAccessToken();

        headers.set("Authorization", "Bearer " + ACCESS_TOKEN);
        //get data from supplier API
        try {
            ResponseEntity<SupplierResultDto<UploadMediaDto>> allDataResponse = template.exchange(
                    supplierUrl + "media/upload-new?propertyId=" + propertyId + "&mediaTypeId=4&isMain=false&isLogo=false&sortOrder=3&sortOrderMain=3",
                    HttpMethod.POST, entity, new ParameterizedTypeReference<SupplierResultDto<UploadMediaDto>>() {
                    });
            if (allDataResponse.getStatusCodeValue() == 201) {
                return Objects.requireNonNull(allDataResponse.getBody().getResult());
            } else
                return null;

        } catch (Exception e) {
            log.error(e.getMessage());
        }

        log.error(DATA_NOT_FOUND);
        return null;
    }

    public MessageContentSupplier getSentMessage(Integer i, String token) {

        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        headers.set("Authorization", "Bearer " + token);

        for (int j = 0; j < 3; j++) {
            //get data from supplier API
            try {
                ResponseEntity<SupplierResultDto<MessageContentSupplier>> allDataResponse = template.exchange(
                        supplierUrl + "message/sent/paging?page=" + i + "&size=100&sortBy=name&sortDesc=true",
                        HttpMethod.GET, entity, new ParameterizedTypeReference<SupplierResultDto<MessageContentSupplier>>() {
                        });
                if (allDataResponse.getStatusCodeValue() == HttpStatus.OK.value()) {
                    return Objects.requireNonNull(allDataResponse.getBody().getResult());
                } else
                    Thread.sleep(1000 * j);

            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
        log.error(DATA_NOT_FOUND);
        return null;
    }

    public static String getPropertyEmail(PropertyDto propertyEntity) {
        //fetch primary email if possible, otherwise return any email or null
        String emailAddress = null;
        if (!CollectionUtils.isEmpty(propertyEntity.getEmails())) {
            List<EmailEntity> filteredPrimaryEmail = propertyEntity.getEmails().stream().filter(p -> p.getEmailType().getCode().equals("primary")).toList();
            if (!CollectionUtils.isEmpty(filteredPrimaryEmail)) {
                emailAddress = filteredPrimaryEmail.get(0).getEmail();
            } else {
                emailAddress = propertyEntity.getEmails().get(0).getEmail();
            }
        }
        return emailAddress;
    }
}
