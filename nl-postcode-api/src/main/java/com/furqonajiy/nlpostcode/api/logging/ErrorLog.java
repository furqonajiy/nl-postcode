package com.furqonajiy.nlpostcode.api.logging;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@Getter
@Setter
public class ErrorLog extends TransactionLog {
    private final String errorCode;
    private final String errorMessage;
    private final String faultDetails;

    public ErrorLog(long startMillis, Integer httpStatusCode, String errorCode, String errorMessage, Object requestPayload, Object responsePayload, Throwable e) {
        super(startMillis, httpStatusCode, requestPayload, responsePayload);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.faultDetails = Arrays.toString(e.getStackTrace());
    }
}
