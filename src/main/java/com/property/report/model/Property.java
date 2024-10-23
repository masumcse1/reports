package com.property.report.model;

import com.property.report.common.dto.FreeGoogleBooking;
import com.property.report.common.dto.PropertyDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

import static com.property.report.service.SupplierServiceImpl.getPropertyEmail;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Property {

    @Id
    @Column(nullable = false, unique = true)
    private Integer id;

    private String email;

    @Column(nullable = false, unique = true)
    private Integer propertyId;

    private String name;

    @Column(length = 65000, columnDefinition = "TEXT")
    private String googleAddress;

    @Column(length = 65000, columnDefinition = "TEXT")
    private String googleBusinessID;

    private String googlePhoneNumber;

    @Column(length = 65000, columnDefinition = "TEXT")
    private String websiteURL;

    @Column(length = 65000, columnDefinition = "TEXT")
    private String propertyWebsiteURL;

    private String usedBookingEngine;

    private Boolean emailAddressUnsubscribe;

    private Boolean freeGoogleBookingLinks;

    private Integer googleRating;

    private String cmsUsedInWebsite;

    private String googleCategory;

    private String topic;

    private String sentAt;

    private Boolean isDeleted;

    private Boolean forTesting;

    private Boolean googleParser;

    private Double latitude;

    private Double longitude;

    private String googleMetaSearchLinksUrl;

    private String googleMapsWithGoogleBusinessPlacesIdScreenshotUrl;

    private String screenshotOfBookingEngineUrl;

    private String brandOfBookingEngineId;

    private String eHotelId;

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id", nullable = true)
    private Country country;
    
    public Property(PropertyDto propertyDto, FreeGoogleBooking freeGoogleBooking, Country country) {
        this.id = propertyDto.getId();
        this.propertyId = propertyDto.getId();
        this.name = propertyDto.getName();
        this.email = getPropertyEmail(propertyDto);
        this.emailAddressUnsubscribe = propertyDto.isEmailAddressUnsubscribe();
        this.country = country;
        this.isDeleted = propertyDto.getIsDeleted();
        this.forTesting = propertyDto.getForTesting();
        this.propertyWebsiteURL = propertyDto.getWebsite();
        if (Objects.nonNull(propertyDto.getGeoCode())) {
            this.latitude = propertyDto.getGeoCode().getLatitude();
            this.longitude = propertyDto.getGeoCode().getLongitude();
        }
        if (Objects.nonNull(freeGoogleBooking)) {
            this.websiteURL = freeGoogleBooking.getWebsiteUrl();
            this.usedBookingEngine = freeGoogleBooking.getBrandOfBookingEngine();
            this.googleBusinessID = freeGoogleBooking.getGoogleBusinessPlacesId();
            this.freeGoogleBookingLinks = freeGoogleBooking.getFreeGoogleBookingLinks();
            this.cmsUsedInWebsite = freeGoogleBooking.getCmsUsedInWebsite();
            this.googleCategory = freeGoogleBooking.getGoogleCategory();
            this.googlePhoneNumber = freeGoogleBooking.getGooglePhoneNumber();
            this.googleRating = freeGoogleBooking.getGoogleRating();
            this.googleAddress = freeGoogleBooking.getGoogleAddress();
            this.googleMetaSearchLinksUrl = freeGoogleBooking.getGoogleMetaSearchLinksUrl();
            this.googleMapsWithGoogleBusinessPlacesIdScreenshotUrl = freeGoogleBooking.getGoogleMapsWithGoogleBusinessPlacesIdScreenshotUrl();
            this.screenshotOfBookingEngineUrl = freeGoogleBooking.getScreenshotOfBookingEngineUrl();
//            this.brandOfBookingEngineId = freeGoogleBooking.getBrandOfBookingEngineId();
        }
    }

    public Property(PropertyDto propertyDto, FreeGoogleBooking freeGoogleBooking, Country country, String bookingDotComId, String bookingDotComUrl, String eHotelId) {

        this.id = propertyDto.getId();
        this.propertyId = propertyDto.getId();
        this.name = propertyDto.getName();
        this.email = getPropertyEmail(propertyDto);
        this.emailAddressUnsubscribe = propertyDto.isEmailAddressUnsubscribe();
        this.country = country;
        this.isDeleted = propertyDto.getIsDeleted();
        this.forTesting = propertyDto.getForTesting();
        this.propertyWebsiteURL = propertyDto.getWebsite();
        if (Objects.nonNull(propertyDto.getGeoCode())) {
            this.latitude = propertyDto.getGeoCode().getLatitude();
            this.longitude = propertyDto.getGeoCode().getLongitude();
        }
        if (Objects.nonNull(freeGoogleBooking)) {
            this.websiteURL = freeGoogleBooking.getWebsiteUrl();
            this.usedBookingEngine = freeGoogleBooking.getBrandOfBookingEngine();
            this.googleBusinessID = freeGoogleBooking.getGoogleBusinessPlacesId();
            this.freeGoogleBookingLinks = freeGoogleBooking.getFreeGoogleBookingLinks();
            this.cmsUsedInWebsite = freeGoogleBooking.getCmsUsedInWebsite();
            this.googleCategory = freeGoogleBooking.getGoogleCategory();
            this.googlePhoneNumber = freeGoogleBooking.getGooglePhoneNumber();
            this.googleRating = freeGoogleBooking.getGoogleRating();
            this.googleAddress = freeGoogleBooking.getGoogleAddress();
            this.googleMetaSearchLinksUrl = freeGoogleBooking.getGoogleMetaSearchLinksUrl();
            this.googleMapsWithGoogleBusinessPlacesIdScreenshotUrl = freeGoogleBooking.getGoogleMapsWithGoogleBusinessPlacesIdScreenshotUrl();

        }
        this.screenshotOfBookingEngineUrl = bookingDotComUrl;
        this.brandOfBookingEngineId = bookingDotComId;
        this.eHotelId = eHotelId;
    }
}
