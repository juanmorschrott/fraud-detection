package com.hellolight.frauddetection;

import com.hellolight.frauddetection.reading.adapter.persistence.ResultEntity;
import com.hellolight.frauddetection.reading.adapter.persistence.ResultRepository;
import com.hellolight.frauddetection.reading.domain.Result;
import com.hellolight.frauddetection.reading.application.usecase.DetectFraudUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.postgresql.PostgreSQLContainer;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers(disabledWithoutDocker = true)
@SpringBootTest(properties = "spring.shell.interactive.enabled=false")
class ApplicationIntegrationTest {

    @Container
    @ServiceConnection
    static final PostgreSQLContainer POSTGRES = new PostgreSQLContainer("postgres:17-alpine");

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private DetectFraudUseCase detectFraudUseCase;

    @Test
    void startsWithTheConfiguredSchemaAndPersistsResults() {
        ResultEntity saved = resultRepository.save(ResultEntity.builder()
                .clientId("client-1")
                .period(LocalDate.of(2016, 9, 1))
                .suspiciousReading(500L)
                .mean(200.0)
                .build());

        assertThat(saved.getId()).isNotNull();
        assertThat(resultRepository.findById(saved.getId()))
                .get()
                .extracting(ResultEntity::getPeriod)
                .isEqualTo(LocalDate.of(2016, 9, 1));
    }

    @Test
    void detectsFraudFromFileAndStoresResultsInPostgres() {
        long before = resultRepository.count();

        List<Result> results = detectFraudUseCase.detect("2016-readings.csv");

        assertThat(results).isNotEmpty()
                .allSatisfy(result -> assertThat(result.period()).isEqualTo(YearMonth.from(result.period().atDay(1))));
        assertThat(resultRepository.count()).isEqualTo(before + results.size());
    }
}
