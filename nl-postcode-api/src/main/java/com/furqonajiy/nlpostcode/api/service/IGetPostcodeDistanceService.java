package com.furqonajiy.nlpostcode.api.service;

import com.furqonajiy.nlpostcode.model.getnlpostcodedistance.PostcodeDistance;
import com.furqonajiy.nlpostcode.model.getnlpostcodedistance.Postcode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface IGetPostcodeDistanceService {
    PostcodeDistance generateResponse(Postcode postcodeA, Postcode postcodeB, double distance);
}
