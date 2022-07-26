package com.hellolight.frauddetection.infrastructure.xml.adapter;

import com.hellolight.frauddetection.domain.model.Reading;
import com.hellolight.frauddetection.domain.port.output.ReadingsProvider;
import com.hellolight.frauddetection.infrastructure.xml.converter.XmlReadingsToReadingsConverter;
import com.hellolight.frauddetection.infrastructure.xml.entity.XmlReadings;
import com.hellolight.frauddetection.infrastructure.xml.helper.XmlHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class XmlReadingsAdapter implements ReadingsProvider {

    private XmlHelper xmlHelper;
    private XmlReadingsToReadingsConverter converter;

    public XmlReadingsAdapter(final XmlHelper xmlHelper, final XmlReadingsToReadingsConverter converter) {
        this.xmlHelper = xmlHelper;
        this.converter = converter;
    }

    @Override
    public List<Reading> getReadings(final String fileName) throws IOException {

        XmlReadings xmlReadings = this.xmlHelper.unmarshall(fileName);

        return this.converter.convert(xmlReadings);
    }

}
