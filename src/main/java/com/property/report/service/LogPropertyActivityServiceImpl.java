package com.property.report.service;

import com.property.report.common.dto.LogPropertyActivityQdo;
import com.property.report.model.LogPropertyActivity;
import com.property.report.model.Property;
import com.property.report.repository.LogPropertyActivityRepository;
import com.property.report.repository.PropertyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class LogPropertyActivityServiceImpl implements LogPropertyActivityService{


        @Autowired
        LogPropertyActivityRepository logPropertyActivityRepository;

        @Autowired
        PropertyRepository propertyRepository;

        @Override
        public void logPropertyActivity(LogPropertyActivityQdo logPropertyActivityQdo){

            log.info("Call for property : "+logPropertyActivityQdo.getProperty());

            Property property = propertyRepository.findById(logPropertyActivityQdo.getProperty()).orElse(null);

            if(Objects.nonNull(property)) {
                log.info("Call for property inside: "+logPropertyActivityQdo.getProperty());
                logPropertyActivityRepository.save(new LogPropertyActivity(logPropertyActivityQdo, property));
                property.setGoogleParser(true);
                propertyRepository.save(property);
            }

        }

}
