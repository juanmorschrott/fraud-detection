package com.hellolight.frauddetection.reading.application.usecase;

import com.hellolight.frauddetection.reading.domain.Result;

import java.util.List;

public interface DetectFraudUseCase {

    List<Result> detect(String fileName);
}
