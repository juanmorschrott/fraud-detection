package com.hellolight.frauddetection.reading.adapter.out.persistence;

import com.hellolight.frauddetection.reading.domain.Result;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ResultEntityToResultConverter implements Converter<ResultEntity, Result> {

    @Override
    public Result convert(ResultEntity resultEntity) {

        return Result.builder()
                .clientId(resultEntity.getClientId())
                .month(resultEntity.getPeriod().getMonth())
                .suspiciousReading(resultEntity.getSuspiciousReading())
                .mean(resultEntity.getMean())
                .build();
    }
}
