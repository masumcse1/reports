package com.property.report.common.constant;

public class WorldCsvConstant {

    public static String emailContent = """
            Please find the updated world CSV attached below.<br><br>
            
            <table>
            <thead>
            <th>Field</th>
            <th style="text-align: right;">Properties</th>
            <th style="text-align: right;">% of total</th>
            </thead>
            <tbody>
            <tr><td valign="middle"> Total sum of properties:</td> %total_sum_of_property%</tr>
            <tr><td valign="middle">Properties for deletion:</td> %is_deleted%</tr>
            <tr><td valign="middle">Properties for testing:</td> %for_testing%</tr>
            <tr><td valign="middle"> Net property IDs:</td> %property_id%</tr>
            <tr><td valign="middle">Google Business ID:</td> %google_business_id%</tr>
            <tr><td valign="middle">Property name:</td> %property_name%</tr>
            <tr><td valign="middle">Google Address:</td> %google_address%</tr>
            <tr><td valign="middle" >Email Address:</td> %email%</tr>
            <tr><td valign="middle">Google phone number:</td> %google_phone%</tr>
            <tr><td valign="middle">Website URL:</td> %website_url%</tr>
            <tr><td valign="middle">Used booking engine:</td> %used_booking_engine%</tr>
            <tr><td valign="middle">Google category:</td> %google_category% </tr>
            <tr><td valign="middle">Free Google booking links:</td> %free_booking_link%</tr>
            <tr><td valign="middle">Google rating:</td> %google_rating%</tr>
            <tr><td valign="middle">CMS used in website:</td> %cms_used_in_website%</tr>
            <tr><td valign="middle">Email address unsubscribe:</td> %email_unsubscribe%</tr>
            <tr><td valign="middle">Topic:</td> %topic%</tr>
            <tr><td valign="middle">Sent at:</td> %sent_at%</tr>
            
            </tbody>
            </table>
            
            """;
}
