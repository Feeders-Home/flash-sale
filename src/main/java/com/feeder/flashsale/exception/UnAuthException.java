package com.feeder.flashsale.exception;

import java.util.Map;

public class UnAuthException extends AppException {
    public UnAuthException(ErrorCode error, Map<String, Object> data) {
        super(error, data);
    }
}
