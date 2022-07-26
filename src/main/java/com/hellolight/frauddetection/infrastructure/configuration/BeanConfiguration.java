package com.hellolight.frauddetection.infrastructure.configuration;

import com.hellolight.frauddetection.domain.port.input.FraudDetectionService;
import com.hellolight.frauddetection.domain.service.FraudDetectionServiceImpl;
import com.hellolight.frauddetection.infrastructure.csv.adapter.CsvReadingsAdapter;
import com.hellolight.frauddetection.infrastructure.xml.adapter.XmlReadingsAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    FraudDetectionService fraudDetectionService(final CsvReadingsAdapter csvReadingsAdapter,
                                                final XmlReadingsAdapter xmlReadingsAdapter) {

        return new FraudDetectionServiceImpl(csvReadingsAdapter, xmlReadingsAdapter);
    }

}
