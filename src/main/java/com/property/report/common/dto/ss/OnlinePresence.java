package com.property.report.common.dto.ss;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OnlinePresence {

    private List<SocialMedia> socialMedias;

    // Google related properties
    @JsonProperty
    @Size(min = 1, max = 255)
    @Schema(description = "Property name at Google", example = "Golden Tulip")
    private String googlePropertyName;

    @JsonProperty
    @Schema(description = "Last update of property name at Google.")
    private Timestamp googlePropertyNameLastUpdate;

    @JsonProperty
    @Size(min = 1, max = 255)
    @Schema(description = "Property address at Google.")
    private String googleAddress;

    @JsonProperty
    @Schema(description = "Last update of property address at Google.")
    private Timestamp googleAddressLastUpdate;

    @JsonProperty
    @Size(min = 1, max = 15)
    @Schema(description = "Phone number at Google.", example = "949302387654")
    private String googlePhoneNumber;

    @JsonProperty
    @Schema(description = "Last update of phone number at Google.")
    private Timestamp googlePhoneNumberLastUpdate;

    @JsonProperty
    @Size(min = 1, max = 255)
    @Schema(description = "Category at Google.")
    private String googleCategory;

    @JsonProperty
    @Schema(description = "Last update of category at Google.")
    private Timestamp googleCategoryLastUpdate;

    @JsonProperty
    @Size(min = 1, max = 2047)
    @Schema(description = "Screenshot of property name and address at Google (just URL, screenshot itself should be stored on media server).")
    private String propertyNameAndAddressAtGoogleScreenshotUrl;

    @JsonProperty
    @Schema(description = "Last update of the URL of \"property name and address at Google\" screenshot.")
    private Timestamp propertyNameAndAddressAtGoogleScreenshotUrlLastUpdate;

    @JsonProperty
    @Size(min = 1, max = 255)
    @Schema(description = "Google Business Places ID.")
    private String googleBusinessPlacesId;

    @JsonProperty
    @Schema(description = "Last update of the Google Business Places ID.")
    private Timestamp googleBusinessPlacesIdLastUpdate;

    @JsonProperty
    @Size(min = 1, max = 2047)
    @Schema(description = "Screenshot of Google Maps with Google Business Places ID (just URL, screenshot itself should be stored on media server).")
    private String googleMapsWithGoogleBusinessPlacesIdScreenshotUrl;

    @JsonProperty
    @Schema(description = "Last update of the URL of \"Google Maps with Google Business Places ID\" screenshot.")
    private Timestamp googleMapsGoogleBusinessPlacesIdScreenshotUrlLastUpdate;

    @JsonProperty
    @Min(0)
    @Schema(description = "rating at Google")
    private Float googleRating;

    @JsonProperty
    @Schema(description = "Last update of rating at Google.")
    private Timestamp googleRatingLastUpdate;

    @JsonProperty
    @Min(0)
    @Schema(description = "Number of reviews at Google")
    private Integer googleNumberOfReviews;

    @JsonProperty
    @Schema(description = "Last update of number of reviews at Google.")
    private Timestamp googleNumberOfReviewsLastUpdate;

    @JsonProperty
    @Size(min = 1, max = 2047)
    @Schema(description = "Screenshot of Google rating and number of reviews (just URL, screenshot itself should be stored on media server).")
    private String googleRatingAndNumberOfReviewsScreenshotUrl;

    @JsonProperty
    @Schema(description = "Last update of the URL of \"Google rating and number of reviews\" screenshot.")
    private Timestamp googleRatingAndNumberOfReviewsScreenshotUrlLastUpdate;

    @JsonProperty
    @Schema(description = "Free Google booking links (boolean).")
    private Boolean freeGoogleBookingLinks;

    @JsonProperty
    @Schema(description = "Last update of the free Google booking links.")
    private Timestamp freeGoogleBookingLinksLastUpdate;

    @JsonProperty
    @Schema(description = "Screenshot of Google meta search links (just URL, screenshot itself should be stored on media server).")
    private String googleMetaSearchLinksUrl;

    @JsonProperty
    @Schema(description = "Last update of the URL of \"Google meta search links\" screenshot.")
    private Timestamp googleMetaSearchLinksUrlLastUpdate;

    // Website related properties
    @JsonProperty
    @Size(min = 1, max = 2047)
    @Schema(description = "Website URL")
    private String websiteUrl;

    @JsonProperty
    @Schema(description = "Last update of the website  URL.")
    private Timestamp websiteUrlLastUpdate;

    @JsonProperty
    @Schema(description = "Is Website URL Reachable")
    private Boolean websiteUrlReachable;

    @JsonProperty
    @Schema(description = "Last update of the website URL Reachability.")
    private Timestamp websiteUrlReachabilityLastUpdate;

    @JsonProperty
    @Size(min = 1, max = 2047)
    @Schema(description = "Screenshot of website landing page (just URL, screenshot itself should be stored on media server).")
    private String screenshotOfWebsiteLandingPageUrl;

    @JsonProperty
    @Schema(description = "Last update of the URL of \"website landing page\" screenshot.")
    private Timestamp screenshotOfWebsiteLandingPageUrlLastUpdate;

    @JsonProperty
    @Schema(description = "Is SSL certification enabled?", example = "true")
    private Boolean sslCertificate;

    @JsonProperty
    @Schema(description = "SSL certificate start date")
    private Timestamp sslCertificateBeginDate;

    @JsonProperty
    @Schema(description = "SSL certificate expire date")
    private Timestamp sslCertificateExpireDate;

    @JsonProperty
    @Schema(description = "Last update of SSL certificate data.")
    private Timestamp sslCertificateLastUpdate;

    @JsonProperty
    @Size(min = 1, max = 63)
    @Schema(description = "Language of website.")
    private String languageOfWebsite;

    @JsonProperty
    @Schema(description = "Last update of website language.")
    private Timestamp languageOfWebsiteLastUpdate;

    @JsonProperty
    @Size(min = 1, max = 63)
    @Schema(description = "CMS of the website.")
    private String cmsUsedInWebsite;

    @JsonProperty
    @Schema(description = "Last update of the CMS used in website.")
    private Timestamp cmsUsedInWebsiteLastUpdate;

    @JsonProperty
    @Size(min = 1, max = 31)
    @Schema(description = "CMS version of the website.")
    private String versionOfCmsUsedInWebsite;

    @JsonProperty
    @Schema(description = "Last update of version of the CMS used in website.")
    private Timestamp versionOfCmsUsedInWebsiteLastUpdate;

    @JsonProperty
    @Size(min = 1, max = 31)
    @Schema(description = "Programming language of the website.")
    private String programmingLanguageOfWebsite;

    @JsonProperty
    @Schema(description = "Last update of the website programming language.")
    private Timestamp programmingLanguageOfWebsiteLastUpdate;

    @JsonProperty
    @Size(min = 1, max = 31)
    @Schema(description = "Version of programming language used to develop the website.")
    private String versionOfProgrammingLanguage;

    @JsonProperty
    @Schema(description = "Last update of the version of programming language.")
    private Timestamp versionOfProgrammingLanguageLastUpdate;

    @JsonProperty
    @Size(min = 1, max = 31)
    @Schema(description = "Web server of the website")
    private String webserver;

    @JsonProperty
    @Schema(description = "Last update of website webserver")
    private Timestamp webserverLastUpdate;

    @JsonProperty
    @Size(min = 1, max = 2047)
    @Schema(description = "Screenshot of CMS detection (just URL, screenshot itself should be stored on media server).")
    private String screenshotOfCmsDetectionUrl;

    @JsonProperty
    @Schema(description = "Last update of the URL of \"CMS detection\" screenshot.")
    private Timestamp screenshotOfCmsDetectionUrlLastUpdate;

    @JsonProperty
    @Schema(description = "Booking engine (boolean)")
    private Boolean bookingEngine;

    @JsonProperty
    @Schema(description = "Last update of the booking engine.")
    private Timestamp bookingEngineLastUpdate;

    @JsonProperty
    @Schema(description = "Brand of booking engine")
    private String brandOfBookingEngine;

    @JsonProperty
    @Schema(description = "Brand of booking engine Id")
    private Integer brandOfBookingEngineId;

    @JsonProperty
    @Schema(description = "Last update of the brand of booking engine.")
    private Timestamp brandOfBookingEngineLastUpdate;

    @JsonProperty
    @Schema(description = "Screenshot of booking engine (just URL, screenshot itself should be stored on media server).")
    private String screenshotOfBookingEngineUrl;

    @JsonProperty
    @Schema(description = "Last update of the URL of \"booking engine\" screenshot.")
    private Timestamp screenshotOfBookingEngineUrlLastUpdate;

    @JsonProperty
    @Schema(description = "When the Google Map API was triggered to search for \"lodging\" nearby a property.")
    private Timestamp googleNearbySearchLastUpdate;

    @JsonProperty
    @Min(1_000_000)
    @Schema(description = "Property ID", example = "1000068")
    private Integer propertyId;

    @JsonProperty
    @Schema(description = "latitude of property.", example = "20.10")
    private BigDecimal latitude;

    @JsonProperty
    @Schema(description = "longitude of property.", example = "22.30")
    private BigDecimal longitude;

    @Schema(description = "Brand of booking engine Id", accessMode = Schema.AccessMode.READ_ONLY, hidden = true)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private BrandOfBookingEngineDto brandOfBookingEngineDto;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(description = "Last update of online presence object")
    private Timestamp lastUpdate;

}
