package com.furqonajiy.nlpostcode.api.utility.distancecalculator;

import com.furqonajiy.nlpostcode.api.utility.DistanceCalculator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Objects;

@Slf4j
@SpringBootTest
public class ServiceTest {
    @InjectMocks
    DistanceCalculator distanceCalculator;

    @Test
    @DisplayName("calculateDistance(6651EH, 1189WK)")
    void calculate_6651_1189() {
        double lat1 = 51.88760463;
        double long1 = 5.597723367;

        double lat2 = 52.25902055;
        double long2 = 4.869899155;

        Double distance = distanceCalculator.calculateDistance(lat1, long1, lat2, long2);
        assert Objects.equals(distance.intValue(), 64);
    }
}
