package com.hellolight.frauddetection.infrastructure.csv.adapter;

import com.hellolight.frauddetection.domain.exception.FraudDetectionException;
import com.hellolight.frauddetection.domain.model.Reading;
import com.hellolight.frauddetection.domain.port.output.FileReadingsProvider;
import com.hellolight.frauddetection.infrastructure.csv.helper.CsvHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.EMPTY;

@Service
public class CsvReadingsAdapter implements FileReadingsProvider {

    private static final String CSV = "csv";
    private CsvHelper csvHelper;

    public CsvReadingsAdapter(final CsvHelper csvHelper) {
        this.csvHelper = csvHelper;
    }

    @Override
    public List<Reading> getReadings(final String fileName) throws IOException {

        String extension = getFileExtension(fileName);

        if (!extension.equalsIgnoreCase(CSV)) {
            throw new FraudDetectionException("Not valid file provided");
        }

        return this.csvHelper.unmarshall(fileName);
    }

    private String getFileExtension(final String path) {

        return Optional.ofNullable(path)
                .map(String::toLowerCase)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(path.lastIndexOf(".") + 1))
                .orElse(EMPTY);
    }

}
