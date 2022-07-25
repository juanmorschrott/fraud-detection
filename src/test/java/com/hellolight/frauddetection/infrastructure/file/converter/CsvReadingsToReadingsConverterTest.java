package com.hellolight.frauddetection.infrastructure.file.converter;

import com.hellolight.frauddetection.domain.model.Reading;
import com.hellolight.frauddetection.infrastructure.file.dto.CsvReadings;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.hellolight.frauddetection.ReadingsFixture.getCsvReadings;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isA;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CsvReadingsToReadingsConverterTest {

    @InjectMocks
    private CsvReadingsToReadingsConverter converter;

    @Test
    public void shouldConvertCsvReadingsToReadings() {

        List<CsvReadings> csvReadings = getCsvReadings();

        List<Reading> readings = this.converter.convert(csvReadings);

        assertEquals(readings.size(), 3);
        assertThat(readings.get(0), isA(Reading.class));
    }

}