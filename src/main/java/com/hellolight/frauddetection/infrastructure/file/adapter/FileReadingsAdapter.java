package com.hellolight.frauddetection.infrastructure.file.adapter;

import com.hellolight.frauddetection.domain.exception.FraudDetectionException;
import com.hellolight.frauddetection.domain.model.Reading;
import com.hellolight.frauddetection.domain.port.output.FileReadingsProvider;
import com.hellolight.frauddetection.infrastructure.file.helper.CsvHelper;
import com.hellolight.frauddetection.infrastructure.file.helper.XmlHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.EMPTY;

@Service
public class FileReadingsAdapter implements FileReadingsProvider {

    private static final String XML = "xml";
    private static final String CSV = "csv";
    private CsvHelper csvHelper;
    private XmlHelper xmlHelper;

    public FileReadingsAdapter(final CsvHelper csvHelper, final XmlHelper xmlHelper) {
        this.csvHelper = csvHelper;
        this.xmlHelper = xmlHelper;
    }

    @Override
    public List<Reading> getReadings(final String fileName) throws IOException {

        String extension = getFileExtension(fileName);

        if (EMPTY.equals(extension)) {
            throw new FraudDetectionException("Not valid file provided");
        }

        return switch (extension) {
            case XML -> this.xmlHelper.unmarshall(fileName);
            case CSV -> this.csvHelper.unmarshall(fileName);
            default -> new ArrayList<>();
        };
    }

    private String getFileExtension(final String path) {

        return Optional.ofNullable(path)
                .map(String::toLowerCase)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(path.lastIndexOf(".") + 1))
                .orElse(EMPTY);
    }

}
