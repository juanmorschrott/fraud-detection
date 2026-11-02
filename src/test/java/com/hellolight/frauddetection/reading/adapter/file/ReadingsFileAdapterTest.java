package com.hellolight.frauddetection.reading.adapter.file;

import com.hellolight.frauddetection.reading.application.exception.InvalidReadingsFileException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ReadingsFileAdapterTest {

    private ReadingsFileAdapter adapter;

    @BeforeEach
    void setUp() {
        CsvReader csvReader = new CsvReader();
        XmlReader xmlReader = new XmlReader();
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
                .isInstanceOf(InvalidReadingsFileException.class);
        assertThatThrownBy(() -> adapter.load("readings.json"))
                .isInstanceOf(InvalidReadingsFileException.class);
    }
}
