package com.hellolight.frauddetection.infrastructure.csv.helper;

import com.hellolight.frauddetection.domain.model.Reading;
import com.hellolight.frauddetection.infrastructure.csv.converter.CsvReadingsToReadingsConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class CsvHelperTest {

    @InjectMocks
    private CsvHelper csvHelper;

    @Mock
    private CsvReadingsToReadingsConverter converter;

    @BeforeEach
    public void init() {
        ReflectionTestUtils.setField(this.csvHelper, "path", "data/");
    }

    @Test
    public void shouldReturnReadingsFromCsvFile() throws IOException {

        List<Reading> readings = this.csvHelper.unmarshall("file-name.csv");

        assertThat(readings.size() > 0);
    }

}