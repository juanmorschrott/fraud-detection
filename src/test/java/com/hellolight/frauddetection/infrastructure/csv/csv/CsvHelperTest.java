package com.hellolight.frauddetection.infrastructure.csv.csv;

import com.hellolight.frauddetection.domain.model.Reading;
import com.hellolight.frauddetection.infrastructure.csv.converter.CsvReadingsToReadingsConverter;
import com.hellolight.frauddetection.infrastructure.csv.helper.CsvHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static com.hellolight.frauddetection.ReadingsFixture.getReadings;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CsvHelperTest {

    private String path;

    @InjectMocks
    private CsvHelper csvHelper;

    @Mock
    private CsvReadingsToReadingsConverter converter;

    @BeforeEach
    public void init() {
        ReflectionTestUtils.setField(this.csvHelper, "path", "data/");
    }

    @Test
    public void shouldGetReadingsFromCsvFile() throws Exception {

        when(this.converter.convert(any())).thenReturn(getReadings());

        List<Reading> readings = csvHelper.unmarshall("2016-readings.csv");

        assertEquals(readings.size(), 3);
    }

}
