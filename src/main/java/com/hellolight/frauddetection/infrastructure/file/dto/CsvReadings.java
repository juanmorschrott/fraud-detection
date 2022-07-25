package com.hellolight.frauddetection.infrastructure.file.dto;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.YearMonth;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CsvReadings {

    @CsvBindByPosition(position = 0)
    private String clientId;

    @CsvDate(value = "yyyy-MM")
    @CsvBindByPosition(position = 1)
    private YearMonth period;

    @CsvBindByPosition(position = 2)
    private Long value;

}
