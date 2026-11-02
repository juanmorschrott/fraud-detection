package com.hellolight.frauddetection.reading.adapter.persistence;

import com.hellolight.frauddetection.reading.domain.Result;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;
import java.time.YearMonth;

@Mapper(componentModel = "spring")
public interface ResultEntityMapper {

    @Mapping(target = "id", ignore = true)
    ResultEntity toEntity(Result result);

    Result toDomain(ResultEntity entity);

    default LocalDate toLocalDate(YearMonth period) {
        return period == null ? null : period.atDay(1);
    }

    default YearMonth toYearMonth(LocalDate date) {
        return date == null ? null : YearMonth.from(date);
    }
}
