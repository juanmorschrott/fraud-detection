package com.hellolight.frauddetection.reading.application.service;

import com.hellolight.frauddetection.reading.application.port.in.DetectFraudUseCase;
import com.hellolight.frauddetection.reading.application.port.in.ReadingsPort;
import com.hellolight.frauddetection.reading.application.port.out.StoreResultPort;
import com.hellolight.frauddetection.reading.domain.Reading;
import com.hellolight.frauddetection.reading.domain.Result;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.averagingLong;
import static java.util.stream.Collectors.groupingBy;

public class DetectFraudService implements DetectFraudUseCase {

    private final ReadingsPort readingsPort;
    private final StoreResultPort storeResultPort;

    public DetectFraudService(ReadingsPort readingsPort, StoreResultPort storeResultPort) {
        this.readingsPort = readingsPort;
        this.storeResultPort = storeResultPort;
    }

    @Override
    public List<Result> detect(String fileName) throws IOException {

        List<Reading> readings = this.readingsPort.getReadings(fileName);

        Map<String, Double> readingsMeans = obtainReadingsMeans(readings);

        List<Result> results = obtainResults(readings, readingsMeans);

        return this.storeResultPort.save(results);
    }

    private static Map<String, Double> obtainReadingsMeans(List<Reading> readings) {

        return Optional.ofNullable(readings)
                .orElseGet(ArrayList::new)
                .stream()
                .collect(groupingBy(Reading::getClientId, averagingLong(Reading::getValue)));
    }

    private static List<Result> obtainResults(List<Reading> readings, Map<String, Double> readingsMeans) {

        return readings.stream()
                .filter(reading -> reading.getValue() > readingsMeans.get(reading.getClientId()).floatValue() * 1.5)
                .map(reading -> Result.builder()
                        .clientId(reading.getClientId())
                        .month(reading.getPeriod().getMonth())
                        .suspiciousReading(reading.getValue())
                        .mean(readingsMeans.get(reading.getClientId()).floatValue())
                        .build())
                .collect(Collectors.toList());
    }
}
