package com.furqonajiy.nlpostcode.api.config.postgre;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.furqonajiy.nlpostcode.api.postgre.NLPostcodeDao;
import com.furqonajiy.nlpostcode.api.utility.NLPostcodeTableCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

@Slf4j
@Configuration
public class NLPostcodeTableCacheConfig {
    @Autowired
    @Qualifier("nlPostcodeDao")
    private NLPostcodeDao nlPostcodeDao;

    @Autowired
    private NLPostcodeTableCache nlPostcodeTableCache;

    @Autowired
    @Qualifier("nlPostcodeObjectMapper")
    private ObjectMapper objectMapper;

    @EventListener
    public void contextStarted(ContextRefreshedEvent context) {
        log.info("Context refreshed event triggered");
        nlPostcodeTableCache.setNlPostcodeTable(nlPostcodeDao.getNLPostcodeList());
    }
}
