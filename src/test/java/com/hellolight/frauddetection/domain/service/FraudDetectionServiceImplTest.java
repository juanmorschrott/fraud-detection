package com.hellolight.frauddetection.domain.service;

import com.hellolight.frauddetection.domain.model.Result;
import com.hellolight.frauddetection.infrastructure.file.adapter.FileReadingsAdapter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;

import static com.hellolight.frauddetection.ReadingsFixture.getReadings;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FraudDetectionServiceImplTest {

    @InjectMocks
    private FraudDetectionServiceImpl fraudDetectionService;

    @Mock
    private FileReadingsAdapter fileReadingsAdapter;

    @Test
    public void shouldDetectFraudInFileReadings() throws IOException {

        when(this.fileReadingsAdapter.getReadings(anyString())).thenReturn(getReadings());

        List<Result> results = this.fraudDetectionService.detect("file-name.csv");

        assertThat(results).isNotNull();
    }

}