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

    @Value("${property.cron.flag}")
    boolean enabled;

    @Scheduled(fixedDelayString = "${property.scheduler.fixed-delay}")
    public void merge()  {

        if (!propertyExecutorService.getPaginationLog().get().getIsReadAllPage() &&  enabled){
                log.info("Starting Scheduled Task...");

            try {
                propertyExecutorService.dataSynForProperty();
            } catch (Exception e) {
                log.error("Error correction with retrying: ---" + e.getMessage());
                try {
                    Thread.sleep(30000);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                merge();
            }

            log.info("Scheduled Task completed successfully!");
        }else {
            log.info("----------property data sync scheduler is not running --------------");
        }


    }

}
