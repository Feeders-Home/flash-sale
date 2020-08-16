package com.feeder.flashsale.service.flashSale;

public enum ErrorCode implements com.feeder.flashsale.exception.ErrorCode {
    NOT_ENOUGH_STOCK(400,10001,"库存不足");

    private final int status;
    private final int code;
    private final String message;
    ErrorCode(int status, int code, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }

    @Override
    public int getStatus() {
        return status;
    }
    @Override
    public String getMessage() {
        return message;
    }
    @Override
    public int getCode() {
        return code;
    }
}
