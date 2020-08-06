package com.feeder.flashsale.exception;

/**
 * the base interface for the detail error.
 * <p>
 * from the ecommerce-common package
 */
public interface ErrorCode {
    int getStatus();
    int getCode();
    String getMessage();
}
