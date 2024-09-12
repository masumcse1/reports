package com.property.report.service;

import com.property.report.common.dto.*;
import com.property.report.model.Country;
import com.property.report.model.Property;
import org.springframework.core.io.ByteArrayResource;

import java.util.List;

public interface SupplierService {

     List<CountryDto> getAllCountry(String token);

    List<Property> getPaginatedDataByCountryId(String token, Country countryId);

    SupplierContentPaginated getDataByCountryId(Integer i, Integer countryId);

    PropertyDto getDataByPropertyId(Integer id,String token);

    FreeGoogleBooking getOnlinePresenceById(Integer id);

    void getGermanyData(String token,Country country);

    List<MessageRdo> getScheduledMessageData(String token);

    UploadMediaDto uploadMediaFile(Integer propertyId, ByteArrayResource fileAsResource);

}
