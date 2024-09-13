package com.property.report.job;

import com.property.report.service.property.PropertyExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PropertyScheduler {

    @Autowired
    private PropertyExecutorService propertyExecutorService;

    @Scheduled(fixedDelayString = "${job.schedule.fixed-delay}")
    public void merge() {
        propertyExecutorService.execute();
    }

}
