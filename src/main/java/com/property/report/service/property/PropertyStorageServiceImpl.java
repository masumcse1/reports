package com.property.report.service.property;

import com.property.report.common.dto.CountryDto;
import com.property.report.common.dto.PropertyDto;
import com.property.report.model.Country;
import com.property.report.model.Property;
import com.property.report.repository.PropertyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PropertyStorageServiceImpl implements PropertyStorageService {

    @Autowired
    private PropertyRepository propertyRepository;

    public void save(List<PropertyDto> properties) {
        log.info("---start data save done for page...");

        properties.forEach(data -> {
            Country country = null;

            if (!data.getAddresses().isEmpty()) {
                CountryDto countryDto = data.getAddresses().get(0).getCountry();
                country = new Country(countryDto);
            }

            Property property = new Property(data, data.getOnlinePresence(), country);
            propertyRepository.save(property);
        });

        log.info("--end-data save done for page...");
    }

}
