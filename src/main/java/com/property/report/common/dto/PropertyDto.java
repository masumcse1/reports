package com.property.report.common.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PropertyDto {

    private Integer id;

    private String name;

    private String cultSwitchId;

    private String status;

    private boolean emailAddressUnsubscribe;

    private String emailAddressComment;

    private List<EmailEntity> emails;

    private List<AddressDto> addresses;

    private Boolean isDeleted;

    private Boolean forTesting;

    private String website;

    private GeoCode geoCode;



}
