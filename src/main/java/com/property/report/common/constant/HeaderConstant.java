package com.property.report.common.constant;

import com.property.report.common.dto.PropertyCount;
import com.property.report.model.Country;
import com.property.report.model.Property;

import java.util.Objects;

public class HeaderConstant {

    public static final String[] HEADERS = {"S.NO", "Property ID", "Google Business ID", "Property Name",
            "Google Address", "Email address", "Google phone number", "Website URL", "Used booking engine",
            "Google Category", "Free Google Booking links", "Google Rating", "CMS used in Website",
            "emailAddressUnsubscribe", "topic", "sentAt"};

    public static String[] convertDetailsToString(Property campaignData, int i) {
        return new String[]{String.valueOf(i),
                String.valueOf(campaignData.getPropertyId()),
                campaignData.getGoogleBusinessID(),
                campaignData.getName(),
                campaignData.getGoogleAddress(),
                campaignData.getEmail(),
                campaignData.getGooglePhoneNumber(),
                campaignData.getWebsiteURL(),
                campaignData.getUsedBookingEngine(),
                campaignData.getGoogleCategory(),
                String.valueOf(campaignData.getFreeGoogleBookingLinks()),
                String.valueOf(campaignData.getGoogleRating()),
                campaignData.getCmsUsedInWebsite(),
                String.valueOf(campaignData.getEmailAddressUnsubscribe()),
                campaignData.getTopic(),
                campaignData.getSentAt()};
    }

    public static final String[] WORLD_HEADERS = {"S.NO", "Country", "Country code", "Property ID", "Google Business ID",
            "Booking.com URL", "Booking.com id", "EHotel id", "Property Name", "Google Address", "Email address",
            "Google phone number", "Website URL", "Used booking engine", "Google Category", "Free Google Booking links",
            "Google Rating", "CMS used in Website", "emailAddressUnsubscribe", "topic", "sentAt", "isDeleted", "forTesting"};

    public static final String[] GEOCODE_HEADERS = {"S.NO", "Country", "Country code", "Property ID", "Google Business ID",
            "Property Name", "Google Address", "Email address", "Website URL", "Latitude", "Longitude"};


    public static final String[] GOOGLE_PARSER_HEADERS = {"S.No", "Country", "Property ID"};

    public static String[] convertGooglePrserPropertyToString(Integer propertyId, int i, Country country) {
        return new String[]{
                String.valueOf(i),
                country.getName(),
                String.valueOf(propertyId)};
    }

    public static String[] convertWorldDetailsToString(PropertyCount propertyCount, int i) {
        return new String[]{
                String.valueOf(i),
                propertyCount.getCountry(),
                propertyCount.getCountryCode(),
                String.valueOf(propertyCount.getPropertyId()),
                String.valueOf(propertyCount.getGoogleBusinessID()),
                String.valueOf(propertyCount.getScreenshotOfBookingEngineUrl()),
                String.valueOf(propertyCount.getBrandOfBookingEngineId()),
                String.valueOf(propertyCount.getEHotelId()),
                String.valueOf(propertyCount.getName()),
                String.valueOf(propertyCount.getGoogleAddress()),
                String.valueOf(propertyCount.getEmail()),
                String.valueOf(propertyCount.getGooglePhoneNumber()),
                String.valueOf(propertyCount.getWebsiteURL()),
                String.valueOf(propertyCount.getUsedBookingEngine()),
                String.valueOf(propertyCount.getGoogleCategory()),
                String.valueOf(propertyCount.getFreeGoogleBookingLinks()),
                String.valueOf(propertyCount.getGoogleRating()),
                String.valueOf(propertyCount.getCmsUsedInWebsite()),
                String.valueOf(propertyCount.getEmailAddressUnsubscribe()),
                String.valueOf(propertyCount.getTopic()),
                String.valueOf(propertyCount.getSentAt()),
                String.valueOf(propertyCount.getIsDeleted()),
                String.valueOf(propertyCount.getForTesting())};
    }

    public static String[] convertGeocodeToString(PropertyCount propertyCount, int i) {
        return new String[]{
                String.valueOf(i),
                propertyCount.getCountry(),
                propertyCount.getCountryCode(),
                String.valueOf(propertyCount.getPropertyId()),
                String.valueOf(propertyCount.getGoogleBusinessID()),
                String.valueOf(propertyCount.getName()),
                String.valueOf(propertyCount.getGoogleAddress()),
                String.valueOf(propertyCount.getEmail()),
                String.valueOf(propertyCount.getWebsiteURL()),
                String.valueOf(propertyCount.getLatitude()),
                String.valueOf(propertyCount.getLongitude())};
    }

    public static String[] convertWorldDetailsToStringPercantage(PropertyCount propertyCount, int i, String percantage) {
        return new String[]{
                String.valueOf(i),
                propertyCount.getCountry(),
                "",
                propertyCount.getPropertyId() + percantage,
                propertyCount.getGoogleBusinessID() + percantage,
                propertyCount.getScreenshotOfBookingEngineUrl() + percantage,
                propertyCount.getBrandOfBookingEngineId() + percantage,
                propertyCount.getEHotelId() + percantage,
                propertyCount.getName() + percantage,
                propertyCount.getGoogleAddress() + percantage,
                propertyCount.getEmail() + percantage,
                propertyCount.getGooglePhoneNumber() + percantage,
                propertyCount.getWebsiteURL() + percantage,
                propertyCount.getUsedBookingEngine() + percantage,
                propertyCount.getGoogleCategory() + percantage,
                propertyCount.getFreeGoogleBookingLinks() + percantage,
                propertyCount.getGoogleRating() + percantage,
                propertyCount.getCmsUsedInWebsite() + percantage,
                propertyCount.getEmailAddressUnsubscribe() + percantage,
                propertyCount.getTopic() + percantage,
                propertyCount.getSentAt() + percantage,
                propertyCount.getIsDeleted() + percantage,
                propertyCount.getForTesting() + percantage};
    }

}
