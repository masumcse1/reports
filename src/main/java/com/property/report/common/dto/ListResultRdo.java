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
public class ListResultRdo<T> {

    private List<T> data;

    private Pagination pagination;

    public ListResultRdo<T> getListResultRdo(List<T> data, long pageNumber, long pageSize, long totalCount) {

        Pagination paginationInstance = new Pagination();
        paginationInstance.total = totalCount;
        paginationInstance.pageNumber = pageNumber;
        paginationInstance.pageSize = data.size();

        this.data = data;
        this.pagination = paginationInstance;

        return this;
    }

}