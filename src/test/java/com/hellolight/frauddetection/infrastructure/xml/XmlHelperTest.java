package com.hellolight.frauddetection.infrastructure.xml;

import com.hellolight.frauddetection.domain.model.Reading;
import com.hellolight.frauddetection.infrastructure.xml.converter.XmlReadingsToReadingsConverter;
import com.hellolight.frauddetection.infrastructure.xml.helper.XmlHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class XmlHelperTest {

    @InjectMocks
    private XmlHelper xmlHelper;

    @Mock
    private XmlReadingsToReadingsConverter converter;

    @BeforeEach
    public void init() {
        ReflectionTestUtils.setField(this.xmlHelper, "path", "./data/");
    }

    @Test
    public void shouldReturnReadingsFromCsvFile() throws IOException {

        List<Reading> readings = this.xmlHelper.unmarshall("2016-readings.xml");

        assertThat(readings.size() > 0);
    }
}
