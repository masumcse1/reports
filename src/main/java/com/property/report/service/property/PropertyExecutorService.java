package com.property.report.service.property;

import com.property.report.model.PaginationLog;

import java.util.Optional;

public interface PropertyExecutorService {

    public void dataSynForProperty() throws Exception;
    public Optional<PaginationLog> getPaginationLog();

}
