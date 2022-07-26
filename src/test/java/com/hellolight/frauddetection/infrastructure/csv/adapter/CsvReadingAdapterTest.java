package com.hellolight.frauddetection.infrastructure.csv.adapter;

import com.hellolight.frauddetection.domain.model.Reading;
import com.hellolight.frauddetection.infrastructure.csv.converter.CsvReadingsToReadingsConverter;
import com.hellolight.frauddetection.infrastructure.csv.helper.CsvHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;

import static com.hellolight.frauddetection.ReadingsFixture.getCsvReadings;
import static com.hellolight.frauddetection.ReadingsFixture.getReadings;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CsvReadingAdapterTest {

    @InjectMocks
    private CsvReadingsAdapter fileReadingsProvider;

    @Mock
    private CsvHelper csvHelper;

    @Mock
    private CsvReadingsToReadingsConverter converter;

    @Test
    public void shouldGetReadingsByFileName() throws IOException {

        when(this.csvHelper.unmarshall(anyString())).thenReturn(getCsvReadings());
        when(this.converter.convert(anyList())).thenReturn(getReadings());

        List<Reading> readings = this.fileReadingsProvider.getReadings("path/file-name.csv");

        assertEquals(3, readings.size());
    }

}