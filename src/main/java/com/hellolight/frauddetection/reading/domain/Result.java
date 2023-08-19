package com.hellolight.frauddetection.reading.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("client_id")
    private String clientId;

    @JsonProperty("month")
    private Month month;

    @JsonProperty("suspicious_reading")
    private Long suspiciousReading;

    @JsonProperty("mean")
    private Float mean;
}
