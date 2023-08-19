package com.hellolight.frauddetection.reading.adapter.in.file;

import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CsvReading {

    private Long id;

    @CsvBindByPosition(position = 0)
    private String clientId;

    @CsvBindByPosition(position = 1)
    private String period;

    @CsvBindByPosition(position = 2)
    private Long value;
}

