package com.furqonajiy.nlpostcode.api.service;

import com.furqonajiy.nlpostcode.model.getnlpostcodedistance.Postcode;
import com.furqonajiy.nlpostcode.model.updatenlpostcode.UpdatePostcode;

import java.util.List;
import java.util.Map;

public interface IUpdatePostcodeService {
    List<Map<String, Object>> updateNLPostcodeTableCache(String postcode, Double latitude, Double longitude, List<Map<String, Object>> nlPostcodeTable);

    UpdatePostcode generateResponse(Postcode old, Postcode postcodeB);
}
