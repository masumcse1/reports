package com.property.report.service;

import com.property.report.common.dto.ListResultRdo;
import com.property.report.model.Property;

import java.io.Writer;
import java.util.List;

public interface PropertyService {

    Integer addPropertyByCountry(String id);

     void getCsvFile(Writer writer,String id);

     void getWorldCsvFile(Writer writer);

    void updateProperty(Integer propertyId,String token);

    void updateSentMessageProperty(String token);

    ListResultRdo<Integer> getPropertyByCountryAndEmail(Integer page, Integer size, String code);

    ListResultRdo<Integer> getPropertyByCountryAndGoogle(Integer page,Integer size,String code);

}
