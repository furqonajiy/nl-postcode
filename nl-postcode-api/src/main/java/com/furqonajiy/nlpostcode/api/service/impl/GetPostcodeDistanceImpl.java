package com.furqonajiy.nlpostcode.api.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.furqonajiy.nlpostcode.api.service.IGetPostcodeDistanceService;
import com.furqonajiy.nlpostcode.api.service.INLPostcodeService;
import com.furqonajiy.nlpostcode.api.utility.DistanceCalculator;
import com.furqonajiy.nlpostcode.api.utility.NLPostcodeTableCache;
import com.furqonajiy.nlpostcode.model.getnlpostcodedistance.GetNLPostcodeDistanceRq;
import com.furqonajiy.nlpostcode.model.getnlpostcodedistance.Postcode;
import com.furqonajiy.nlpostcode.model.getnlpostcodedistance.PostcodeDistance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class GetPostcodeDistanceImpl extends GenericPostcodeImpl implements INLPostcodeService<GetNLPostcodeDistanceRq, PostcodeDistance>, IGetPostcodeDistanceService {
    @Autowired
    private NLPostcodeTableCache nlPostcodeTableCache;

    @Autowired
    private DistanceCalculator distanceCalculator;

    @Autowired
    @Qualifier("nlPostcodeObjectMapper")
    private ObjectMapper objectMapper;

    @Override
    public PostcodeDistance process(GetNLPostcodeDistanceRq request) {
        log.debug("Process Get NL Postcode Distance");

        List<Map<String, Object>> nlPostcodeTable = nlPostcodeTableCache.getNlPostcodeTable();

        // Initialize Postcode A Parameter
        Postcode postcodeA = getPostCodeRow(request.getPostcodeA(), nlPostcodeTable);
        double latA = postcodeA.getLatitude();
        double longA = postcodeA.getLongitude();

        // Initialize Postcode B Parameter
        Postcode postcodeB = getPostCodeRow(request.getPostcodeB(), nlPostcodeTable);
        double latB = postcodeB.getLatitude();
        double longB = postcodeB.getLongitude();

        // Calculate Distance
        double distance = distanceCalculator.calculateDistance(latA, longA, latB, longB);

        return generateResponse(postcodeA, postcodeB, distance);
    }

    @Override
    public PostcodeDistance generateResponse(Postcode postcodeA, Postcode postcodeB, double distance) {
        log.debug("Generate Response");

        PostcodeDistance postcodeDistance = new PostcodeDistance();
        postcodeDistance.setPostcodeA(postcodeA);
        postcodeDistance.setPostcodeB(postcodeB);
        postcodeDistance.setDistance(distance);
        postcodeDistance.setUnit("km");

        return postcodeDistance;
    }
}
