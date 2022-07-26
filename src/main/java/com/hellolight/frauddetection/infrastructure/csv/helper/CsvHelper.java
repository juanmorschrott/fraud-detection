package com.hellolight.frauddetection.infrastructure.csv.helper;

import com.hellolight.frauddetection.domain.model.Reading;
import com.hellolight.frauddetection.infrastructure.csv.converter.CsvReadingsToReadingsConverter;
import com.hellolight.frauddetection.infrastructure.csv.dto.CsvReadings;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

@Component
public class CsvHelper {

    @Value("${file.path}")
    private String path;

    private CsvReadingsToReadingsConverter converter;

    public CsvHelper(CsvReadingsToReadingsConverter converter) {
        this.converter = converter;
    }

    public List<Reading> unmarshall(String fileName) throws IOException {
        File file = new File(path + fileName);
        Reader targetReader = new FileReader(file);

        List<CsvReadings> csvReadings = new CsvToBeanBuilder(targetReader)
                .withType(CsvReadings.class)
                .withSkipLines(1)
                .build()
                .parse();

        return converter.convert(csvReadings);
    }
}
