package com.shand.banksystem.services.utils;


import com.shand.banksystem.dto.base.BasePageRequest;
import com.shand.banksystem.dto.base.BaseResponse;

public class ValidationUtils {
    private ValidationUtils(){}
    public static String validatePageRequest(BasePageRequest request) {
        if (request == null) {
            return "Request is null.";
        }
        if (request.getPage() < 0) {
            return "Page should be positive";
        }
        if (request.getSize() < 0) {
            return "Size should be positive";
        }
        return null;
    }
}
