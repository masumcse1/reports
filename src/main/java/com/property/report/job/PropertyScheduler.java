package com.property.report.job;

import com.property.report.service.property.PropertyExecutorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@Slf4j
public class PropertyScheduler {

    @Autowired
    private PropertyExecutorService propertyExecutorService;

    @Scheduled(fixedDelayString = "${job.schedule.fixed-delay}")
    public void merge() {
        log.info("Starting Scheduled Task..."+ Instant.now());
        propertyExecutorService.dataSynForProperty();

        log.info("Scheduled Task completed successfully!"+ Instant.now());

    }

}
