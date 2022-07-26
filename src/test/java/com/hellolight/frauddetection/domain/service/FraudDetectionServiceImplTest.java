package com.hellolight.frauddetection.domain.service;

import com.hellolight.frauddetection.domain.exception.FraudDetectionException;
import com.hellolight.frauddetection.domain.model.Result;
import com.hellolight.frauddetection.infrastructure.csv.adapter.CsvReadingsAdapter;
import com.hellolight.frauddetection.infrastructure.xml.adapter.XmlReadingsAdapter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;

import static com.hellolight.frauddetection.ReadingsFixture.getReadings;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FraudDetectionServiceImplTest {

    @InjectMocks
    private FraudDetectionServiceImpl fraudDetectionService;

    @Mock
    private CsvReadingsAdapter csvReadingsAdapter;

    @Mock
    private XmlReadingsAdapter xmlReadingsAdapter;

    @Test
    public void shouldDetectFraudInFileReadings() throws IOException {

        when(this.csvReadingsAdapter.getReadings(anyString())).thenReturn(getReadings());

        List<Result> results = this.fraudDetectionService.detect("data/file-name.csv");

        assertThat(results).isNotNull();
    }

    @Test
    public void shouldNotifyNotValidFileWasProvided() {

        FraudDetectionException thrown = assertThrows(FraudDetectionException.class, () -> {
            this.fraudDetectionService.detect("file-name");
        });
    }

}