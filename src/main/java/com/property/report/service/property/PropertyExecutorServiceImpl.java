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

import java.util.Optional;

@Slf4j
@Service
public class PropertyExecutorServiceImpl implements PropertyExecutorService {

    @Autowired
    private PaginationLogRepository paginationLogRepository;

    @Autowired
    private PropertyFetchService propertyFetchService;

    @Autowired
    private PropertyStorageService propertyStorageService;

    @Value("${property.schedule.page-size}")
    private Integer size;


    @Override
    public void dataSynForProperty() throws Exception {

        PaginationLog paginationLog = new PaginationLog();

        paginationLog = paginationLogRepository.findById(paginationLog.getId())
                .orElse(paginationLog);

        Pageable pageable = Pageable.ofSize(size).withPage(paginationLog.getPageNumber());
        log.info("starting page for reading : ---" + paginationLog.getPageNumber());
        try {
            Page<PropertyDto> properties;

            do {
                properties = propertyFetchService.getProperties(pageable);

                if(properties.isEmpty()){
                    paginationLog.setIsReadAllPage(true);
                    paginationLogRepository.save(paginationLog);
                    break;
                }
                propertyStorageService.save(properties.getContent());
                pageable = pageable.next();

                paginationLog.setPageNumber(pageable.getPageNumber());
                paginationLogRepository.save(paginationLog);
                log.info("current page for reading : ---" + pageable.getPageNumber());

            } while (pageable.getPageNumber() < properties.getTotalPages());

        } catch (Exception ex) {
            log.error("Error page no : ---" + pageable.getPageNumber());
            log.error("Error page reason : ---" + ex.getCause()+"-----"+ex.getMessage());
            //log.error("Data unable to process", ex);
            throw new Exception(String.valueOf(pageable.getPageNumber()));
        }

    }

    public Optional<PaginationLog>  getPaginationLog(){
        return paginationLogRepository.findById(1);
    }

}
