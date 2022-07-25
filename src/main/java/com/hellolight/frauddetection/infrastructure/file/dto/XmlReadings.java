package com.hellolight.frauddetection.infrastructure.file.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.Data;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Data
public class XmlReadings {

    @JacksonXmlProperty(localName = "reading")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Reading> readings = new ArrayList<>();

    @Data
    public static class Reading {

        @JacksonXmlProperty(localName = "clientID", isAttribute = true)
        private String clientId;

        @JacksonXmlProperty(isAttribute = true)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM")
        private YearMonth period;

        @JacksonXmlText
        private Long value;

    }
}
