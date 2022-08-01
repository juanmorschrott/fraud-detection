package com.hellolight.frauddetection.domain.service;

import com.hellolight.frauddetection.domain.model.Reading;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.hellolight.frauddetection.ReadingsFixture.getReadings;
import static com.hellolight.frauddetection.domain.service.FraudDetectionMathUtils.obtainReadingsMedians;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class FraudDetectionMathUtilsTest {

    @Test
    public void shouldWorkWithEmptyList() {

        List<Reading> readings = new ArrayList<>();

        Map<String, Double> result = obtainReadingsMedians(readings);

        assertThat(result.size()).isEqualTo(0);
    }

    @Test
    public void shouldCalculateMedian() {

        String clientId = "583ef6329d7b9";
        List<Reading> readings = getReadings();
        Double expectedMedian = 44055.0;

        Map<String, Double> result = obtainReadingsMedians(readings);

        assertThat(result.get(clientId).doubleValue()).isEqualTo(expectedMedian);
    }

}