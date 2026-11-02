package com.hellolight.frauddetection.reading.adapter.cli;

import com.hellolight.frauddetection.reading.application.usecase.DetectFraudUseCase;
import com.hellolight.frauddetection.reading.domain.Result;
import org.springframework.shell.core.command.annotation.Command;
import org.springframework.shell.core.command.annotation.Option;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DetectFraudShell {

    private final DetectFraudUseCase detectFraudUseCase;

    public DetectFraudShell(DetectFraudUseCase detectFraudUseCase) {
        this.detectFraudUseCase = detectFraudUseCase;
    }

    @Command(name = "info", description = "Provides application information")
    public String info() {

        return """
                 Input files must be available under src/main/resources/data.
                 Bundled sample files are available there.

                 Scan a file by name:
                 shell:>scan --fileName 2016-readings.xml
               """;
    }

    @Command(name = "scan", description = "Detect suspicious readings in a CSV or XML file")
    public void scan(@Option(longName = "fileName", description = "File name under src/main/resources/data")
            String fileName) {

        List<Result> results = detectFraudUseCase.detect(fileName);

        this.generateTable(results);
    }

    private void generateTable(final List<Result> results) {
        String leftAlignFormat = "| %-19s | %-18s | %-18d | %.2f |%n";

        System.out.println();
        System.out.format("| Client              | Period             | Suspicious         | Mean     |%n");
        System.out.format(" ---------------------------------------------------------------------------%n");

        results.forEach(result -> System.out.format(leftAlignFormat,
                result.clientId(),
                result.period(),
                result.suspiciousReading(),
                result.mean()));
    }

}
