package com.hellolight.frauddetection.reading.application.port;

import com.hellolight.frauddetection.reading.domain.Reading;

import java.io.IOException;
import java.util.List;

public interface LoadReadingsPort {

    List<Reading> load(String fileName) throws IOException;
}
