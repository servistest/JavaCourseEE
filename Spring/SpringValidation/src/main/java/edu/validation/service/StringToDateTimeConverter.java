package edu.validation.service;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import javax.annotation.PostConstruct;

/**
 * Created by Admin on 30.09.2016.
 */
public class StringToDateTimeConverter implements Converter<String,DateTime> {
    private static final String DEFAULT_DATE_PATTERN="yyyy-mm-dd";
    private String datePattern=DEFAULT_DATE_PATTERN;
    private DateTimeFormatter dateTimeFormat;

    public String getDatePattern() {
        return datePattern;
    }

    @Autowired(required = false)
    public void setDatePattern(String datePattern) {
        this.datePattern = datePattern;
    }

    @PostConstruct
    public void init(){
        dateTimeFormat= DateTimeFormat.forPattern(datePattern);
    }


    @Override
    public DateTime convert(String dateString) {
        return dateTimeFormat.parseDateTime(dateString);
    }
}
