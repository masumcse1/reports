package com.property.report.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EwsEmail {

     private String _id;

     private String body;

     private String created_at;

     private String status;

     private List<String> bcc;

     private String webhook_url;

}
