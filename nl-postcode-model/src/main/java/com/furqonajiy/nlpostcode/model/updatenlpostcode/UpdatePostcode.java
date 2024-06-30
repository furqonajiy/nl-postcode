package com.furqonajiy.nlpostcode.model.updatenlpostcode;

import com.furqonajiy.nlpostcode.model.getnlpostcodedistance.Postcode;
import lombok.Data;

import java.util.List;

@Data
public class UpdatePostcode {
    private Postcode oldPostcode;
    private Postcode newPostcode;
}
