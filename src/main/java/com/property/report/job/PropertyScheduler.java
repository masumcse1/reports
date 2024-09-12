package com.property.report.job;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PropertyScheduler {

    @Value("${job.schedule.page-size}")
    private Integer size;

    @Scheduled(fixedDelayString = "${job.schedule.fixed-delay}")
    public void merge() {

    }

}
