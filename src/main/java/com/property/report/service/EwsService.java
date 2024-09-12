package com.property.report.service;

import com.property.report.common.dto.EwsDto;
import com.property.report.common.rdo.EwsRdo;

public interface EwsService {

    EwsRdo sendEmailByEws(EwsDto ewsDto);

}
