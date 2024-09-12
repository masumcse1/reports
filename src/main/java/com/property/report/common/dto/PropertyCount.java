package com.property.report.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.DecimalFormat;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PropertyCount {

    private float email;

    private float propertyId;

    private float name;

    private float googleAddress;

    private float googleBusinessID;

    private float googlePhoneNumber;

    private float websiteURL;

    private float usedBookingEngine;

    private float emailAddressUnsubscribe;

    private float freeGoogleBookingLinks;

    private float googleRating;

    private float cmsUsedInWebsite;

    private float googleCategory;

    private String country;

    private String countryCode;

    private float topic;

    private float sentAt;

    private float isDeleted;

    private float forTesting;

    private float netProperty;

    private float latitude;

    private float longitude;

    public PropertyCount(String totalSumOfProperties) {
        this.country = totalSumOfProperties;
    }

    public PropertyCount(PropertyCount propertyCount) {
        this.email = convertfloat((propertyCount.getEmail() / propertyCount.getPropertyId()));
        this.propertyId = 100.0f;
        this.netProperty = 100.0f;
        this.name = convertfloat(propertyCount.getName() / propertyCount.getNetProperty());
        this.googleAddress = convertfloat(propertyCount.getGoogleAddress() / propertyCount.getNetProperty());
        this.googleBusinessID = convertfloat(propertyCount.getGoogleBusinessID() / propertyCount.getNetProperty());
        this.googlePhoneNumber = convertfloat(propertyCount.getGooglePhoneNumber() / propertyCount.getNetProperty());
        this.websiteURL = convertfloat(propertyCount.getWebsiteURL() / propertyCount.getNetProperty());
        this.usedBookingEngine = convertfloat(propertyCount.getUsedBookingEngine() / propertyCount.getNetProperty());
        this.emailAddressUnsubscribe = convertfloat(propertyCount.getEmailAddressUnsubscribe() / propertyCount.getNetProperty());
        this.freeGoogleBookingLinks = convertfloat(propertyCount.getFreeGoogleBookingLinks() / propertyCount.getNetProperty());
        this.googleRating = convertfloat(propertyCount.getGoogleRating() / propertyCount.getNetProperty());
        this.cmsUsedInWebsite = convertfloat(propertyCount.getCmsUsedInWebsite() / propertyCount.getNetProperty());
        this.googleCategory = convertfloat(propertyCount.getGoogleCategory() / propertyCount.getNetProperty());
        this.sentAt = convertfloat(propertyCount.getSentAt() / propertyCount.getNetProperty());
        this.topic = convertfloat(propertyCount.getTopic() / propertyCount.getNetProperty());
        this.forTesting = convertfloat(propertyCount.getForTesting() / propertyCount.getPropertyId());
        this.isDeleted = convertfloat(propertyCount.getIsDeleted() / propertyCount.getPropertyId());
        this.country = "Total % of properties";
    }

    public static float convertfloat(float floatValue) {
        DecimalFormat decimalFormat = new DecimalFormat("#.#");

        // Format the float value using the DecimalFormat
        String roundedValue = decimalFormat.format(100 * floatValue);

        // Convert the formatted string back to a float if needed
        return Float.parseFloat(roundedValue);

    }
}
