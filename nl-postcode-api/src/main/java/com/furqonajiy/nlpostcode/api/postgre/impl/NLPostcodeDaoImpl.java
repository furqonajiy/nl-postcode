package com.furqonajiy.nlpostcode.api.postgre.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.furqonajiy.nlpostcode.api.exception.ServiceUnavailableException;
import com.furqonajiy.nlpostcode.api.postgre.NLPostcodeDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

@Slf4j
public class NLPostcodeDaoImpl implements NLPostcodeDao {
    @Autowired
    @Qualifier("nlPostcodeJdbcTemplate")
    private JdbcTemplate nlPostcodeJdbcTemplate;

    @Autowired
    @Qualifier("nlPostcodeObjectMapper")
    private ObjectMapper objectMapper;

    private static final String QUERY_ALL_POSTCODE = "SELECT * FROM postcode_coordinate";

    @Override
    public List<Map<String, Object>> getNLPostcodeList() {
        try {
            List<Map<String, Object>> databaseResponseList = nlPostcodeJdbcTemplate.queryForList(QUERY_ALL_POSTCODE);

            return databaseResponseList;
        } catch (Exception e) {
            log.debug("Exception occurs.", e);

            throw new ServiceUnavailableException("NL PostCode Database", e);
        }
    }

    private static final String QUERY_UPDATE_POSTCODE = "UPDATE postcode_coordinate " +
            "SET latitude = :latitude, longitude = :longitude " +
            "WHERE postcode = :postcode";

    @Override
    public boolean updateNLPostcode(String postcode, Double latitude, Double longitude) {
        try {
            String sqlQuery = QUERY_UPDATE_POSTCODE
                    .replace(":latitude", latitude.toString())
                    .replace(":longitude", longitude.toString())
                    .replace(":postcode", "'" + postcode + "'");

            nlPostcodeJdbcTemplate.execute(sqlQuery);

            return true;
        } catch (Exception e) {
            log.info("Exception occurs.", e);

            throw new ServiceUnavailableException("NL PostCode Database", e);
        }
    }
}
