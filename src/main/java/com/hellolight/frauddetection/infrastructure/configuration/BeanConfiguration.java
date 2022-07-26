package com.hellolight.frauddetection.infrastructure.configuration;

import com.hellolight.frauddetection.domain.port.input.FraudDetectionService;
import com.hellolight.frauddetection.domain.port.output.ReadingsProvider;
import com.hellolight.frauddetection.domain.service.FraudDetectionServiceImpl;
import com.hellolight.frauddetection.infrastructure.csv.adapter.CsvReadingsAdapter;
import com.hellolight.frauddetection.infrastructure.csv.helper.CsvHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    ReadingsProvider readingsProvider(final CsvHelper csvHelper) {
        return new CsvReadingsAdapter(csvHelper);
    }

    @Bean
    FraudDetectionService fraudDetectionService(final ReadingsProvider readingsProvider) {
        return new FraudDetectionServiceImpl(readingsProvider);
    }

}
