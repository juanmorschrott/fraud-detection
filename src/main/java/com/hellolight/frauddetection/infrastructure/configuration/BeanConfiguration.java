package com.hellolight.frauddetection.infrastructure.configuration;

import com.hellolight.frauddetection.domain.port.input.FraudDetectionService;
import com.hellolight.frauddetection.domain.port.output.ReadingsProvider;
import com.hellolight.frauddetection.domain.service.FraudDetectionServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    FraudDetectionService fraudDetectionService(final ReadingsProvider readingsProvider) {

        return new FraudDetectionServiceImpl(readingsProvider);
    }

}
