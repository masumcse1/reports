package com.property.report.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "property_email")
public class PropertyEmail {

    @Id
    private Integer id;

    private String email;
    private String status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "email_type_id")
    private EmailType emailType;

}
