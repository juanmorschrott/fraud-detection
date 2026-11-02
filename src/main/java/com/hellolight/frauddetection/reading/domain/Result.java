package com.hellolight.frauddetection.reading.domain;

import java.time.YearMonth;

public record Result(String clientId, YearMonth period, Long suspiciousReading, Double mean) {
}
