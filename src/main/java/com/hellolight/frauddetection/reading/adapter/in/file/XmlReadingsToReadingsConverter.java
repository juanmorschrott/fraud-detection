package com.hellolight.frauddetection.reading.adapter.in.file;

import com.hellolight.frauddetection.reading.domain.Reading;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class XmlReadingsToReadingsConverter implements Converter<XmlReading,Reading> {

    @Override
    public Reading convert(XmlReading xmlReading) {
        List<Reading> readings = new ArrayList<>();

        return Reading.builder()
                .clientId(xmlReading.getClientId())
                .period(xmlReading.getPeriod())
                .value(xmlReading.getValue())
                .build();
    }

}
