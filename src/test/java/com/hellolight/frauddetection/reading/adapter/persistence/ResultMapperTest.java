package com.hellolight.frauddetection.reading.adapter.persistence;

import com.hellolight.frauddetection.reading.domain.Result;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.YearMonth;

import static org.assertj.core.api.Assertions.assertThat;

class ResultMapperTest {

    private final ResultEntityMapper mapper = Mappers.getMapper(ResultEntityMapper.class);

    @Test
    void preservesYearMonthWhenMappingToAndFromPersistence() {
        Result source = new Result("client-1", YearMonth.of(2016, 9), 500L, 200.0);

        ResultEntity entity = mapper.toEntity(source);
        Result restored = mapper.toDomain(entity);

        assertThat(entity.getId()).isNull();
        assertThat(entity.getPeriod()).hasToString("2016-09-01");
        assertThat(restored).isEqualTo(source);
    }
}
