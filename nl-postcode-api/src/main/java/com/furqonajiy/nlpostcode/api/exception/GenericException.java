package com.furqonajiy.nlpostcode.api.exception;

import lombok.Data;

@Data
public class GenericException extends RuntimeException {
    private String statusCode;
    private String statusDesc;
    private Throwable e;

    protected GenericException(String statusCode, String statusDesc, String exceptionMessage, Throwable e) {
        super(exceptionMessage, e);

        this.statusCode = statusCode;
        this.statusDesc = statusDesc;
    }
}
