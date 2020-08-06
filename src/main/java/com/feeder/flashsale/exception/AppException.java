package com.feeder.flashsale.exception;

import java.util.Collections;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;
import static org.apache.commons.collections4.MapUtils.isEmpty;

/**
 * the base class for the business exception.
 * <p>
 * from the ecommerce-common package
 */
public abstract class AppException extends RuntimeException {
    private final ErrorCode error;
    private final Map<String, Object> data = newHashMap();

    protected AppException(ErrorCode error) {
        this(error, Collections.emptyMap());
    }

    protected AppException(ErrorCode error, Map<String, Object> data) {
        super(format(error.getCode(), error.getMessage(), data));
        this.error = error;
        if (!isEmpty(data)) {
            this.data.putAll(data);
        }
    }

    protected AppException(ErrorCode code, Map<String, Object> data, Throwable cause) {
        super(format(code.getCode(), code.getMessage(), data), cause);
        this.error = code;
        if (!isEmpty(data)) {
            this.data.putAll(data);
        }
    }

    private static String format(int code, String message, Map<String, Object> data) {
        return String.format("[%d]%s:%s.", code, message, isEmpty(data) ? "" : data.toString());
    }

    public ErrorCode getError() {
        return error;
    }

    public Map<String, Object> getData() {
        return data;
    }
}

