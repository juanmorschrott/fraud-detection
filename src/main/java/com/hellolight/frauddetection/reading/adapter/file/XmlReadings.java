package com.hellolight.frauddetection.reading.adapter.file;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class XmlReadings implements Serializable {

    @JacksonXmlProperty(localName = "reading")
    @JacksonXmlElementWrapper(useWrapping = false)
    @Builder.Default
    private List<XmlReading> xmlReadings = new ArrayList<>();
}
