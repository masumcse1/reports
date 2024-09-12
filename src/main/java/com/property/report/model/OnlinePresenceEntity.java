package com.property.report.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "online_presence")
public class OnlinePresenceEntity {

	@Id
	private Integer id;

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
