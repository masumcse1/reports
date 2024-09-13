package com.property.report.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.sql.Timestamp;

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

}
