package com.property.report.service.ss;

import com.property.report.common.dto.ss.PropertyWithOnlinePresenceDTO;
import com.property.report.repository.ss.PropertyEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PropertyEntityServiceImpl implements PropertyEntityService{


    @Autowired
    private PropertyEntityRepository propertyRepository;



    public void save(List<PropertyWithOnlinePresenceDTO> properties){

        //convert

        //save directly to db .
      //  propertyRepository.saveAll();

    }



}
