package com.property.report.repository;

import com.property.report.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country,Integer> {

    Country findByCode(String code);
}
