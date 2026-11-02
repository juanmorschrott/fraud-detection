package com.hellolight.frauddetection.reading.adapter.file;

import com.hellolight.frauddetection.reading.application.exception.InvalidReadingsFileException;
import com.hellolight.frauddetection.reading.application.port.LoadReadingsPort;
import com.hellolight.frauddetection.reading.domain.Reading;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class ReadingsFileAdapter implements LoadReadingsPort {

    private static final String XML = "xml";
    private static final String CSV = "csv";

    private final CsvReader csvReader;
    private final XmlReader xmlReader;
    private final ReadingFileMapper mapper;

    public ReadingsFileAdapter(CsvReader csvReader, XmlReader xmlReader, ReadingFileMapper mapper) {
        this.csvReader = csvReader;
        this.xmlReader = xmlReader;
        this.mapper = mapper;
    }

    @Override
    public List<Reading> load(String fileName) throws IOException {

        if (fileName == null || fileName.isBlank() || fileName.contains("/")
                || fileName.contains("\\") || fileName.equals(".") || fileName.equals("..")) {
            throw new InvalidReadingsFileException("A plain file name is required");
        }
        
        String fileType = obtainFileExtension(fileName);

        List<Reading> readings;

        if (XML.equals(fileType)) {
            XmlReadings xmlReadings = xmlReader.unmarshall(fileName);
            readings = xmlReadings.getXmlReadings().stream()
                    .map(mapper::toDomain)
                    .toList();

        } else if (CSV.equals(fileType)) {
            List<CsvReading> csvReadings = csvReader.unmarshall(fileName);
            readings = csvReadings.stream()
                    .map(mapper::toDomain)
                    .toList();

        } else {
            throw new InvalidReadingsFileException("Only CSV and XML files are supported");
        }

        return readings;
    }

    private String obtainFileExtension(String fileName) {
        int extensionSeparator = fileName.lastIndexOf('.');
        return extensionSeparator < 0 ? ""
                : fileName.substring(extensionSeparator + 1).toLowerCase(Locale.ROOT);
    }
}
