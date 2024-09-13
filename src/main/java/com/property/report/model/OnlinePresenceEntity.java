package com.property.report.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "online_presence")
public class OnlinePresenceEntity {

    @Id
    private Integer propertyId;

    private String googlePropertyName;
    private Timestamp googlePropertyNameLastUpdate;
    private String googleAddress;
    private Timestamp googleAddressLastUpdate;
    private String googlePhoneNumber;
    private Timestamp googlePhoneNumberLastUpdate;
    private String googleCategory;
    private Timestamp googleCategoryLastUpdate;
    private String googleBusinessPlacesId;
    private Timestamp googleBusinessPlacesIdLastUpdate;
    private Float googleRating;
    private Timestamp googleRatingLastUpdate;
    private Integer googleNumberOfReviews;
    private Timestamp googleNumberOfReviewsLastUpdate;
    private String websiteUrl;
    private Timestamp websiteUrlLastUpdate;
    private Boolean sslCertificate;
    private Timestamp sslCertificateLastUpdate;
    private Boolean freeGoogleBookingLinks;
    private Timestamp freeGoogleBookingLinksLastUpdate;

    @JsonProperty("brandOfBookingEngineDto")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "brand_of_booking_engine_id")
    private BrandOfBookingEngine brandOfBookingEngine;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<SocialMedia> socialMedias;

}
