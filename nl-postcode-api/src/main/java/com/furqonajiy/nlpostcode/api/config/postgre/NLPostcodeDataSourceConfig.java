package com.furqonajiy.nlpostcode.api.config.postgre;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

@Configuration
public class NLPostcodeDataSourceConfig {
    @Bean("nlPostcodeDataSourceProperties")
    @Primary
    @ConfigurationProperties("nl-postcode-db.datasource")
    public DataSourceProperties nlPostcodeDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean("nlPostcodeDataSource")
    @Primary
    @ConfigurationProperties(prefix = "nl-postcode-db.datasource")
    public DataSource nlPostcodeDataSource() {
        return nlPostcodeDataSourceProperties()
                .initializeDataSourceBuilder()
                .type(SimpleDriverDataSource.class)
                .build();
    }
}