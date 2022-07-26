package com.hellolight.frauddetection.infrastructure.csv.helper;

import com.hellolight.frauddetection.infrastructure.csv.entity.CsvReading;
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

    public List<CsvReading> unmarshall(final String fileName) throws IOException {

        File file = new File(path + fileName);
        Reader targetReader = new FileReader(file);

        return new CsvToBeanBuilder(targetReader)
                .withType(CsvReading.class)
                .withSkipLines(1)
                .build()
                .parse();
    }
}
