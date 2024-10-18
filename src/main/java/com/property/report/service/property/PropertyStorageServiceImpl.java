package com.property.report.service.property;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.property.report.common.dto.AccessTokenSupplier;
import com.property.report.common.dto.CountryDto;
import com.property.report.common.dto.PropertyDto;
import com.property.report.common.dto.PropertyIdentifier;
import com.property.report.model.Country;
import com.property.report.model.Property;
import com.property.report.repository.CountryRepository;
import com.property.report.repository.PropertyRepository;
import com.property.report.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class PropertyStorageServiceImpl implements PropertyStorageService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private TokenService tokenService;

    @Value("${supplier.api.url}")
    private String supplierUrl;

    public void save(List<PropertyDto> properties) {

        properties.forEach(data -> {
            Country country = null;

            if (!data.getAddresses().isEmpty()) {
                CountryDto countryDto = data.getAddresses().get(0).getCountry();

                if (countryDto.getId() != null) {
                    country = new Country(countryDto);
                }
            }
            if (country == null){
                country = new Country(handleCountryNullValue());
            }

            Result result = getPropertyIdentifierValues(data);

//            Property property = new Property(data, data.getOnlinePresence(), country);
            Property property = new Property(data, data.getOnlinePresence(), country, result.bookingDotComId(), result.bookingDotComUrl(), result.eHotelId());
            propertyRepository.save(property);
        });

        log.info("---data save done for this page...");
    }

    private Result getPropertyIdentifierValues(PropertyDto data) {
        AccessTokenSupplier tokenFromSuppliers = tokenService.getTokenFromSuppliers();
        String token = tokenFromSuppliers.getAccessToken();

        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        String apiUrl = supplierUrl
                + "properties/identifier/" + data.getId();

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
        Result result = new Result(bookingDotComId, bookingDotComUrl, eHotelId);
        return result;
    }

    private record Result(String bookingDotComId, String bookingDotComUrl, String eHotelId) {
    }

    public CountryDto handleCountryNullValue(){

        Optional<Country> countryNull = countryRepository.findById(260); // handle country null  id  = 260
        Country country=countryNull.get();

        CountryDto countryDto = new CountryDto();
        countryDto.setId(country.getId());
        countryDto.setName(country.getName());
        countryDto.setFullName(country.getFullName());
        countryDto.setCode(country.getCode());
        countryDto.setCodeA3(country.getCodeA3());
        countryDto.setCodeNumeric(country.getCodeNumeric());
        return countryDto;
    }

}
