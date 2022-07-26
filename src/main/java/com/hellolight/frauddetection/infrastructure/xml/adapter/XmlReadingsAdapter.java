package com.hellolight.frauddetection.infrastructure.xml.adapter;

import com.hellolight.frauddetection.domain.exception.FraudDetectionException;
import com.hellolight.frauddetection.domain.model.Reading;
import com.hellolight.frauddetection.domain.port.output.ReadingsProvider;
import com.hellolight.frauddetection.infrastructure.xml.converter.XmlReadingsToReadingsConverter;
import com.hellolight.frauddetection.infrastructure.xml.entity.XmlReadings;
import com.hellolight.frauddetection.infrastructure.xml.helper.XmlHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.EMPTY;

@Service
public class XmlReadingsAdapter implements ReadingsProvider {

    private static final String XML = "xml";
    private XmlHelper xmlHelper;
    private XmlReadingsToReadingsConverter converter;

    public XmlReadingsAdapter(final XmlHelper xmlHelper, final XmlReadingsToReadingsConverter converter) {
        this.xmlHelper = xmlHelper;
    }

    @Override
    public List<Reading> getReadings(final String fileName) throws IOException {

        String extension = getFileExtension(fileName);

        if (!extension.equalsIgnoreCase(XML)) {
            throw new FraudDetectionException("Not valid file provided");
        }

        XmlReadings xmlReadings = this.xmlHelper.unmarshall(fileName);

        return this.converter.convert(xmlReadings);
    }

    private String getFileExtension(final String path) {

        return Optional.ofNullable(path)
                .map(String::toLowerCase)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(path.lastIndexOf(".") + 1))
                .orElse(EMPTY);
    }

}
