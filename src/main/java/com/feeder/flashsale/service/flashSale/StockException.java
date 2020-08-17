package com.feeder.flashsale.service.flashSale;

import java.util.Map;

import com.feeder.flashsale.exception.AppException;
import com.feeder.flashsale.exception.ErrorCode;

public class StockException extends AppException {

    public StockException(ErrorCode error, Map<String, Object> data) {
        super(error, data);
    }
}
