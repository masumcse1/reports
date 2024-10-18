package com.property.report.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PropertyIdentifier {

    @JsonProperty
    @Min(value = 1_000_000, message = "The property id should be greater than or equal to 1,000,000.")
    @Schema(description = "Room DB Property Id of the property", example = "1000040")
    private Integer propertyId;

    @JsonProperty
    @Size(min = 1)
    @Schema(description = "Identifier for the property, for specified source", example = "40")
    private String identifier;

    @JsonProperty
    @Schema(description = "Url, for specified source", example = "https://www.booking.com/hotel.html")
    private String url;

    @JsonProperty
    private IdentifierSource source;

}
