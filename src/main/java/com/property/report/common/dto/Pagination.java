package com.property.report.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pagination {

        //total number of records in the system
        @JsonProperty("total")
        public long total;

        //current page-number
        @JsonProperty("page_number")
        public long pageNumber;

        //maximum number of records that can be sent in one page
        @JsonProperty("page_size")
        public long pageSize;

}

