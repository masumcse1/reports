package com.property.report.repository;

import com.property.report.model.Country;
import com.property.report.model.Property;
import com.property.report.repository.custom.PropertyCustomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property,Integer>, PropertyCustomRepository {

    List<Property> findByCountry(Country country);

    Property findByPropertyId(Integer id);

    Page<Property> findByLatitudeOrLongitude(Double latitude, Double longitude, PageRequest pageRequest);

    Page<Property> findByLatitudeOrLongitudeOrPropertyWebsiteURL(Double latitude, Double longitude,String websiteURL, PageRequest pageRequest);

    @Query("SELECT p FROM Property p WHERE p.latitude IS NOT NULL OR p.longitude IS NOT NULL")
    Page<Property> findByLatitudeOrLongitudeNotNull(Pageable pageable);

    Page<Property> findByForTestingOrIsDeleted(Double forTesting, Boolean isDeleted, PageRequest pageRequest);

    Page<Property> findByCountry(Country country, PageRequest pageRequest);

    Page<Property> findByGoogleMetaSearchLinksUrlOrGoogleMapsWithGoogleBusinessPlacesIdScreenshotUrl
            (String googlemeta, String businessUrl, PageRequest pageRequest);


}

