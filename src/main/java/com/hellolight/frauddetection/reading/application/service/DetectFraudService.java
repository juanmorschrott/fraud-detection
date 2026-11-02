package com.hellolight.frauddetection.reading.application.service;

import com.hellolight.frauddetection.reading.application.exception.ReadingFileAccessException;
import com.hellolight.frauddetection.reading.application.usecase.DetectFraudUseCase;
import com.hellolight.frauddetection.reading.application.port.LoadReadingsPort;
import com.hellolight.frauddetection.reading.application.port.StoreResultPort;
import com.hellolight.frauddetection.reading.domain.Reading;
import com.hellolight.frauddetection.reading.domain.Result;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.stream.Collectors.averagingLong;
import static java.util.stream.Collectors.groupingBy;

public class DetectFraudService implements DetectFraudUseCase {

    private final LoadReadingsPort loadReadingsPort;
    private final StoreResultPort storeResultPort;

    public DetectFraudService(LoadReadingsPort loadReadingsPort, StoreResultPort storeResultPort) {
        this.loadReadingsPort = loadReadingsPort;
        this.storeResultPort = storeResultPort;
    }

    @Override
    public List<Result> detect(String fileName) {
        final List<Reading> readings;
        
        try {
            readings = Objects.requireNonNull(loadReadingsPort.load(fileName),
                    "The readings port must not return null");
        } catch (IOException exception) {
            throw new ReadingFileAccessException("Could not read the readings file", exception);
        }

        Map<ClientYear, Double> readingsMeans = obtainReadingsMeans(readings);

        List<Result> results = obtainResults(readings, readingsMeans);

        return storeResultPort.save(results);
    }

    private static Map<ClientYear, Double> obtainReadingsMeans(List<Reading> readings) {
        return readings.stream().collect(groupingBy(
                reading -> new ClientYear(reading.clientId(), reading.period().getYear()),
                averagingLong(Reading::value)));
    }

    private static List<Result> obtainResults(List<Reading> readings, Map<ClientYear, Double> readingsMeans) {
        List<Result> results = new ArrayList<>();

        for (Reading reading : readings) {
            ClientYear key = new ClientYear(reading.clientId(), reading.period().getYear());
            double mean = readingsMeans.get(key);
            if (reading.value() > mean * 1.5) {
                results.add(new Result(reading.clientId(), reading.period(), reading.value(), mean));
            }
        }
        return results;
    }

    private record ClientYear(String clientId, int year) {
    }
}
