package com.property.report.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
