package com.property.report.model;

import com.property.report.common.dto.LogPropertyActivityQdo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

import static com.property.report.common.util.DateTimeUtil.convertStringToTimeStamp;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LogPropertyActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private Timestamp dateOfRequest;


    private Timestamp dateOfResponse;

    @ManyToOne
    @JoinColumn(name = "property_id", referencedColumnName = "id",nullable = false)
    private Property property;


    private Integer scriptId;


    @Column(length = 65000,columnDefinition = "TEXT")
    private String responseBody;


    private boolean success;


    private String server;


    public LogPropertyActivity(LogPropertyActivityQdo logPropertyActivityQdo,Property property) {
     this.responseBody=logPropertyActivityQdo.getResponseBody();
     this.success= logPropertyActivityQdo.isSuccess();
     this.scriptId= logPropertyActivityQdo.getScriptId();
     this.server= logPropertyActivityQdo.getServer();
     this.property=property;
     this.dateOfRequest=convertStringToTimeStamp(logPropertyActivityQdo.getDateOfRequest());
     this.dateOfResponse=convertStringToTimeStamp(logPropertyActivityQdo.getDateOfResponse());
    }

}
