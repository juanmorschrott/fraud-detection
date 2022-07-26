package com.hellolight.frauddetection;

import com.hellolight.frauddetection.domain.model.Reading;
import com.hellolight.frauddetection.infrastructure.csv.entity.CsvReading;
import com.hellolight.frauddetection.infrastructure.xml.entity.XmlReadings;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.YearMonth;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ReadingsFixture {

    public static List<Reading> getReadings() {

        Reading r1 = Reading.builder()
                .clientId("583ef6329d7b9")
                .period(YearMonth.of(2016,01))
                .value(42451L)
                .build();

        Reading r2 = Reading.builder()
                .clientId("583ef6329d7b9")
                .period(YearMonth.of(2016,02))
                .value(44279L)
                .build();

        Reading r3 = Reading.builder()
                .clientId("583ef6329d7b9")
                .period(YearMonth.of(2016,03))
                .value(44055L)
                .build();

        return Arrays.asList(r1, r2, r3);
    }

    public static List<CsvReading> getCsvReadings() {

        CsvReading cr1 = CsvReading.builder()
                .clientId("583ef6329d7b9")
                .period(YearMonth.of(2016, 1))
                .value(42451L)
                .build();

        CsvReading cr2 = CsvReading.builder()
                .clientId("583ef6329d7b9")
                .period(YearMonth.of(2016, 2))
                .value(44279L)
                .build();

        CsvReading cr3 = CsvReading.builder()
                .clientId("583ef6329d7b9")
                .period(YearMonth.of(2016, 3))
                .value(44055L)
                .build();

        return Arrays.asList(cr1, cr2, cr3);
    }

    public static XmlReadings getXmlReadings() {

        XmlReadings.Reading xmlReading = XmlReadings.Reading
                .builder()
                .clientId("583ef6329d7b9")
                .period(YearMonth.of(2016, 1))
                .value(42451L)
                .build();

        return XmlReadings.builder()
                .readings(Collections.singletonList(xmlReading))
                .build();
    }
}
