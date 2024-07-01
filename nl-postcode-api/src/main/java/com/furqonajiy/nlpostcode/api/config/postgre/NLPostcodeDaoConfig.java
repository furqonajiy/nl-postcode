package com.furqonajiy.nlpostcode.api.config.postgre;

import com.furqonajiy.nlpostcode.api.postgre.NLPostcodeDao;
import com.furqonajiy.nlpostcode.api.postgre.impl.NLPostcodeDaoImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class NLPostcodeDaoConfig {
    @Value("${nl-postcode-db.query.timeout-second}")
    private int nlPostcodeQueryConnectionTimeoutMS;

    @Bean("nlPostcodeJdbcTemplate")
    public JdbcTemplate nlPostcodeJdbcTemplate(@Qualifier("nlPostcodeDataSource") DataSource dataSource) {
        JdbcTemplate nlPostcodeJdbcTemplate = new JdbcTemplate(dataSource);
        nlPostcodeJdbcTemplate.setQueryTimeout(this.nlPostcodeQueryConnectionTimeoutMS);

        return nlPostcodeJdbcTemplate;
    }

    @Bean("nlPostcodeDao")
    public NLPostcodeDao nlPostcodeDao(){
        return new NLPostcodeDaoImpl();
    }
}
