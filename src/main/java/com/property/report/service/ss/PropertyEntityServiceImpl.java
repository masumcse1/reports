package com.property.report.service.ss;

import com.property.report.model.PropertyEntity;
import com.property.report.repository.ss.PropertyEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyEntityServiceImpl implements PropertyEntityService {

    @Autowired
    private PropertyEntityRepository propertyRepository;

    public void save(List<PropertyEntity> properties) {
        for (PropertyEntity property : properties) {
            if (property.getOnlinePresence().getPropertyId() == null) {
                property.setOnlinePresence(null);
            }

            property.getAddresses().stream()
                    .filter(a -> a.getCountry().getId() == null)
                    .forEach(a -> a.setCountry(null));

            System.out.println(property);
            propertyRepository.save(property);
        }
    }

}
