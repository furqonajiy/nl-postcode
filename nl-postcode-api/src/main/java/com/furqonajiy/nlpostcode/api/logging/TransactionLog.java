package com.furqonajiy.nlpostcode.api.logging;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransactionLog {
    private final LocalDateTime timestamp;
    private final long processTime;
    private final Integer httpStatusCode;
    private final Object requestPayload;
    private final Object responsePayload;

    public TransactionLog(long startMillis, Integer httpStatusCode, Object requestPayload, Object responsePayload) {
        this.timestamp = LocalDateTime.now();
        this.processTime = System.currentTimeMillis() - startMillis;
        this.httpStatusCode = httpStatusCode;
        this.requestPayload = requestPayload;
        this.responsePayload = responsePayload;
    }
}
