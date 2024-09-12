package com.property.report.common.util;

import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Slf4j
public class DateTimeUtil {


    public static Timestamp convertStringToTimeStamp(String dateTimeString){

        // Define the formatter to match the input string
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try {

            if (dateTimeString.contains(".")) {
                dateTimeString = dateTimeString.substring(0, dateTimeString.indexOf('.'));
            }
            // Parse the string to a LocalDateTime object
            LocalDateTime localDateTime = LocalDateTime.parse(dateTimeString, formatter);

            // Convert LocalDateTime to Timestamp
           return Timestamp.valueOf(localDateTime);
        } catch (DateTimeParseException e) {
            log.error("Error parsing date-time string: " + e.getMessage());
            return null;
        }
    }

    public static String convertTimeStampToString() {

        ZonedDateTime gmtDateTime = ZonedDateTime.now(ZoneId.of("GMT"));
        //convert timezone to string
        return gmtDateTime.toInstant().toString();
    }

}
