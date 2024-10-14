package com.property.report.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CountryDto {

    private Integer id;

    private String name;

    private String fullName;

    private String code;

    private String codeA3;

    private Integer codeNumeric;
}
