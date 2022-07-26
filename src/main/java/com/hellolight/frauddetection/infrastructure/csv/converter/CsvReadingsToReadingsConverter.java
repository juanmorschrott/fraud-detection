package com.hellolight.frauddetection.infrastructure.csv.converter;

import com.hellolight.frauddetection.domain.model.Reading;
import com.hellolight.frauddetection.infrastructure.csv.entity.CsvReading;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CsvReadingsToReadingsConverter implements Converter<List<CsvReading>, List<Reading>> {

    @Override
    public List<Reading> convert(final List<CsvReading> source) {

        return source.stream()
                .map(csvReadings -> new Reading(csvReadings.getClientId(),
                        csvReadings.getPeriod(),
                        csvReadings.getValue()))
                .collect(Collectors.toList());
    }
}
