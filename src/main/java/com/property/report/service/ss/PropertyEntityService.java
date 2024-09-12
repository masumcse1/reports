package com.property.report.service.ss;

import com.property.report.common.dto.ss.PropertyWithOnlinePresenceDTO;

import java.util.List;

public interface PropertyEntityService {
    void save(List<PropertyWithOnlinePresenceDTO> properties);
}
