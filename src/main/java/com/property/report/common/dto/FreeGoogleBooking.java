package com.property.report.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FreeGoogleBooking {

    private Integer id;

    private String name;

    private String emailAddress;

    private Boolean freeGoogleBookingLinks;

    private String websiteUrl;

    private String googleBusinessPlacesId;

    private String brandOfBookingEngine;

    private String cmsUsedInWebsite;

    private String googleCategory;

    private String googlePhoneNumber;

    private Integer googleRating;

    private String googleAddress;

    private String googleMetaSearchLinksUrl;

    private String googleMapsWithGoogleBusinessPlacesIdScreenshotUrl;


}
