package com.property.report.common.dto.ss;

import com.fasterxml.jackson.annotation.*;
import com.property.report.model.enums.SocialMediaNetwork;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SocialMedia {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Min(1)
    @Schema(description = "ID", example = "12")
    private Integer id;

    //	@JsonProperty(required = true)
    //	@Min(1_000_000)
    //	@Schema(description = "Property ID", example = "10000006")
    //	private Integer propertyId;

    @JsonProperty(required = true, defaultValue = "INSTAGRAM")
    @Schema(description = "Social media network", example = "INSTAGRAM")
    @JsonSetter(nulls = Nulls.SKIP)
    private SocialMediaNetwork network;

    @JsonProperty(required = true)
    @Size(min = 1, max = 255)
    @Schema(description = "profile")
    private String profile;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(description = "Last update")
    private Timestamp lastUpdate;

}
