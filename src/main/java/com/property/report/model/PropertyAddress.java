package com.property.report.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class PropertyAddress {
    @Id
    private Integer id;

    private String addressType;
    private String cityName;

    @ManyToOne
    private Country country;
}