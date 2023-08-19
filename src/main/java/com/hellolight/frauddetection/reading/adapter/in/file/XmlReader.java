package com.hellolight.frauddetection.reading.adapter.in.file;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class XmlReader {

    @Value("${data.path}")
    private String dataPath;

    public XmlReadings unmarshall(final String fileName) throws IOException {
        ClassPathResource resource = new ClassPathResource(dataPath + "/" + fileName);

        ObjectMapper objectMapper = new XmlMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        return objectMapper.readValue(resource.getInputStream(), XmlReadings.class);
    }
}
