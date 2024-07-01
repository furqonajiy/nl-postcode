package com.furqonajiy.nlpostcode.api.service.impl.getpostcodedistance;

import com.furqonajiy.nlpostcode.api.service.impl.GetPostcodeDistanceImpl;
import com.furqonajiy.nlpostcode.model.getnlpostcodedistance.Postcode;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class ServiceTest {
    @InjectMocks
    GetPostcodeDistanceImpl getPostcodeDistance;

    @Test
    @DisplayName("getPostcode(1189WK)")
    void get_1189WK() {
        Postcode selectedPostCode = getPostcodeDistance.getPostCodeRow("1189WK", TestData.postcodeCoordinate());
        assert selectedPostCode.getPostcode().equals("1189WK");
        assert selectedPostCode.getLatitude() == 52.25902055;
        assert selectedPostCode.getLongitude() == 4.869899155;
    }
}
