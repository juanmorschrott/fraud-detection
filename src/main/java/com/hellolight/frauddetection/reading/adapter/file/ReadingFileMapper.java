package com.hellolight.frauddetection.reading.adapter.file;

import java.time.YearMonth;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.hellolight.frauddetection.reading.domain.Reading;

@Mapper(componentModel = "spring")
public interface ReadingFileMapper {

    @Mapping(target = "period", source = "period", qualifiedByName = "parseYearMonth")
    Reading toDomain(CsvReading csvReading);

    Reading toDomain(XmlReading xmlReading);

    @Named("parseYearMonth")
    default YearMonth parseYearMonth(String period) {
        return YearMonth.parse(period);
    }
}
