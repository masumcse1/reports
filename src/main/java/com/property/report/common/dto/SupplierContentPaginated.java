package com.property.report.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SupplierContentPaginated {

    private List<PropertyDto> content ;

    private int totalPages;

    private int totalElements;

    private boolean last;

    private int number;

    private int size;

    private int numberOfElements;

    private boolean first;

    private boolean empty;

}
