package com.property.report.service;

import com.property.report.common.dto.EwsDto;
import com.property.report.common.rdo.EwsRdo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
@Slf4j
public class EwsServiceImpl implements EwsService{

    @Value("${ews.api.token}")
    String token;

    @Value("${ews.api.host}")
    String ewsUrl;

    @Override
    public EwsRdo sendEmailByEws(EwsDto ewsDto){

        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<EwsDto> entity = new HttpEntity<>(ewsDto, headers);
        for (int j = 0; j < 3; j++) {
            //get data from supplier API
            try {
                ResponseEntity<EwsRdo> allDataResponse = template.exchange(
                        ewsUrl + "/send?token=" + token,
                        HttpMethod.POST, entity, new ParameterizedTypeReference<EwsRdo>() {
                        });
                if (allDataResponse.getStatusCodeValue() == HttpStatus.OK.value())
                    return Objects.requireNonNull(allDataResponse.getBody());
                else
                    Thread.sleep(1000 * j);

            } catch (Exception e) {
                log.error(e.getMessage(),e);
            }
        }
        return null;
    }

}
