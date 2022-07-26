package com.hellolight.frauddetection.infrastructure.xml.helper;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hellolight.frauddetection.infrastructure.xml.entity.XmlReadings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class XmlHelper {

    @Value("${file.path}")
    private String path;

    public XmlReadings unmarshall(String fileName) throws IOException {
        File file = new File(path + fileName);
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.registerModule(new JavaTimeModule());
        xmlMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        return xmlMapper.readValue(file, XmlReadings.class);
    }
}
