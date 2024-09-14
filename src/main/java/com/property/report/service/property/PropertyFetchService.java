package com.property.report.service.property;

import com.property.report.common.dto.PropertyDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PropertyFetchService {

    public Page<PropertyDto> getProperties(Pageable pageable);

}
