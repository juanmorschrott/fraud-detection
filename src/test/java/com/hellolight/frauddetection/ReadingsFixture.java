package com.hellolight.frauddetection;

import com.hellolight.frauddetection.domain.model.Reading;
import com.hellolight.frauddetection.infrastructure.csv.dto.CsvReadings;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.YearMonth;
import java.util.Arrays;
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

    public static List<CsvReadings> getCsvReadings() {

        CsvReadings cr1 = CsvReadings.builder()
                .clientId("583ef6329d7b9")
                .period(YearMonth.of(2016,01))
                .value(42451L)
                .build();

        CsvReadings cr2 = CsvReadings.builder()
                .clientId("583ef6329d7b9")
                .period(YearMonth.of(2016,02))
                .value(44279L)
                .build();

        CsvReadings cr3 = CsvReadings.builder()
                .clientId("583ef6329d7b9")
                .period(YearMonth.of(2016,03))
                .value(44055L)
                .build();

        return Arrays.asList(cr1, cr2, cr3);
    }
}
