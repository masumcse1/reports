package com.property.report.service;

import com.property.report.common.dto.CountryDto;
import com.property.report.model.Country;
import com.property.report.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.property.report.common.constant.MessageConstant.ADDITION_SUCCESS;

@Service
public class CountryServiceImpl implements CountryService{

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    SupplierService supplierService;


    @Override
    public String saveAllCountry(String token) {

        List<CountryDto> allCountry = supplierService.getAllCountry(token);

        List<Country> countryList = allCountry.stream().map(Country::new).toList();

        countryRepository.saveAll(countryList);

        return ADDITION_SUCCESS;
    }
}
