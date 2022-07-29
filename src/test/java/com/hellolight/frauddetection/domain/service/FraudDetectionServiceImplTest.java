package com.hellolight.frauddetection.domain.service;

import com.hellolight.frauddetection.domain.exception.FraudDetectionException;
import com.hellolight.frauddetection.domain.model.Result;
import com.hellolight.frauddetection.domain.port.output.ReadingsProvider;
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
    private ReadingsProvider readingsProvider;

    @Test
    public void shouldDetectFraudInFileReadings() throws IOException {

        when(this.readingsProvider.getReadings(anyString())).thenReturn(getReadings());

        List<Result> results = this.fraudDetectionService.detect("data/file-name.xml");

        assertThat(results).isNotNull();
    }

    @Test
    public void shouldNotifyNotValidFileWasProvided() {

        FraudDetectionException thrown = assertThrows(FraudDetectionException.class, () -> {
            this.fraudDetectionService.detect("file-name");
        });
    }

}