package com.hellolight.frauddetection.reading.adapter.in.file;

import com.hellolight.frauddetection.reading.application.exception.InvalidDataFileTypeException;
import com.hellolight.frauddetection.reading.application.port.in.ReadingsPort;
import com.hellolight.frauddetection.reading.domain.Reading;
import org.springframework.core.convert.ConversionService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.EMPTY;

public class ReadingsAdapter implements ReadingsPort {

    private static final String XML = "xml";
    private static final String CSV = "csv";

    private final CsvReader csvReader;
    private final XmlReader xmlReader;
    private final ConversionService conversionService;

    public ReadingsAdapter(CsvReader csvReader, XmlReader xmlReader, ConversionService conversionService) {
        this.csvReader = csvReader;
        this.xmlReader = xmlReader;
        this.conversionService = conversionService;
    }

    @Override
    public List<Reading> getReadings(String fileName) throws IOException {

        String fileType = this.obtainFileExtension(fileName);

        List<Reading> readings;

        if (XML.equals(fileType)) {
            XmlReadings xmlReadings = this.xmlReader.unmarshall(fileName);
            readings = xmlReadings.getXmlReadings().stream()
                    .map(xmlReading -> this.conversionService.convert(xmlReading, Reading.class))
                    .collect(Collectors.toList());

        } else if (CSV.equals(fileType)) {
            List<CsvReading> csvReadings = this.csvReader.unmarshall(fileName);
            readings = csvReadings.stream()
                    .map(csvReading -> this.conversionService.convert(csvReading, Reading.class))
                    .collect(Collectors.toList());

        } else {
            throw new InvalidDataFileTypeException("Not valid file type provided");
        }

        return readings;
    }

    private String obtainFileExtension(String fileName) {

        return Optional.ofNullable(fileName)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(fileName.lastIndexOf(".") + 1))
                .map(String::toLowerCase)
                .orElse(EMPTY);
    }
}
