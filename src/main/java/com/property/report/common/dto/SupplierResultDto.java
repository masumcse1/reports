package com.property.report.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SupplierResultDto<T> {

    private T result;

    private String status;

    private String message;

    private int executionTime;


}
