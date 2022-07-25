package com.hellolight.frauddetection.domain.port.input;

import com.hellolight.frauddetection.domain.model.Result;

import java.io.IOException;
import java.util.List;

public interface FraudDetectionService {

    List<Result> detect(String fileName) throws IOException;

}
