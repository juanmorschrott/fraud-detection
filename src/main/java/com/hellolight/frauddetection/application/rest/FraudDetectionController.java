package com.hellolight.frauddetection.application.rest;

import com.hellolight.frauddetection.domain.model.Result;
import com.hellolight.frauddetection.domain.port.input.FraudDetectionService;

import java.io.IOException;
import java.util.List;

/**
 * This controller just has illustration purposes. I want to show how another
 * input system can be integrated in a hexagonal architectured application.
 */
// @RestController
public class FraudDetectionController {

    private FraudDetectionService fraudDetectionService;

    public FraudDetectionController(FraudDetectionService fraudDetectionService) {
        this.fraudDetectionService = fraudDetectionService;
    }

    // @PostMapping("/scan")
    public List<Result> scan(/*@RequestParam */String fileName) throws IOException {

        return fraudDetectionService.detect(fileName);
    }

}
