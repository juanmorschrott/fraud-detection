package com.hellolight.frauddetection.reading.adapter.out.persistence;

import com.hellolight.frauddetection.reading.application.port.out.StoreResultPort;
import com.hellolight.frauddetection.reading.domain.Result;
import org.springframework.core.convert.ConversionService;

import java.util.List;
import java.util.stream.Collectors;

public class ResultAdapter implements StoreResultPort {

    private final ResultRepository resultRepository;
    private final ConversionService conversionService;

    public ResultAdapter(ResultRepository resultRepository, ConversionService conversionService) {
        this.resultRepository = resultRepository;
        this.conversionService = conversionService;
    }

    @Override
    public void save(List<Result> results) {

        List<ResultEntity> resultEntities = results.stream()
                .map(r -> this.conversionService.convert(r, ResultEntity.class))
                .collect(Collectors.toList());

        this.resultRepository.saveAll(resultEntities);
    }
}
