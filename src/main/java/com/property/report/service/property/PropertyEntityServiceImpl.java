package com.property.report.service.property;

import com.property.report.model.OnlinePresenceEntity;
import com.property.report.model.PropertyEntity;
import com.property.report.repository.property.PropertyEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PropertyEntityServiceImpl implements PropertyEntityService {

    @Autowired
    private PropertyEntityRepository propertyRepository;

    @Async
    public void save(List<PropertyEntity> properties) {
        log.info("---start data save done for page...");
        for (PropertyEntity property : properties) {
            OnlinePresenceEntity onlinePresence = property.getOnlinePresence();

            if (onlinePresence.getPropertyId() == null) {
                property.setOnlinePresence(null);
            } else if (onlinePresence.getBrandOfBookingEngine().getId() == null) {
                onlinePresence.setBrandOfBookingEngine(null);
            }

            property.getAddresses().stream()
                    .filter(a -> a.getCountry().getId() == null)
                    .forEach(a -> a.setCountry(null));

            property.getEmails().stream()
                    .filter(a -> a.getEmailType().getId() == null)
                    .forEach(a -> a.setEmailType(null));

            propertyRepository.save(property);
        }

        log.info("--end-data save done for page...");
    }

}