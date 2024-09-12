package com.property.report.common.rdo;


import com.property.report.common.dto.EwsEmail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EwsRdo {

    private String message;

    private EwsEmail email;

}
