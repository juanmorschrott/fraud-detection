package com.hellolight.frauddetection.reading.adapter.in.file;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class XmlReadings implements Serializable {

    @JacksonXmlProperty(localName = "reading")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<XmlReading> xmlReadings = new ArrayList<>();
}
