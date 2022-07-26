package com.hellolight.frauddetection.infrastructure.db.adapter;

import com.hellolight.frauddetection.domain.model.Reading;
import com.hellolight.frauddetection.domain.port.output.ReadingsProvider;
import com.hellolight.frauddetection.infrastructure.db.repository.ReadingsRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This service just has illustration purposes. I want to show how another
 * output system can be integrated in a hexagonal architectured application.
 */
public class DBReadingsAdapter implements ReadingsProvider {

    private ReadingsRepository repository;

    public DBReadingsAdapter(final ReadingsRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Reading> getReadings(String fileName) throws IOException {

        // List<Reading> readings = this.repository.findAll();

        return new ArrayList<>();
    }
}
