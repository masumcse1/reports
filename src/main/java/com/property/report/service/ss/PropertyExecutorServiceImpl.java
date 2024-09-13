package com.property.report.service.ss;

import com.property.report.model.PropertyEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PropertyExecutorServiceImpl implements PropertyExecutorService {

    @Autowired
    private PropertyFetchService propertyFetchService;

    @Autowired
    private PropertyEntityService propertyEntityService;

    @Value("${job.schedule.page-size}")
    private Integer size;

    @Override
    public void execute() {
        try {
            Pageable pageable = Pageable.ofSize(size);
            Page<PropertyEntity> properties;

            do {
                properties = propertyFetchService.getProperties(pageable);
                propertyEntityService.save(properties.getContent());
                pageable = pageable.next();
            } while (pageable.getPageNumber() < properties.getTotalPages());
        } catch (Exception ex) {
            log.error("Data unable to read", ex);
        }
    }

}
