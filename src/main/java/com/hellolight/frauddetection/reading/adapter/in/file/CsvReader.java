package com.hellolight.frauddetection.reading.adapter.in.file;

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
public class CsvReader {

    @Value("${data.path}")
    private String dataPath;

    public List<CsvReading> unmarshall(final String fileName) throws IOException {
        ClassPathResource resource = new ClassPathResource(dataPath + "/" + fileName);
        File file = resource.getFile();

        Reader targetReader = new FileReader(file);

        return new CsvToBeanBuilder<CsvReading>(targetReader)
                .withType(CsvReading.class)
                .withSkipLines(1)
                .withSeparator(',')
                .build()
                .parse();
    }
}
