package com.hellolight.frauddetection.reading.adapter.persistence;

import com.hellolight.frauddetection.reading.application.port.StoreResultPort;
import com.hellolight.frauddetection.reading.domain.Result;

import java.util.List;

public class ResultPersistenceAdapter implements StoreResultPort {

    private final ResultRepository resultRepository;
    private final ResultEntityMapper mapper;

    public ResultPersistenceAdapter(ResultRepository resultRepository, ResultEntityMapper mapper) {
        this.resultRepository = resultRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Result> save(List<Result> results) {

        List<ResultEntity> resultEntities = results.stream()
                .map(mapper::toEntity)
                .toList();

        return this.resultRepository.saveAll(resultEntities).stream()
                .map(mapper::toDomain)
                .toList();
    }
}
