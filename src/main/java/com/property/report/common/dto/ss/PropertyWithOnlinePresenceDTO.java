package com.property.report.common.dto.ss;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PropertyWithOnlinePresenceDTO {

    protected Integer id;
    protected String cultSwitchId;
    protected String code;
    protected String name;
    protected String alternativeName;
    protected String favicon;
    protected String status;
    protected Boolean forTesting;
    protected Boolean isMaster;
    private Boolean isDeleted;
    private Boolean isDeletedBySupplier;
    private Boolean isPermanentlyClosed;
    protected Boolean autoReplenishment;
    protected Boolean showCultSwitchDefaultCancellationRules;
    protected String emailAddressComment;
    protected Boolean emailAddressUnsubscribe;
    protected String vatNumber;
    protected String legalCompanyName;
    protected String commercialRegistrationNumber;
    protected String managingDirector;
    protected String companyOrganisationNumber;
    protected String fax;
    protected String distributorType;

    private OnlinePresence onlinePresence;

    private Set<PropertyAddressDTO> addresses = new HashSet<>();
    private Set<PropertyEmailDTO> emails = new HashSet<>();
    private Set<PropertyMessageDTO> messages = new HashSet<>();

}
