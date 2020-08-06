package com.feeder.flashsale.exception;

import java.util.Map;

/**
 * invalid request exception.
 */
public class RequestValidationException extends AppException {
    public RequestValidationException(Map<String, Object> data) {
        super(CommonErrorCode.REQUEST_VALIDATION_FAILED, data);
    }
}
