package com.hellolight.frauddetection.infrastructure.csv.converter;

import com.hellolight.frauddetection.domain.model.Reading;
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

        List<Reading> readings = this.converter.convert(getCsvReadings());

        assertEquals(3, readings.size());
        assertThat(readings.get(0), isA(Reading.class));
    }

}