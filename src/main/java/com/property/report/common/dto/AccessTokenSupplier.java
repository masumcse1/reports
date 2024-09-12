package com.property.report.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AccessTokenSupplier {

    private  String tokenType;

    private String accessToken;

    private int executionTime;

    private int expiresIn;

}
