package com.furqonajiy.nlpostcode.api.utility;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Getter
@Setter
public class NLPostcodeTableCache {
    private List<Map<String, Object>> nlPostcodeTable;
}
