package com.hellolight.frauddetection.reading.adapter.out.persistence;

import com.hellolight.frauddetection.reading.application.port.out.StoreResultPort;
import com.hellolight.frauddetection.reading.domain.Result;
import org.springframework.core.convert.ConversionService;

import java.util.List;
import java.util.stream.Collectors;

public class ResultPersistenceAdapter implements StoreResultPort {

    private final ResultRepository resultRepository;
    private final ConversionService conversionService;

    public ResultPersistenceAdapter(ResultRepository resultRepository, ConversionService conversionService) {
        this.resultRepository = resultRepository;
        this.conversionService = conversionService;
    }

    @Override
    public List<Result> save(List<Result> results) {

        List<ResultEntity> resultEntities = results.stream()
                .map(result -> this.conversionService.convert(result, ResultEntity.class))
                .collect(Collectors.toList());

        return this.resultRepository.saveAll(resultEntities).stream()
                .map(r -> this.conversionService.convert(r, Result.class))
                .collect(Collectors.toList());
    }
}
