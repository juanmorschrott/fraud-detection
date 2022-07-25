package com.hellolight.frauddetection.infrastructure.csv.converter;

import com.hellolight.frauddetection.domain.model.Reading;
import com.hellolight.frauddetection.infrastructure.csv.dto.CsvReadings;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CsvReadingsToReadingsConverter implements Converter<List<CsvReadings>, List<Reading>> {

    @Override
    public List<Reading> convert(List<CsvReadings> source) {

        return source.stream()
                .map(csvReadings -> new Reading(csvReadings.getClientId(),
                        csvReadings.getPeriod(),
                        csvReadings.getValue()))
                .collect(Collectors.toList());
    }
}
