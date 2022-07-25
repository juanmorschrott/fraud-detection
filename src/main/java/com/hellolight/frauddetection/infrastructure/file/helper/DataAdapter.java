package com.hellolight.frauddetection.infrastructure.file.helper;

import com.hellolight.frauddetection.domain.model.Reading;

import java.io.IOException;
import java.util.List;

public interface DataAdapter {

    List<Reading> unmarshall(final String fileName) throws IOException;
}
