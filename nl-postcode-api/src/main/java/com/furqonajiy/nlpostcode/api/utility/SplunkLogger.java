package com.furqonajiy.nlpostcode.api.utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.furqonajiy.nlpostcode.api.logging.TransactionLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SplunkLogger {
    @Autowired
    private ObjectMapper objectMapper;

    public void info(TransactionLog transactionLog) {
        try {
            log.info(objectMapper.writeValueAsString(transactionLog));
        } catch (Exception e) {
            log.info("Error Splunk Logger", e);
        }
    }

    public void error(TransactionLog transactionLog) {
        try {
            log.error(objectMapper.writeValueAsString(transactionLog));
        } catch (Exception e) {
            log.error("Error Splunk Logger", e);
        }
    }
}
