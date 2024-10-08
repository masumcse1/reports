package com.property.report.model;

import com.property.report.common.dto.CountryDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Country {

    @Id
    private Integer id;

    @Column(unique = true)
    private String name;

    private String fullName;

    private String code;

    private String codeA3;

    private Integer codeNumeric;

    public Country(CountryDto countryDto) {
        this.id = countryDto.getId();
        this.code = countryDto.getCode();
        this.name = countryDto.getName();
        this.codeA3 = countryDto.getCodeA3();
        this.fullName = countryDto.getFullName();
        this.codeNumeric = countryDto.getCodeNumeric();
    }
}
