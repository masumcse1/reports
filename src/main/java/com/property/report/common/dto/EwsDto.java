package com.property.report.common.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EwsDto {

    private String subject;

    private String body;

    private String to_address;

    private String to_name;

    private String from_address;

    private String from_name;

    private String reply_address;

    private String reply_name;

   private List<String> bcc;

   private String domain;

   private String webhook_url;

   private String attachment_url;

    public EwsDto(String body,String attachment_url) {
        this.subject = convertDateToString()+" : World property list";
        this.body = body;
        this.to_address ="property-list-world@cultuzz.com";
        this.to_name = "Reinhard vogel";
        this.from_address ="sendCsv@mail5.cultbooking.com";
        this.from_name = "World CSV";
        this.attachment_url=attachment_url;
    }

    public String convertDateToString(){

        Date currentDate = new Date();

        // Create a SimpleDateFormat object with the desired date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // Use the format() method to format the Date object as a string
        return dateFormat.format(currentDate);

    }
}
