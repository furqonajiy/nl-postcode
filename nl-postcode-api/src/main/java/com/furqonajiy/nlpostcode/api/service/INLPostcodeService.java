package com.furqonajiy.nlpostcode.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.furqonajiy.nlpostcode.model.getnlpostcodedistance.Postcode;

import java.util.List;
import java.util.Map;

public interface INLPostcodeService<Rq, Rs> {
    Rs process(Rq input) throws JsonProcessingException;
}

