package com.hellolight.frauddetection.common;

import com.hellolight.frauddetection.reading.adapter.file.CsvReader;
import com.hellolight.frauddetection.reading.adapter.file.ReadingFileMapper;
import com.hellolight.frauddetection.reading.adapter.file.ReadingsFileAdapter;
import com.hellolight.frauddetection.reading.adapter.file.XmlReader;
import com.hellolight.frauddetection.reading.adapter.persistence.ResultEntityMapper;
import com.hellolight.frauddetection.reading.adapter.persistence.ResultPersistenceAdapter;
import com.hellolight.frauddetection.reading.adapter.persistence.ResultRepository;
import com.hellolight.frauddetection.reading.application.usecase.DetectFraudUseCase;
import com.hellolight.frauddetection.reading.application.port.LoadReadingsPort;
import com.hellolight.frauddetection.reading.application.port.StoreResultPort;
import com.hellolight.frauddetection.reading.application.service.DetectFraudService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    LoadReadingsPort readingsFileAdapter(CsvReader csvReader, XmlReader xmlReader, ReadingFileMapper mapper) {
        return new ReadingsFileAdapter(csvReader, xmlReader, mapper);
    }

    @Bean
    StoreResultPort resultPersistenceAdapter(ResultRepository resultRepository, ResultEntityMapper mapper) {
        return new ResultPersistenceAdapter(resultRepository, mapper);
    }

    @Bean
    DetectFraudUseCase detectFraudUseCase(LoadReadingsPort loadReadingsPort, StoreResultPort storeResultPort) {
        return new DetectFraudService(loadReadingsPort, storeResultPort);
    }
}
