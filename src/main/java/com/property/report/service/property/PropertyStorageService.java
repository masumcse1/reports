package com.property.report.service.property;

import com.property.report.common.dto.PropertyDto;

import java.util.List;

public interface PropertyStorageService {

    public void save(List<PropertyDto> properties);

}
