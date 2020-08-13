package com.feeder.flashsale.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerException extends RuntimeException{

    private int errorCode;
    private String msg;

}
