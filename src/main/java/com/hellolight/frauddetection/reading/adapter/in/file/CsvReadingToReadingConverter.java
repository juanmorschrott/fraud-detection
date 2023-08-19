package com.hellolight.frauddetection.reading.adapter.in.file;

import com.hellolight.frauddetection.reading.domain.Reading;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

@Component
public class CsvReadingToReadingConverter implements Converter<CsvReading, Reading> {

    @Override
    public Reading convert(final CsvReading csvReading) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");

        return Reading.builder()
                .clientId(csvReading.getClientId())
                .period(YearMonth.parse(csvReading.getPeriod(), formatter))
                .value(csvReading.getValue())
                .build();
    }
}
