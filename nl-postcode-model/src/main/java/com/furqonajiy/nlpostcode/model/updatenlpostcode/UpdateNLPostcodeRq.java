package com.furqonajiy.nlpostcode.model.updatenlpostcode;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UpdateNLPostcodeRq {
    @NotBlank
    private String postcode;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;
}
