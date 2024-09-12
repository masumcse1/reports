package com.property.report.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LogPropertyActivityQdo {


    private Integer id;


    private String dateOfRequest;


    private String dateOfResponse;


    private Integer property;


    private Integer scriptId;


    private String responseBody;


    private boolean success;


    private String server;


}
