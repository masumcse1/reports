package com.property.report.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PropertyDto {

    private Integer id;

    private String name;

    private String cultSwitchId;

    private String status;

    private boolean emailAddressUnsubscribe;

    private String emailAddressComment;

    private Boolean isDeleted;

    private Boolean forTesting;

    private String website;

    private GeoCode geoCode;

    private FreeGoogleBooking onlinePresence;

    private List<EmailEntity> emails;

    private List<AddressDto> addresses;

}
