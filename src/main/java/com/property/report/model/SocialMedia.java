package com.property.report.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.property.report.model.enums.SocialMediaNetwork;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Data;

import java.sql.Timestamp;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class SocialMedia {

    @Id
    private Integer id;

    @Enumerated
    private SocialMediaNetwork network;

    private String profile;
    private Timestamp lastUpdate;

}
