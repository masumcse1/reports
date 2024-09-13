package com.property.report.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "property_entity")
public class PropertyEntity {

    @Id
    private Integer id;

    private String cultSwitchId;
    private String code;
    private String name;
    private String alternativeName;
    private String favicon;
    private String status;
    private Boolean forTesting;
    private Boolean isMaster;
    private Boolean isDeleted;
    private Boolean isDeletedBySupplier;
    private Boolean isPermanentlyClosed;
    private Boolean autoReplenishment;
    private Boolean showCultSwitchDefaultCancellationRules;
    private String emailAddressComment;
    private Boolean emailAddressUnsubscribe;
    private String vatNumber;
    private String legalCompanyName;
    private String commercialRegistrationNumber;
    private String managingDirector;
    private String companyOrganisationNumber;
    private String fax;
    private String distributorType;

    @OneToOne(cascade = CascadeType.ALL)
    private OnlinePresenceEntity onlinePresence;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<PropertyAddress> addresses = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    private Set<PropertyEmail> emails = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    private Set<PropertyMessage> messages = new HashSet<>();

}
