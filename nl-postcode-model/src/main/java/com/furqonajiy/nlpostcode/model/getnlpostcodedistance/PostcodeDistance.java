package com.furqonajiy.nlpostcode.model.getnlpostcodedistance;

import lombok.Data;

@Data
public class PostcodeDistance {
    private Postcode postcodeA;
    private Postcode postcodeB;
    private double distance;
    private String unit;
}
