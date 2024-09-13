package com.property.report.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.sql.Timestamp;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "property_message")
public class PropertyMessage {

    @Id
    private Integer id;

    private Integer propertyId;
    private Integer campaignId;
    private String name;
    private String topic;
    private String subject;
    private String toAddress;
    private String toName;
    private String fromAddress;
    private String fromName;
    private String replyAddress;
    private String replyName;
    private String domain;
    private String provider;
    private String details;
    private String ewsId;
    private String status;
    private String body;
    private Timestamp sentAt;
    private Timestamp scheduledFor;
    private Timestamp permissionTimestamp;
    private Timestamp dismissTopicTimestamp;
    private Timestamp unsubscribeTimestamp;
    private Boolean permission;
    private Boolean dismissTopic;
    private Boolean unsubscribe;
    private String dismissTopicIPv4;
    private String permissionIPv4;
    private String unsubscribeIPv4;
    private Timestamp lastUpdate;

}
