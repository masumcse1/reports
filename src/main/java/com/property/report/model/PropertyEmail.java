package com.property.report.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class PropertyEmail {
    @Id
    private Integer id;

    private String email;
    private String emailType;
    private String status;
}