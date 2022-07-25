package com.hellolight.frauddetection.infrastructure.file.configuration;

import com.hellolight.frauddetection.domain.port.input.FraudDetectionService;
import com.hellolight.frauddetection.domain.port.output.FileReadingsProvider;
import com.hellolight.frauddetection.domain.service.FraudDetectionServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    FraudDetectionService fraudDetectionService(final FileReadingsProvider fileReadingsProvider) {
        return new FraudDetectionServiceImpl(fileReadingsProvider);
    }

}
