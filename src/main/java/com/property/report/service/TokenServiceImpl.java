package com.property.report.service;


import com.property.report.common.dto.AccessTokenSupplier;
import com.property.report.exception.InvalidCredentialsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

import static com.property.report.common.constant.MessageConstant.INVALID_CREDENTIALS;


@Service
public class TokenServiceImpl implements TokenService {

    @Value("${supplier.api.url}")
    String supplierUrl;


    @Value("${supplier.id.key}")
    String supplierId;

    @Value("${supplier.secret.key}")
    String supplierSecret;


    Logger logger = LoggerFactory.getLogger(TokenServiceImpl.class);

    @Override
    public AccessTokenSupplier getTokenFromSuppliers(){

        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        for(int j=0; j<3;j++) {
            try {
                ResponseEntity<AccessTokenSupplier> allDataResponse = template.exchange(
                        supplierUrl + "suppliers/get-token?supplierId=" + supplierId + "&supplierSecret=" + supplierSecret,
                        HttpMethod.POST, entity, AccessTokenSupplier.class);

              if (allDataResponse.getStatusCodeValue() == HttpStatus.OK.value()){
                    return Objects.requireNonNull(allDataResponse.getBody());
                }
                else
                    Thread.sleep(1000 * j);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
        throw new InvalidCredentialsException(INVALID_CREDENTIALS);
    }

}
