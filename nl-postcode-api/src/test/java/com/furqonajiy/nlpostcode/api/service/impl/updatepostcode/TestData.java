package com.furqonajiy.nlpostcode.api.service.impl.updatepostcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestData {
    public static List<Map<String, Object>> postcodeCoordinate() {
        List<Map<String, Object>> postcodeCoordinate = new ArrayList<>();
        postcodeCoordinate.add(new HashMap<>() {{
            put("id", 1);
            put("postcode", "6651EH");
            put("latitude", 51.88760463);
            put("longitude", 5.597723367);
        }});
        postcodeCoordinate.add(new HashMap<>() {{
            put("id", 2);
            put("postcode", "1189WK");
            put("latitude", 52.25902055);
            put("longitude", 4.869899155);
        }});
        postcodeCoordinate.add(new HashMap<>() {{
            put("id", 3);
            put("postcode", "1065VL");
            put("latitude", 52.36234477);
            put("longitude", 4.831505362);
        }});

        return postcodeCoordinate;
    }
}
