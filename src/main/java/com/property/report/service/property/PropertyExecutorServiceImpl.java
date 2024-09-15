package com.property.report.service.property;

import com.property.report.common.dto.PropertyDto;
import com.property.report.model.PaginationLog;
import com.property.report.repository.PaginationLogRepository;
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
    private PaginationLogRepository paginationLogRepository;

    @Autowired
    private PropertyFetchService propertyFetchService;

    @Autowired
    private PropertyStorageService propertyStorageService;

    @Value("${job.schedule.page-size}")
    private Integer size;

    @Override
    public void dataSynForProperty() {
        PaginationLog paginationLog = new PaginationLog();

        paginationLog = paginationLogRepository.findById(paginationLog.getId())
                .orElse(paginationLog);

        Pageable pageable = Pageable.ofSize(size)
                .withPage(paginationLog.getPageNumber());

        try {
            Page<PropertyDto> properties;

            do {
                properties = propertyFetchService.getProperties(pageable);

                propertyStorageService.save(properties.getContent());
                pageable = pageable.next();

                paginationLog.setPageNumber(pageable.getPageNumber());
                paginationLogRepository.save(paginationLog);

                log.info("Current page for property : ---" + pageable.getPageNumber());
            } while (pageable.getPageNumber() < properties.getTotalPages());

           // paginationLog.setPageNumber(0);
        } catch (Exception ex) {
            log.error("Data unable to read", ex);
        }

        // paginationLogRepository.save(paginationLog);
    }

}
