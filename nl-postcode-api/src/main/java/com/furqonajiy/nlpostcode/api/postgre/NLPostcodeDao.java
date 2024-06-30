package com.furqonajiy.nlpostcode.api.postgre;

import java.util.List;
import java.util.Map;

public interface NLPostcodeDao {
    List<Map<String, Object>> getNLPostcodeList();

    boolean updateNLPostcode(String postcode, Double latitude, Double longitude);
}
