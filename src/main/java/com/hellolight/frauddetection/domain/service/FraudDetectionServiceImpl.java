package com.hellolight.frauddetection.domain.service;

import com.hellolight.frauddetection.domain.exception.FraudDetectionException;
import com.hellolight.frauddetection.domain.model.Reading;
import com.hellolight.frauddetection.domain.model.Result;
import com.hellolight.frauddetection.domain.port.input.FraudDetectionService;
import com.hellolight.frauddetection.domain.port.output.ReadingsProvider;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ArrayList;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.averagingLong;
import static java.util.stream.Collectors.groupingBy;
import static org.apache.commons.lang3.StringUtils.EMPTY;

public class FraudDetectionServiceImpl implements FraudDetectionService {
    private static final String XML = "xml";

    private ReadingsProvider readingsProvider;

    public FraudDetectionServiceImpl(final ReadingsProvider readingsProvider) {
        this.readingsProvider = readingsProvider;
    }

    @Override
    public List<Result> detect(final String fileName) throws IOException {

        if (!XML.equals(this.obtainFileExtension(fileName))) {
            throw new FraudDetectionException("Not valid file provided");
        }

        List<Reading> readings = this.readingsProvider.getReadings(fileName);

        Map<String, Double> clientMeans = this.obtainClientMeans(readings);

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


    private String obtainFileExtension(final String fileName) {

        return Optional.ofNullable(fileName)
                .map(String::toLowerCase)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(fileName.lastIndexOf(".") + 1))
                .orElse(EMPTY);
    }

    private Map<String, Double> obtainClientMeans(final List<Reading> readings) {

        return Optional.ofNullable(readings)
                .orElseGet(ArrayList::new)
                .stream()
                .collect(groupingBy(Reading::getClientId, averagingLong(Reading::getValue)));
    }

}
