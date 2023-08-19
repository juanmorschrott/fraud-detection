package com.hellolight.frauddetection.reading.adapter.in.cli;

import com.hellolight.frauddetection.reading.application.port.in.DetectFraudUseCase;
import com.hellolight.frauddetection.reading.domain.Result;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.io.IOException;
import java.util.List;

@ShellComponent
public class FraudDetectionShell {

    private final DetectFraudUseCase detectFraudService;

    public FraudDetectionShell(DetectFraudUseCase detectFraudService) {
        this.detectFraudService = detectFraudService;
    }

    @ShellMethod("Provides application information")
    public String info() {

        return """
                 Please put the file you want to scan on the following path:
                 
                 fraud-detection
                 └───data
                             
                 Once the file is located execute the following command:
                 
                 shell:>scan --fileName 2016-readings.xml
               """;
    }

    @ShellMethod("Initialize fraud detection")
    public void scan(@ShellOption() String fileName) throws IOException {

        List<Result> results = detectFraudService.detect(fileName);

        this.generateTable(results);
    }

    private void generateTable(final List<Result> results) {
        String leftAlignFormat = "| %-19s | %-18s | %-18d | %.2f |%n";

        System.out.println();
        System.out.format("| Client              | Month              | Suspicious         | Mean     |%n");
        System.out.format(" ---------------------------------------------------------------------------%n");

        results.forEach(result -> System.out.format(leftAlignFormat,
                result.getClientId(),
                result.getMonth().name(),
                result.getSuspiciousReading(),
                result.getMean()));
    }

}
