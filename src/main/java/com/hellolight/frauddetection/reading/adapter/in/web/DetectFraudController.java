package com.hellolight.frauddetection.reading.adapter.in.web;

import com.hellolight.frauddetection.reading.application.port.in.DetectFraudUseCase;
import com.hellolight.frauddetection.reading.domain.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class DetectFraudController {

    private final DetectFraudUseCase detectFraudUseCase;

    public DetectFraudController(DetectFraudUseCase detectFraudUseCase) {
        this.detectFraudUseCase = detectFraudUseCase;
    }

    @PostMapping("/scan")
    public List<Result> scan(@RequestParam(name = "file_name") String fileName) throws IOException {

        return detectFraudUseCase.detect(fileName);
    }
}
