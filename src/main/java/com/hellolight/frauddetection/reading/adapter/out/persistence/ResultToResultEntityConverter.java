package com.hellolight.frauddetection.reading.adapter.out.persistence;

import com.hellolight.frauddetection.reading.domain.Result;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Year;

@Component
public class ResultToResultEntityConverter implements Converter<Result, ResultEntity> {

    @Override
    public ResultEntity convert(Result result) {

        int year = Year.now().getValue();
        int monthValue = result.getMonth().getValue();

        LocalDate convertedDate = LocalDate.of(year, monthValue, 1);

        return ResultEntity.builder()
                .clientId(result.getClientId())
                .period(convertedDate)
                .suspiciousReading(result.getSuspiciousReading())
                .mean(result.getMean())
                .build();
    }
}
