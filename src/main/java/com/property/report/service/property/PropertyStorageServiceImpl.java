package com.property.report.service.property;

import com.property.report.common.dto.CountryDto;
import com.property.report.common.dto.PropertyDto;
import com.property.report.model.Country;
import com.property.report.model.Property;
import com.property.report.repository.CountryRepository;
import com.property.report.repository.PropertyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PropertyStorageServiceImpl implements PropertyStorageService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private CountryRepository countryRepository;

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
            Property property = new Property(data, data.getOnlinePresence(), country);
            propertyRepository.save(property);
        });

        log.info("---data save done for this page...");
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
