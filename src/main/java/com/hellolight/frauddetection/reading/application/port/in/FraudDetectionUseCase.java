package com.hellolight.frauddetection.reading.application.port.in;

import com.hellolight.frauddetection.reading.domain.Result;

import java.io.IOException;
import java.util.List;

public interface FraudDetectionUseCase {

    List<Result> detect(String fileName) throws IOException;
}
