package com.property.report.common.dto.ss;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PropertyMessageDTO {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PropertyMessageDTO that = (PropertyMessageDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
