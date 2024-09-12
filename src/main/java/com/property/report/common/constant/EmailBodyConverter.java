package com.property.report.common.constant;

import com.property.report.common.dto.PropertyCount;

import java.text.NumberFormat;
import java.util.Locale;

import static com.property.report.common.constant.WorldCsvConstant.emailContent;

public class EmailBodyConverter {

    public static String convertEmailBody(PropertyCount totalCount, PropertyCount percentageByProperty) {

        String replaceTotalProperty = emailContent.replace("%total_sum_of_property%", "<td valign=\"middle\" style=\"text-align: right;\">" + formatNumber(totalCount.getPropertyId()) + "</td><td valign=\"middle\" style=\"text-align: right;\">100%</td>");

        String replacePropertyId = replaceTotalProperty.replace("%property_id%", "<td valign=\"middle\" style=\"text-align: right;\">" + formatNumber(totalCount.getNetProperty()) + "</td><td valign=\"middle\" style=\"text-align: right;\">" + percentageByProperty.getNetProperty() + "%</td>");

        String replaceGoogleBusiness = replacePropertyId.replace("%google_business_id%", "<td valign=\"middle\" style=\"text-align: right;\">" + formatNumber(totalCount.getGoogleBusinessID()) + "</td><td valign=\"middle\" style=\"text-align: right;\">" + percentageByProperty.getGoogleBusinessID() + "%</td>");

        String replacePropertyName = replaceGoogleBusiness.replace("%property_name%", "<td valign=\"middle\" style=\"text-align: right;\">" + formatNumber(totalCount.getName()) + "</td><td valign=\"middle\" style=\"text-align: right;\">" + percentageByProperty.getName() + "%</td>");

        String replaceAddress = replacePropertyName.replace("%google_address%", "<td valign=\"middle\" style=\"text-align: right;\">" + formatNumber(totalCount.getGoogleAddress()) + "</td><td valign=\"middle\" style=\"text-align: right;\">" + percentageByProperty.getGoogleAddress() + "%</td>");

        String replaceEmail = replaceAddress.replace("%email%", "<td valign=\"middle\" style=\"text-align: right;\">" + formatNumber(totalCount.getEmail()) + "</td><td valign=\"middle\" style=\"text-align: right;\">" + percentageByProperty.getEmail() + "%</td>");

        String replaceGooglePhone = replaceEmail.replace("%google_phone%", "<td valign=\"middle\" style=\"text-align: right;\">" + formatNumber(totalCount.getGooglePhoneNumber()) + "</td><td valign=\"middle\" style=\"text-align: right;\">" + percentageByProperty.getGooglePhoneNumber() + "%</td>");

        String replaceWebsiteUrl = replaceGooglePhone.replace("%website_url%", "<td valign=\"middle\" style=\"text-align: right;\">" + formatNumber(totalCount.getWebsiteURL()) + "</td><td valign=\"middle\" style=\"text-align: right;\">" + percentageByProperty.getWebsiteURL() + "%</td>");

        String replaceUsedBooking = replaceWebsiteUrl.replace("%used_booking_engine%", "<td valign=\"middle\" style=\"text-align: right;\">" + formatNumber(totalCount.getUsedBookingEngine()) + "</td><td valign=\"middle\" style=\"text-align: right;\">" + percentageByProperty.getUsedBookingEngine() + "%</td>");

        String replaceGoogleCategory = replaceUsedBooking.replace("%google_category%", "<td valign=\"middle\" style=\"text-align: right;\">" + formatNumber(totalCount.getGoogleCategory()) + "</td><td valign=\"middle\" style=\"text-align: right;\">" + percentageByProperty.getGoogleCategory() + "%</td>");

        String replaceFreeBookingLink = replaceGoogleCategory.replace("%free_booking_link%", "<td valign=\"middle\" style=\"text-align: right;\">" + formatNumber(totalCount.getFreeGoogleBookingLinks()) + "</td><td valign=\"middle\" style=\"text-align: right;\">" + percentageByProperty.getFreeGoogleBookingLinks() + "%</td>");

        String replaceGoogleRating = replaceFreeBookingLink.replace("%google_rating%", "<td valign=\"middle\" style=\"text-align: right;\">" + formatNumber(totalCount.getGoogleRating()) + "</td><td valign=\"middle\" style=\"text-align: right;\">" + percentageByProperty.getGoogleRating() + "%</td>");

        String replaceCmsUsed = replaceGoogleRating.replace("%cms_used_in_website%", "<td valign=\"middle\" style=\"text-align: right;\">" + formatNumber(totalCount.getCmsUsedInWebsite()) + "</td><td valign=\"middle\" style=\"text-align: right;\">" + percentageByProperty.getCmsUsedInWebsite() + "%</td>");

        String replaceEmailUnsubscribe = replaceCmsUsed.replace("%email_unsubscribe%", "<td valign=\"middle\" style=\"text-align: right;\">" + formatNumber(totalCount.getEmailAddressUnsubscribe()) + "</td><td valign=\"middle\" style=\"text-align: right;\">" + percentageByProperty.getEmailAddressUnsubscribe() + "%</td>");

        String replaceTopic = replaceEmailUnsubscribe.replace("%topic%", "<td valign=\"middle\" style=\"text-align: right;\">" + formatNumber(totalCount.getTopic()) + "</td><td valign=\"middle\" style=\"text-align: right;\">" + percentageByProperty.getTopic() + "%</td>");

        String replaceIsDeleted = replaceTopic.replace("%is_deleted%", "<td valign=\"middle\" style=\"text-align: right;\">" + formatNumber(totalCount.getIsDeleted()) + "</td><td valign=\"middle\" style=\"text-align: right;\">" + percentageByProperty.getIsDeleted() + "%</td>");

        String replaceForTesting = replaceIsDeleted.replace("%for_testing%", "<td valign=\"middle\" style=\"text-align: right;\">" + formatNumber(totalCount.getForTesting()) + "</td><td valign=\"middle\" style=\"text-align: right;\">" + percentageByProperty.getForTesting() + "%</td>");

        return replaceForTesting.replace("%sent_at%", "<td valign=\"middle\" style=\"text-align: right;\">" + formatNumber(totalCount.getSentAt()) + "</td><td valign=\"middle\" style=\"text-align: right;\">" + percentageByProperty.getSentAt() + "%</td>");


    }

    public static String formatNumber(float number) {
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.ENGLISH);
        return numberFormat.format(number);
    }
}
