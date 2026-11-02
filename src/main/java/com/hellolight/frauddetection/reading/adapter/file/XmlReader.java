package com.hellolight.frauddetection.reading.adapter.file;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Component
public class XmlReader {

    public XmlReadings unmarshall(final String fileName) throws IOException {
        ClassPathResource resource = new ClassPathResource("data/" + fileName);

        ObjectMapper objectMapper = new XmlMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        try (InputStream inputStream = resource.getInputStream()) {
            return objectMapper.readValue(inputStream, XmlReadings.class);
        }
    }
}
