package com.hellolight.frauddetection.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Month;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    private String clientId;

    private Month month;

    private Long suspiciousReading;

    private Float median;

}
