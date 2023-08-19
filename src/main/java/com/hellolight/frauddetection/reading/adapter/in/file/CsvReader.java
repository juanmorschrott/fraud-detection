package com.hellolight.frauddetection.reading.adapter.in.file;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@Component
public class CsvReader {

    @Value("${data.path}")
    private String dataPath;

    public List<CsvReading> unmarshall(final String fileName) throws IOException {
        ClassPathResource resource = new ClassPathResource(dataPath + "/" + fileName);

        Reader targetReader = new InputStreamReader(resource.getInputStream());

        return new CsvToBeanBuilder<CsvReading>(targetReader)
                .withType(CsvReading.class)
                .withSkipLines(1)
                .withSeparator(',')
                .build()
                .parse();
    }
}
