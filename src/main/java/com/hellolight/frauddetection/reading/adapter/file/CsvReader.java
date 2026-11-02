package com.hellolight.frauddetection.reading.adapter.file;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.opencsv.bean.CsvToBeanBuilder;

@Component
public class CsvReader {

    @Value("${data.path}")
    private String dataPath;

    public List<CsvReading> unmarshall(final String fileName) throws IOException {
        ClassPathResource resource = new ClassPathResource(dataPath + "/" + fileName);

        try (Reader targetReader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)) {
            return new CsvToBeanBuilder<CsvReading>(targetReader)
                    .withType(CsvReading.class)
                    .withSkipLines(1)
                    .withSeparator(',')
                    .build()
                    .parse();
        }
    }
}
