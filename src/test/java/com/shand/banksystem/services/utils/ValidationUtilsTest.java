package com.shand.banksystem.services.utils;

import com.shand.banksystem.dto.base.BasePageRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ValidationUtilsTest {

    @Test
    void validatePageRequest() {
        assertEquals("Request is null.", ValidationUtils.validatePageRequest(null));

        assertEquals("Page should be positive",
                ValidationUtils.validatePageRequest(BasePageRequest.builder().page(-1).build()));

        assertEquals("Size should be positive",
                ValidationUtils.validatePageRequest(BasePageRequest.builder().size(-1).build()));

        assertNull(ValidationUtils.validatePageRequest(BasePageRequest.builder().build()));
    }
}