package com.hellolight.frauddetection.reading.application.port.in;

import com.hellolight.frauddetection.reading.domain.Reading;

import java.io.IOException;
import java.util.List;

public interface ReadingsPort {

    List<Reading> getReadings(final String fileName) throws IOException;
}
