package com.property.report.service.ss;

import com.property.report.common.dto.ss.PropertyWithOnlinePresenceDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PropertyFetchService {
    public Page<PropertyWithOnlinePresenceDTO> getPropertiesPageWise(String url, Pageable pageable);
}
