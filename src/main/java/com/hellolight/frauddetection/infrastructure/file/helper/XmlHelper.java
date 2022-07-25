package com.hellolight.frauddetection.infrastructure.file.helper;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hellolight.frauddetection.domain.model.Reading;
import com.hellolight.frauddetection.infrastructure.file.converter.XmlReadingsToReadingsConverter;
import com.hellolight.frauddetection.infrastructure.file.dto.XmlReadings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class XmlHelper implements DataAdapter {

    @Value("${file.path}")
    private String path;

    private XmlReadingsToReadingsConverter converter;

    public XmlHelper(XmlReadingsToReadingsConverter converter) {
        this.converter = converter;
    }

    @Override
    public List<Reading> unmarshall(String fileName) throws IOException {
        File file = new ClassPathResource(path + fileName).getFile();
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.registerModule(new JavaTimeModule());
        xmlMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        XmlReadings xmlReadings = xmlMapper.readValue(file, XmlReadings.class);

        return converter.convert(xmlReadings);
    }
}
