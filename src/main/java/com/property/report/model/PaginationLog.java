package com.property.report.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "pagination_log")
public class PaginationLog {

    @Id
    private Integer id = 1;

    private Integer pageNumber = 0;

}
