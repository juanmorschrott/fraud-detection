package com.hellolight.frauddetection.reading.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.YearMonth;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reading {

    private String clientId;

    private YearMonth period;

    private Long value;
}
