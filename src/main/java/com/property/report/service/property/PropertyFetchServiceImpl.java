package com.property.report.service.property;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.property.report.common.dto.AccessTokenSupplier;
import com.property.report.common.dto.PropertyDto;
import com.property.report.service.TokenService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class PropertyFetchServiceImpl implements PropertyFetchService {

    @Autowired
    private TokenService tokenService;

    @Value("${supplier.api.url}")
    private String supplierUrl;

    public Page<PropertyDto> getProperties(Pageable pageable) {
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

        log.info("---start data fetch from supplier api--" + pageable.getPageNumber());

        ResponseEntity<?> response = template.exchange(
                apiUrl,
                HttpMethod.GET,
                entity,
                Map.class);

        Map<String, Object> responseBody = (Map) response.getBody();
        Map<String, Object> result = (Map) responseBody.get("result");

        List<PropertyDto> content = new ObjectMapper()
                .convertValue(result.get("content"),
                        new TypeReference<List<PropertyDto>>() {
                        });
        log.info("---done data fetch from supplier api--" + pageable.getPageNumber());
        long totalElements = (Integer) result.get("totalElements");
        return new PageImpl<>(content, pageable, totalElements);
    }

}
