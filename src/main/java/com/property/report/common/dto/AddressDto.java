package com.property.report.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

   private Integer id;

   private String  addressType;

   private String addressLine;

   private String cityName;

   private CountryDto country;

}
