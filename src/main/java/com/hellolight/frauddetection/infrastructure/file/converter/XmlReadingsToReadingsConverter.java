package com.hellolight.frauddetection.infrastructure.file.converter;

import com.hellolight.frauddetection.domain.model.Reading;
import com.hellolight.frauddetection.infrastructure.file.dto.XmlReadings;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class XmlReadingsToReadingsConverter implements Converter<XmlReadings, List<Reading>> {

    @Override
    public List<Reading> convert(XmlReadings source) {
        List<Reading> readings = new ArrayList<>();

        return source.getReadings().stream()
                .map(reading -> new Reading(reading.getClientId(), reading.getPeriod(), reading.getValue()))
                .collect(Collectors.toList());
    }

}
