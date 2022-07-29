package com.hellolight.frauddetection.infrastructure.xml.helper;

import com.hellolight.frauddetection.infrastructure.xml.entity.XmlReadings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class XmlHelperTest {

    @InjectMocks
    private XmlHelper xmlHelper;

    @BeforeEach
    public void init() {
        ReflectionTestUtils.setField(this.xmlHelper, "path", "data/");
    }

    @Test
    public void shouldReturnReadingsFromCsvFile() throws IOException {

        XmlReadings readings = this.xmlHelper.unmarshall("2016-readings.xml");

        assertThat(readings.getReadings().size() == 1);
    }
}
