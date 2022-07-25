package com.hellolight.frauddetection.infrastructure.file.helper;

import com.hellolight.frauddetection.domain.model.Reading;
import com.hellolight.frauddetection.infrastructure.file.converter.CsvReadingsToReadingsConverter;
import com.hellolight.frauddetection.infrastructure.file.dto.CsvReadings;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

@Component
public class CsvHelper implements DataAdapter {

    @Value("${file.path}")
    private String path;

    private CsvReadingsToReadingsConverter converter;

    public CsvHelper(CsvReadingsToReadingsConverter converter) {
        this.converter = converter;
    }

    @Override
    public List<Reading> unmarshall(String fileName) throws IOException {
        File file = new ClassPathResource(path + fileName).getFile();
        Reader targetReader = new FileReader(file);

        List<CsvReadings> csvReadings = new CsvToBeanBuilder(targetReader)
                .withType(CsvReadings.class)
                .withSkipLines(1)
                .build()
                .parse();

        return converter.convert(csvReadings);
    }
}
