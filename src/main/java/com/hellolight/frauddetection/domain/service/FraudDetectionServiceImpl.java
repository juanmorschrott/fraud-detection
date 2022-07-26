package com.hellolight.frauddetection.domain.service;

import com.hellolight.frauddetection.domain.exception.FraudDetectionException;
import com.hellolight.frauddetection.domain.model.Reading;
import com.hellolight.frauddetection.domain.model.Result;
import com.hellolight.frauddetection.domain.port.input.FraudDetectionService;
import com.hellolight.frauddetection.infrastructure.csv.adapter.CsvReadingsAdapter;
import com.hellolight.frauddetection.infrastructure.xml.adapter.XmlReadingsAdapter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ArrayList;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.averagingLong;
import static java.util.stream.Collectors.groupingBy;
import static org.apache.commons.lang3.StringUtils.EMPTY;

public class FraudDetectionServiceImpl implements FraudDetectionService {

    private static final String CSV = "csv";
    private static final String XML = "xml";

    private CsvReadingsAdapter csvReadingsAdapter;
    private XmlReadingsAdapter xmlReadingsAdapter;

    public FraudDetectionServiceImpl(final CsvReadingsAdapter csvReadingsAdapter, final XmlReadingsAdapter xmlReadingsAdapter) {
        this.csvReadingsAdapter = csvReadingsAdapter;
        this.xmlReadingsAdapter = xmlReadingsAdapter;
    }

    @Override
    public List<Result> detect(String fileName) throws IOException {

        List<Reading> readings = this.obtainReadingsByFileType(fileName);

        Map<String, Double> clientMeans = this.groupClientMeans(readings);

        return readings.stream()
                .filter(reading -> reading.getValue() > clientMeans.get(reading.getClientId()).floatValue() * 1.5)
                .map(reading -> Result.builder()
                        .clientId(reading.getClientId())
                        .month(reading.getPeriod().getMonth())
                        .suspiciousReading(reading.getValue())
                        .median(clientMeans.get(reading.getClientId()).floatValue())
                        .build())
                .collect(Collectors.toList());
    }

    private List<Reading> obtainReadingsByFileType(final String fileName) throws IOException {

        String extension = this.getFileExtension(fileName);

        if (!extension.equalsIgnoreCase(CSV) && !extension.equalsIgnoreCase(XML)) {
            throw new FraudDetectionException("Not valid file provided");
        }

        return switch (extension) {
            case CSV -> this.csvReadingsAdapter.getReadings(fileName);
            case XML -> this.xmlReadingsAdapter.getReadings(fileName);
            default -> new ArrayList<>();
        };
    }


    private String getFileExtension(final String path) {

        return Optional.ofNullable(path)
                .map(String::toLowerCase)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(path.lastIndexOf(".") + 1))
                .orElse(EMPTY);
    }

    private Map<String, Double> groupClientMeans(List<Reading> readings) {

        return Optional.ofNullable(readings)
                .orElseGet(ArrayList::new)
                .stream()
                .collect(groupingBy(Reading::getClientId, averagingLong(Reading::getValue)));
    }

}
