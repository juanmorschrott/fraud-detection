package com.hellolight.frauddetection.common;

import com.hellolight.frauddetection.reading.adapter.in.file.CsvReader;
import com.hellolight.frauddetection.reading.adapter.in.file.ReadingsAdapter;
import com.hellolight.frauddetection.reading.adapter.in.file.XmlReader;
import com.hellolight.frauddetection.reading.adapter.out.persistence.ResultAdapter;
import com.hellolight.frauddetection.reading.adapter.out.persistence.ResultRepository;
import com.hellolight.frauddetection.reading.application.port.in.FraudDetectionUseCase;
import com.hellolight.frauddetection.reading.application.port.in.ReadingsPort;
import com.hellolight.frauddetection.reading.application.port.out.StoreResultPort;
import com.hellolight.frauddetection.reading.application.service.FraudDetectionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;

@Configuration
public class BeanConfiguration {

    @Bean
    ReadingsPort readingsAdapter(CsvReader csvReader, XmlReader xmlReader, ConversionService conversionService) {

        return new ReadingsAdapter(csvReader, xmlReader, conversionService);
    }

    @Bean
    StoreResultPort resultAdapter(ResultRepository resultRepository, ConversionService conversionService) {

        return new ResultAdapter(resultRepository, conversionService);
    }

    @Bean
    FraudDetectionUseCase fraudDetectionUseCase(ReadingsPort readingsPort, StoreResultPort storeResultPort) {

        return new FraudDetectionService(readingsPort, storeResultPort);
    }
}
