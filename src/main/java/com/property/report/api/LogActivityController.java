package com.property.report.api;

import com.property.report.common.dto.LogPropertyActivityQdo;
import com.property.report.service.LogPropertyActivityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import static com.property.report.common.constant.MessageConstant.ADDITION_SUCCESS;

@RestController
@Slf4j
public class LogActivityController {


    @Autowired
    LogPropertyActivityService logPropertyActivityService;

    @Value("${app.api.key}")
    private String apiKey;


    @PostMapping("/api/v1/log-property")
    public ResponseEntity<String> logPropertyActivity(@RequestBody LogPropertyActivityQdo logPropertyActivityQdo,
                                                      @RequestHeader(value = "api-key", required = false) String apiKey) {

        if (!this.apiKey.equals(apiKey)) {
            return new ResponseEntity<String>("", HttpStatus.UNAUTHORIZED);
        }
        try {
            logPropertyActivityService.logPropertyActivity(logPropertyActivityQdo);
            return new ResponseEntity<String>(ADDITION_SUCCESS, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<String>("", HttpStatus.NO_CONTENT);
        }
    }
}
