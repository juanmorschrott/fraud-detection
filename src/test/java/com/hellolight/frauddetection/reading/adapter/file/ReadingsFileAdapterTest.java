package com.hellolight.frauddetection.reading.adapter.file;

import com.hellolight.frauddetection.reading.application.exception.InvalidFileTypeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ReadingsFileAdapterTest {

    private ReadingsFileAdapter adapter;

    @BeforeEach
    void setUp() {
        CsvReader csvReader = new CsvReader();
        XmlReader xmlReader = new XmlReader();
        ReflectionTestUtils.setField(csvReader, "dataPath", "data");
        ReflectionTestUtils.setField(xmlReader, "dataPath", "data");
        adapter = new ReadingsFileAdapter(
                csvReader, xmlReader,
                new ReadingFileMapperImpl());
    }

    @Test
    void readsCsvAndPreservesYearAndMonth() throws Exception {
        var readings = adapter.load("2016-readings.csv");

        assertThat(readings).isNotEmpty();
        assertThat(readings.get(0).period().toString()).isEqualTo("2016-01");
    }

    @Test
    void readsXmlAndPreservesYearAndMonth() throws Exception {
        var readings = adapter.load("2016-readings.xml");

        assertThat(readings).isNotEmpty();
        assertThat(readings.get(0).period().toString()).isEqualTo("2016-01");
    }

    @Test
    void rejectsPathsAndUnsupportedExtensions() {
        assertThatThrownBy(() -> adapter.load("../readings.csv"))
                .isInstanceOf(InvalidFileTypeException.class);
        assertThatThrownBy(() -> adapter.load("readings.json"))
                .isInstanceOf(InvalidFileTypeException.class);
    }
}
