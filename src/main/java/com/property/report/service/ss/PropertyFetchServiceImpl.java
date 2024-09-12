package com.property.report.service.ss;

import com.property.report.common.dto.ss.PropertyWithOnlinePresenceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class PropertyFetchServiceImpl implements PropertyFetchService {

    @Autowired
    private RestTemplate restTemplate;


    // Fetch data from external API
    public Page<PropertyWithOnlinePresenceDTO> getPropertiesPageWise(String url, Pageable pageable) {
        String apiUrl = url + "?page=" + pageable.getPageNumber() + "&size=" + pageable.getPageSize();

        ResponseEntity<PageImpl<PropertyWithOnlinePresenceDTO>> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<PageImpl<PropertyWithOnlinePresenceDTO>>() {}
        );

        PageImpl<PropertyWithOnlinePresenceDTO> data = response.getBody();
        return data;
    }

}
