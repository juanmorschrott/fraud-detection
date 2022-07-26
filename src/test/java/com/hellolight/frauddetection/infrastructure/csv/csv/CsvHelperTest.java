package com.hellolight.frauddetection.infrastructure.csv.csv;

import com.hellolight.frauddetection.infrastructure.csv.entity.CsvReading;
import com.hellolight.frauddetection.infrastructure.csv.helper.CsvHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CsvHelperTest {

    private String path;

    @InjectMocks
    private CsvHelper csvHelper;

    @BeforeEach
    public void init() {
        ReflectionTestUtils.setField(this.csvHelper, "path", "data/");
    }

    @Test
    public void shouldGetReadingsFromCsvFile() throws Exception {

        List<CsvReading> readings = csvHelper.unmarshall("2016-readings.csv");

        assertEquals(readings.size(), 120);
    }

}
