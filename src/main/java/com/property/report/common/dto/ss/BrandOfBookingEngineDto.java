package com.property.report.common.dto.ss;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BrandOfBookingEngineDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;

    @JsonProperty
    @NotEmpty(message = "name cannot be empty")
    @Size(min = 1, max = 255)
    @Schema(description = "name", example = "Booking.com")
    private String name;

}
