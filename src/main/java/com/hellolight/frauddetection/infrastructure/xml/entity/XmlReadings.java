package com.hellolight.frauddetection.infrastructure.xml.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class XmlReadings implements Serializable {

    @JacksonXmlProperty(localName = "reading")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Reading> readings = new ArrayList<>();

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
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
