package com.furqonajiy.nlpostcode.api.service.impl;

import com.furqonajiy.nlpostcode.model.getnlpostcodedistance.Postcode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class GenericPostcodeImpl {
    public Postcode getPostCodeRow(String postcodeRq, List<Map<String, Object>> nlPostcodeTable) {
        log.debug("Get Post Code Parameter");

        for (Map<String, Object> nlPostCodeTableRow : nlPostcodeTable) {
            String postcodeValue = nlPostCodeTableRow.get("postcode").toString();

            if (postcodeValue.equalsIgnoreCase(postcodeRq)) {
                Postcode postcodeObject = new Postcode();
                postcodeObject.setPostcode(postcodeRq);
                postcodeObject.setLatitude((double) nlPostCodeTableRow.get("latitude"));
                postcodeObject.setLongitude((double) nlPostCodeTableRow.get("longitude"));

                return postcodeObject;
            }
        }

        return null;
    }
}
