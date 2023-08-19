package com.hellolight.frauddetection.common;

import com.hellolight.frauddetection.reading.adapter.in.file.CsvReadingToReadingConverter;
import com.hellolight.frauddetection.reading.adapter.in.file.XmlReadingsToReadingsConverter;
import com.hellolight.frauddetection.reading.adapter.out.persistence.ResultEntityToResultConverter;
import com.hellolight.frauddetection.reading.adapter.out.persistence.ResultToResultEntityConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new CsvReadingToReadingConverter());
        registry.addConverter(new XmlReadingsToReadingsConverter());
        registry.addConverter(new ResultToResultEntityConverter());
        registry.addConverter(new ResultEntityToResultConverter());
    }
}
