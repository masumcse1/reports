package com.property.report.service;


import com.property.report.common.dto.AccessTokenSupplier;

public interface TokenService {

    public AccessTokenSupplier getTokenFromSuppliers();

}
