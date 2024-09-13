package com.property.report.service.ss;

import com.property.report.model.PropertyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PropertyFetchService {

    public Page<PropertyEntity> getProperties(Pageable pageable);

}
