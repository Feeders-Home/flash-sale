package com.feeder.flashsale.exception;

public enum CommonErrorCode implements ErrorCode {
    SYSTEM_ERROR(500, "系统错误"),
    REQUEST_VALIDATION_FAILED(400, "请求数据格式验证失败"),
    UNAUTHORIZED(403, "权限验证失败"),
    NOT_FOUND(404, "资源找不到");
    private int status;
    private String message;

    CommonErrorCode(int status, String message) {
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
        return status;
    }
}
