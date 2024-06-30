package com.furqonajiy.nlpostcode.api.service.impl.updatepostcode;

import com.furqonajiy.nlpostcode.api.service.impl.UpdatePostcodeImpl;
import com.furqonajiy.nlpostcode.model.getnlpostcodedistance.Postcode;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@Slf4j
@SpringBootTest
class ServiceTest {
    @InjectMocks
    UpdatePostcodeImpl updatePostcode;

    @Test
    @DisplayName("updatePostcode(1065VL)")
    void update_1065VL() {
        Postcode oldPostCode = updatePostcode.getPostCodeRow("1065VL", TestData.postcodeCoordinate());
        assert oldPostCode.getPostcode().equals("1065VL");
        assert oldPostCode.getLatitude() == 52.36234477;
        assert oldPostCode.getLongitude() == 4.831505362;

        List<Map<String, Object>> updatedNLPostcodeTableCache = updatePostcode.updateNLPostcodeTableCache("1065VL", 5.0, 10.0, TestData.postcodeCoordinate());
        Postcode newPostcode = updatePostcode.getPostCodeRow("1065VL", updatedNLPostcodeTableCache);
        assert newPostcode.getPostcode().equals("1065VL");
        assert newPostcode.getLatitude() == 5.0;
        assert newPostcode.getLongitude() == 10.0;
    }
}
