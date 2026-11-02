package com.hellolight.frauddetection.reading.domain;

import java.time.YearMonth;

public record Reading(String clientId, YearMonth period, Long value) {
}
