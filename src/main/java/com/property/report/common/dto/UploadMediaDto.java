package com.property.report.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UploadMediaDto {

    private Integer id;

    private Integer propertyId;

    private String url;

    private boolean isMain;

    private boolean isLogo;

}
