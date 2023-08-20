package com.hellolight.frauddetection.common;

import com.hellolight.frauddetection.reading.adapter.in.file.CsvReader;
import com.hellolight.frauddetection.reading.adapter.in.file.ReadingsFileAdapter;
import com.hellolight.frauddetection.reading.adapter.in.file.XmlReader;
import com.hellolight.frauddetection.reading.adapter.out.persistence.ResultPersistenceAdapter;
import com.hellolight.frauddetection.reading.adapter.out.persistence.ResultRepository;
import com.hellolight.frauddetection.reading.application.port.in.DetectFraudUseCase;
import com.hellolight.frauddetection.reading.application.port.in.ReadingsPort;
import com.hellolight.frauddetection.reading.application.port.out.StoreResultPort;
import com.hellolight.frauddetection.reading.application.service.DetectFraudService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;

@Configuration
public class BeanConfiguration {

    @Bean
    ReadingsPort readingsFileAdapter(CsvReader csvReader, XmlReader xmlReader, ConversionService conversionService) {

        return new ReadingsFileAdapter(csvReader, xmlReader, conversionService);
    }

    @Bean
    StoreResultPort resultPersistenceAdapter(ResultRepository resultRepository, ConversionService conversionService) {

        return new ResultPersistenceAdapter(resultRepository, conversionService);
    }

    @Bean
    DetectFraudUseCase detectFraudUseCase(ReadingsPort readingsPort, StoreResultPort storeResultPort) {

        return new DetectFraudService(readingsPort, storeResultPort);
    }
}
