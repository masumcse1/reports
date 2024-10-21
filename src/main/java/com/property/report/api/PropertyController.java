package com.property.report.api;

import com.property.report.common.dto.AccessTokenSupplier;
import com.property.report.common.dto.ListResultRdo;
import com.property.report.common.dto.PropertyDataDto;
import com.property.report.service.CountryService;
import com.property.report.service.EwsService;
import com.property.report.service.PropertyService;
import com.property.report.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static com.property.report.common.constant.MessageConstant.ACCESS_TOKEN;
import static com.property.report.common.constant.MessageConstant.ADDITION_SUCCESS;
import static com.property.report.common.util.DateTimeUtil.convertTimeStampToString;

@RestController
@Slf4j
public class PropertyController {

    @Autowired
    TokenService tokenService;

    @Autowired
    CountryService countryService;

    @Autowired
    PropertyService propertyService;

    @Autowired
    EwsService ewsService;

    @Value("${app.api.key}")
    private String apiKey;

    @GetMapping("/api/v1/country")
    public void saveCountry() {

        try {
            AccessTokenSupplier tokenFromSuppliers = tokenService.getTokenFromSuppliers();

            countryService.saveAllCountry(tokenFromSuppliers.getAccessToken());
        } catch (Exception e) {
            log.error(e + " ");
        }
    }

    @GetMapping("/api/v1/property")
    public Integer savePropertyById(@RequestHeader(value = "api-key", required = false) String apiKey,
                                    @RequestParam(value = "code") String id) {

        if (!this.apiKey.equals(apiKey)) {
            return 0;
        }
        try {
            ACCESS_TOKEN = tokenService.getTokenFromSuppliers().getAccessToken();
            return propertyService.addPropertyByCountry(id);

        } catch (Exception e) {
            log.error(e.getMessage() + " ");
            return 0;
        }
    }


    @Operation(summary = "Get csv file for a country by country Code",
            description = "Get csv file for a country by country Code. Please add country code in capital letters.Country code as per ISO 3166-1 alpha-2")
    @GetMapping("/api/v1/property-csv")
    public void countryCSV(@RequestParam(value = "country_code") String code,
                           HttpServletResponse servletResponse) throws IOException {


        try {
            ACCESS_TOKEN = tokenService.getTokenFromSuppliers().getAccessToken();
            servletResponse.setContentType("text/csv");
            servletResponse.setCharacterEncoding("UTF-8");
            servletResponse.addHeader("Content-Disposition", "attachment; filename=\"" + convertTimeStampToString() + code + ".csv\"");

            propertyService.getCsvFile(servletResponse.getWriter(), code);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Operation(summary = "Get csv file for a the world",
            description = "Get count for all the fields in a csv file for the world.Please provide api-key.")
    @GetMapping("/api/v1/world-csv")
    public void worldCSV(@RequestHeader(value = "api-key", required = false) String apiKey,
                         HttpServletResponse servletResponse) {

        if (!this.apiKey.equals(apiKey)) {
            return;
        }

        try {
            ACCESS_TOKEN = tokenService.getTokenFromSuppliers().getAccessToken();
            servletResponse.setContentType("text/csv");
            servletResponse.setCharacterEncoding("UTF-8");
            servletResponse.addHeader("Content-Disposition", "attachment; filename=\"" + convertTimeStampToString() + "_world.csv\"");

            propertyService.getWorldCsvFile(servletResponse.getWriter());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @PostMapping("/api/v1/update-property")
    public void update(@RequestBody PropertyDataDto propertyDataDto) {

        log.info("Property Id: ={} Table Name={}", propertyDataDto.getPropertyId(), propertyDataDto.getTableName());
        ACCESS_TOKEN = tokenService.getTokenFromSuppliers().getAccessToken();
        propertyService.updateProperty(propertyDataDto.getPropertyId(), ACCESS_TOKEN);

    }

    @Operation(summary = "Delete Property",
            description = "Delete Property if not found in Supplier DB")
    @DeleteMapping("/api/v1/delete-property")
    public void delete(@RequestBody PropertyDataDto propertyDataDto) {

        log.info("Property Id: ={} Table Name={}", propertyDataDto.getPropertyId(), propertyDataDto.getTableName());
        ACCESS_TOKEN = tokenService.getTokenFromSuppliers().getAccessToken();
        propertyService.deleteProperty(propertyDataDto.getPropertyId(), ACCESS_TOKEN);

    }

    @GetMapping("/api/v1/test-email")
    public void sendTestemail(HttpServletResponse servletResponse) {

        ACCESS_TOKEN = tokenService.getTokenFromSuppliers().getAccessToken();
        try {
            ACCESS_TOKEN = tokenService.getTokenFromSuppliers().getAccessToken();
            servletResponse.setContentType("text/csv");
            servletResponse.setCharacterEncoding("UTF-8");
            servletResponse.addHeader("Content-Disposition", "attachment; filename=\"" + convertTimeStampToString() + "_world.csv\"");

            propertyService.getWorldCsvFile(servletResponse.getWriter());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Operation(summary = "Update sent email details",
            description = "We can update all the details for the sent emails.")
    @GetMapping("/api/v1/sent-message")
    public String updateSentMessageDetails(@RequestHeader(value = "api-key", required = false) String apiKey) {

        if (!this.apiKey.equals(apiKey)) {
            return "";
        }
        ACCESS_TOKEN = tokenService.getTokenFromSuppliers().getAccessToken();

        propertyService.updateSentMessageProperty(ACCESS_TOKEN);

        return ADDITION_SUCCESS;

    }

    @Operation(summary = "Update sent email details",
            description = "We can update all the details for the sent emails.")
    @GetMapping("/api/v1/country-google/{code}")
    public ResponseEntity<ListResultRdo<Integer>> getPropertyByCountryAndGoogle(@RequestHeader(value = "api-key", required = false) String apiKey,
                                                                                @RequestParam("size") Integer size, @RequestParam("page") Integer page,
                                                                                @PathVariable("code") String code) {

        if (!this.apiKey.equals(apiKey)) {
            return new ResponseEntity<ListResultRdo<Integer>>(new ListResultRdo<Integer>(), HttpStatus.UNAUTHORIZED);
        }

        ListResultRdo<Integer> propertyByCountryAndGoogle = propertyService.getPropertyByCountryAndGoogle(page, size, code);

        return new ResponseEntity<ListResultRdo<Integer>>(propertyByCountryAndGoogle, HttpStatus.OK);

    }

    @Operation(summary = "Update sent email details",
            description = "We can update all the details for the sent emails.")
    @GetMapping("/api/v1/country-email/{code}")
    public ResponseEntity<ListResultRdo<Integer>> getPropertyByCountryAndEmail(@RequestHeader(value = "api-key", required = false) String apiKey,
                                                                               @RequestParam("size") Integer size, @RequestParam("page") Integer page,
                                                                               @PathVariable("code") String code) {

        if (!this.apiKey.equals(apiKey)) {
            return new ResponseEntity<ListResultRdo<Integer>>(new ListResultRdo<Integer>(), HttpStatus.UNAUTHORIZED);
        }

        ListResultRdo<Integer> propertyByCountryAndGoogle = propertyService.getPropertyByCountryAndEmail(page, size, code);

        return new ResponseEntity<ListResultRdo<Integer>>(propertyByCountryAndGoogle, HttpStatus.OK);

    }

}
