package com.hellolight.frauddetection.domain.service;

import com.hellolight.frauddetection.domain.model.Reading;
import com.hellolight.frauddetection.domain.model.Result;
import com.hellolight.frauddetection.domain.port.input.FraudDetectionService;
import com.hellolight.frauddetection.domain.port.output.ReadingsProvider;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.averagingLong;
import static java.util.stream.Collectors.groupingBy;

public class FraudDetectionServiceImpl implements FraudDetectionService {

    private ReadingsProvider readingsProvider;

    public FraudDetectionServiceImpl(final ReadingsProvider readingsProvider) {
        this.readingsProvider = readingsProvider;
    }


    @Override
    public List<Result> detect(String fileName) throws IOException {

        List<Reading> readings = this.readingsProvider.getReadings(fileName);

        Map<String, Double> clientMeans = this.calculateMeans(readings);

        return readings.stream()
                .filter(reading -> reading.getValue() > clientMeans.get(reading.getClientId()).floatValue() * 1.5)
                .map(reading -> Result.builder()
                        .clientId(reading.getClientId())
                        .month(reading.getPeriod().getMonth())
                        .suspiciousReading(reading.getValue())
                        .median(clientMeans.get(reading.getClientId()).floatValue())
                        .build())
                .collect(Collectors.toList());
    }

    private Map<String, Double> calculateMeans(List<Reading> readings) {

        return Optional.ofNullable(readings)
                .orElseGet(ArrayList::new)
                .stream()
                .collect(groupingBy(Reading::getClientId, averagingLong(Reading::getValue)));
    }

}
