package com.property.report.service.ss;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.property.report.common.dto.AccessTokenSupplier;
import com.property.report.model.PropertyEntity;
import com.property.report.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class PropertyFetchServiceImpl implements PropertyFetchService {

    @Autowired
    private TokenService tokenService;

    @Value("${supplier.api.url}")
    private String supplierUrl;

    public Page<PropertyEntity> getProperties(Pageable pageable) {
        AccessTokenSupplier tokenFromSuppliers = tokenService.getTokenFromSuppliers();
        String token = tokenFromSuppliers.getAccessToken();

        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        String apiUrl = supplierUrl
                + "properties/fetch-property-with-online-presence/paging?page="
                + pageable.getPageNumber()
                + "&size=" + pageable.getPageSize();

        ResponseEntity<?> response = template.exchange(
                apiUrl,
                HttpMethod.GET,
                entity,
                Map.class);

        Map<String, Object> responseBody = (Map) response.getBody();
        Map<String, Object> result = (Map) responseBody.get("result");

        List<PropertyEntity> content = new ObjectMapper()
                .convertValue(result.get("content"),
                        new TypeReference<List<PropertyEntity>>() {
                        });

        long totalElements = (Integer) result.get("totalElements");
        return new PageImpl<>(content, pageable, totalElements);
    }

}
