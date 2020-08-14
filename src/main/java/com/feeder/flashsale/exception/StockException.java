package com.feeder.flashsale.exception;

import java.util.Map;

public class StockException extends AppException{

    public StockException(ErrorCode error, Map<String, Object> data) {
        super(error, data);
    }
}
