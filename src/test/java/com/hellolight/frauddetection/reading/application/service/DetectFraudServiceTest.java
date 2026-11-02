package com.hellolight.frauddetection.reading.application.service;

import com.hellolight.frauddetection.reading.application.exception.ReadingFileAccessException;
import com.hellolight.frauddetection.reading.application.port.LoadReadingsPort;
import com.hellolight.frauddetection.reading.application.port.StoreResultPort;
import com.hellolight.frauddetection.reading.domain.Reading;
import com.hellolight.frauddetection.reading.domain.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.YearMonth;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DetectFraudServiceTest {

    private LoadReadingsPort loadReadingsPort;
    private StoreResultPort storeResultPort;
    private DetectFraudService service;

    @BeforeEach
    void setUp() {
        loadReadingsPort = mock(LoadReadingsPort.class);
        storeResultPort = mock(StoreResultPort.class);
        service = new DetectFraudService(loadReadingsPort, storeResultPort);
    }

    @Test
    void calculatesAnnualMeanPerClientAndPreservesSuspiciousPeriod() throws IOException {
        List<Reading> readings = List.of(
                reading("client-1", "2016-01", 100),
                reading("client-1", "2016-02", 100),
                reading("client-1", "2016-03", 500),
                reading("client-1", "2017-01", 3_000),
                reading("client-2", "2016-01", 500),
                reading("client-3", "2016-01", 100),
                reading("client-3", "2016-02", 100),
                reading("client-3", "2016-03", 200));
        when(loadReadingsPort.load("readings.csv")).thenReturn(readings);
        when(storeResultPort.save(anyList())).thenAnswer(invocation -> invocation.getArgument(0));

        List<Result> results = service.detect("readings.csv");

        assertThat(results)
                .singleElement()
                .satisfies(result -> {
                    assertThat(result.clientId()).isEqualTo("client-1");
                    assertThat(result.period()).isEqualTo(YearMonth.of(2016, 3));
                    assertThat(result.suspiciousReading()).isEqualTo(500L);
                    assertThat(result.mean()).isEqualTo(700.0 / 3);
                });
    }

    @Test
    void wrapsFileAccessErrorsAtTheApplicationBoundary() throws IOException {
        when(loadReadingsPort.load("missing.csv")).thenThrow(new IOException("missing"));

        assertThatThrownBy(() -> service.detect("missing.csv"))
                .isInstanceOf(ReadingFileAccessException.class)
                .hasCauseInstanceOf(IOException.class);
    }

    private static Reading reading(String clientId, String period, long value) {
        return new Reading(clientId, YearMonth.parse(period), value);
    }
}
