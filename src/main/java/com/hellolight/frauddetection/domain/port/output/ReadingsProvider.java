package com.hellolight.frauddetection.domain.port.output;

import com.hellolight.frauddetection.domain.model.Reading;

import java.io.IOException;
import java.util.List;

public interface ReadingsProvider {

    List<Reading> getReadings(final String fileName) throws IOException;

}
