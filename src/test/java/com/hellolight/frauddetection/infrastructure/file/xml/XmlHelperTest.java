package com.hellolight.frauddetection.infrastructure.file.xml;

import com.hellolight.frauddetection.domain.model.Reading;
import com.hellolight.frauddetection.infrastructure.file.helper.XmlHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class XmlHelperTest {

    @InjectMocks
    private XmlHelper xmlHelper;

    @Test
    public void testUnMarshalling() throws IOException, JAXBException {

        //List<Reading> xmlReadings = xmlHelper.unmarshallXml("data/2016-readings.xml");
    }

}
