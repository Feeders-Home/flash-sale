package com.feeder.flashsale.exception;

import org.apache.logging.log4j.util.Strings;

import static com.feeder.flashsale.exception.CommonErrorCode.SYSTEM_ERROR;
import static com.google.common.collect.ImmutableMap.of;

/**
 * unknown exception.
 */
public class SystemException extends AppException {

    public SystemException(Throwable cause) {
        super(SYSTEM_ERROR, of("detail", getMsgOf(cause)), cause);
    }

    private static String getMsgOf(Throwable cause) {

        String msg = cause.getMessage();
        return Strings.isBlank(msg) ? cause.toString() : msg;
    }
}
