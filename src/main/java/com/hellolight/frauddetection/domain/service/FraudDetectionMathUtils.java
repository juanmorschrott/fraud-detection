package com.hellolight.frauddetection.domain.service;

import com.google.common.math.Quantiles;
import com.hellolight.frauddetection.domain.model.Reading;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.averagingLong;
import static java.util.stream.Collectors.groupingBy;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FraudDetectionMathUtils {

    /**
     * This method groups the List<Reading> in a Map<String, Double> with the Reading::getClientId as key and
     * the average of Reading::getValue as value.
     *
     * @param readings List of readings
     * @return Map<String, Double>
     */
    public static Map<String, Double> obtainReadingsMeans(final List<Reading> readings) {

        return Optional.ofNullable(readings)
                .orElseGet(ArrayList::new)
                .stream()
                .collect(groupingBy(Reading::getClientId, averagingLong(Reading::getValue)));
    }

    /**
     * This method groups the List<Reading> in a Map<String, Double> with the Reading::getClientId as key and
     * the median of Reading::getValue as value.
     *
     * @param readings List of readings
     * @return Map<String, Double>
     */
    public static Map<String, Double> obtainReadingsMedians(final List<Reading> readings) {

        return Optional.ofNullable(readings)
                .orElseGet(ArrayList::new)
                .stream()
                .sorted(Comparator.comparing(Reading::getClientId))
                .collect(groupingBy(
                        Reading::getClientId,
                        Collectors.mapping(Reading::getValue, Collectors.toList())))
                .entrySet()
                .stream()
                .map(entry -> new CalculatedResult(entry.getKey(), Quantiles.median().compute(entry.getValue())))
                .collect(Collectors.toMap(CalculatedResult::getClientId, CalculatedResult::getValue));
    }

    @Getter
    @AllArgsConstructor
    public static class CalculatedResult {

        private String clientId;

        private Double value;

    }

}
