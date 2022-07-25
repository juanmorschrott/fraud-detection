package com.hellolight.frauddetection.infrastructure.file.adapter;

import com.hellolight.frauddetection.domain.exception.FraudDetectionException;
import com.hellolight.frauddetection.domain.model.Reading;
import com.hellolight.frauddetection.infrastructure.file.helper.CsvHelper;
import com.hellolight.frauddetection.infrastructure.file.helper.XmlHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;

import static com.hellolight.frauddetection.ReadingsFixture.getReadings;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FileReadingsAdapterTest {

    @InjectMocks
    private FileReadingsAdapter fileReadingsProvider;

    @Mock
    private CsvHelper csvHelper;

    @Mock
    private XmlHelper xmlHelper;

    @Test
    public void shouldGetReadingsByFileName() throws IOException {

        when(this.csvHelper.unmarshall(anyString())).thenReturn(getReadings());

        List<Reading> readings = this.fileReadingsProvider.getReadings("file-name.csv");

        assertEquals(readings.size(), 3);
    }

    @Test
    public void shouldNotifyNotValidFileWasProvided() {

        FraudDetectionException thrown = assertThrows(FraudDetectionException.class, () -> {
            this.fileReadingsProvider.getReadings("file-name");
        });
    }

}