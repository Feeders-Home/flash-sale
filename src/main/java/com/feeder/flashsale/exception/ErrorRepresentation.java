package com.feeder.flashsale.exception;

import java.time.Instant;
import java.util.Map;

import lombok.Data;
import org.springframework.lang.Nullable;

import static com.google.common.collect.Maps.newHashMap;
import static org.apache.commons.collections4.MapUtils.isEmpty;

/**
 * the response error.
 */
public class ErrorRepresentation {
    private final ErrorDetail error;

    ErrorRepresentation(AppException ex, String path) {
        ErrorCode error = ex.getError();
        this.error = new ErrorDetail(
                error.getCode(), error.getStatus(), error.getMessage(), path, ex.getData()
        );
    }

    ErrorRepresentation(ErrorDetail error) {
        this.error = error;
    }

    ErrorDetail getError() {
        return this.error;
    }

    @Data
    public static class ErrorDetail {
        private int status;
        private int code;
        private String message;
        private String path;
        private Instant timestamp;
        private Map<String, Object> data = newHashMap();

        ErrorDetail(int code, int status, String message, String path,
            @Nullable Map<String, Object> data) {
            this.code = code;
            this.status = status;
            this.message = message;
            this.path = path;
            this.timestamp = Instant.now();
            if (!isEmpty(data)) {
                this.data.putAll(data);
            }
        }
    }
}

