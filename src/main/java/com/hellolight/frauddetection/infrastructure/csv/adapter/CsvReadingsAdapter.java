package com.hellolight.frauddetection.infrastructure.csv.adapter;

import com.hellolight.frauddetection.domain.model.Reading;
import com.hellolight.frauddetection.domain.port.output.ReadingsProvider;
import com.hellolight.frauddetection.infrastructure.csv.converter.CsvReadingsToReadingsConverter;
import com.hellolight.frauddetection.infrastructure.csv.entity.CsvReading;
import com.hellolight.frauddetection.infrastructure.csv.helper.CsvHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class CsvReadingsAdapter implements ReadingsProvider {

    private CsvHelper csvHelper;
    private CsvReadingsToReadingsConverter converter;

    public CsvReadingsAdapter(final CsvHelper csvHelper, final CsvReadingsToReadingsConverter converter) {
        this.csvHelper = csvHelper;
        this.converter = converter;
    }

    @Override
    public List<Reading> getReadings(final String fileName) throws IOException {

        List<CsvReading> csvReadings = this.csvHelper.unmarshall(fileName);

        return this.converter.convert(csvReadings);
    }

}
