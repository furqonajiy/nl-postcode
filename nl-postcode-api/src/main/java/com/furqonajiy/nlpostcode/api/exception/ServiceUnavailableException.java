package com.furqonajiy.nlpostcode.api.exception;

import java.text.MessageFormat;

public class ServiceUnavailableException extends GenericException {
    public ServiceUnavailableException(String backendName, Throwable e) {
        super("50000", MessageFormat.format("Cannot connect to {0}", backendName), e.getMessage(), e);
    }
}
