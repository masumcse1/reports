package com.property.report.repository.custom;

import com.property.report.common.dto.ListResultRdo;
import com.property.report.model.Country;

import java.util.List;

public interface PropertyCustomRepository {

    ListResultRdo<Integer> getPropertyByCountryAndEmail(Integer pageNo, Integer pageSize, Country country);

    ListResultRdo<Integer> getPropertyByCountryAndGoogle(Integer pageNo, Integer pageSize,Country country);

    ListResultRdo<Integer> getPropertyForGoogleParser(Integer pageNo, Integer pageSize, Country country);
}
