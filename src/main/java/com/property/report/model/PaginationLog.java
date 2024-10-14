package com.property.report.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "pagination_log")
public class PaginationLog {

    @Id
    @Column(name = "id")
    private Integer id = 1;

    @Column(name = "page_number")
    private Integer pageNumber = 0;

    @Column(name = "is_read_all_page")
    private Boolean isReadAllPage ;

}
