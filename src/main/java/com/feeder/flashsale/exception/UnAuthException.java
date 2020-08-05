package com.feeder.flashsale.exception;

import java.util.Map;

/**
 * @author zenk.zhang
 * @createdAt 2019-11-14 18:11:33
 */
public class UnAuthException extends AppException {
    public UnAuthException(ErrorCode error, Map<String, Object> data) {
        super(error, data);
    }
}
