package com.hellolight.frauddetection.reading.application.port.out;

import com.hellolight.frauddetection.reading.domain.Result;

import java.util.List;

public interface StoreResultPort {

    void save(List<Result> result);
}
