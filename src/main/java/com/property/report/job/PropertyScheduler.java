package com.property.report.job;

import com.property.report.service.property.PropertyExecutorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class PropertyScheduler {

    @Autowired
    private PropertyExecutorService propertyExecutorService;

    @Value("${cron.flag}")
    boolean enabled;

    @Scheduled(fixedDelayString = "${job.schedule.fixed-delay}")
    public void merge() throws Exception {

        if (!propertyExecutorService.getPaginationLog().get().getIsReadAllPage() &&  enabled){
                log.info("Starting Scheduled Task...");

            try {
                propertyExecutorService.dataSynForProperty();
            } catch (Exception e) {
                log.error("Error correction with retrying: ---" + e.getMessage());
                Thread.sleep(3000);
                propertyExecutorService.dataSynForProperty();
            }

            log.info("Scheduled Task completed successfully!");
        }else {
            log.info("----------not running this data sync scheduler--------------");
        }


    }

}
