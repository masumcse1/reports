package com.property.report.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "email_type")
public class EmailType {

    @Id
    private Integer id;

    private String code;

    private String name;

}
