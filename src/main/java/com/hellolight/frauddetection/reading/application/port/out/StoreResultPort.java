package com.hellolight.frauddetection.reading.application.port.out;

import com.hellolight.frauddetection.reading.domain.Result;

import java.util.List;

public interface StoreResultPort {

    List<Result> save(List<Result> result);
}
