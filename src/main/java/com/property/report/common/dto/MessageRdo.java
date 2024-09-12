package com.property.report.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageRdo {

    private Integer id;

    private Integer propertyId;

    private String name;

    private String topic;

    private String subject;

    private String toAddress;

    private String toName;

    private String fromAddress;

    private String fromName;

    private String replyAddress;

    private String replyName;

    private String body;

    private String scheduledFor;

    private String sentAt;

    private Integer campaignId;

    }