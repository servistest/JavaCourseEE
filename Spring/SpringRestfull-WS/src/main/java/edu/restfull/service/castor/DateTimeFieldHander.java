package edu.restfull.service.castor;

import org.exolab.castor.mapping.GeneralizedFieldHandler;
import org.exolab.castor.mapping.ValidityException;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Properties;

/**
 * Created by ALex on 09.10.2016.
 */
public class DateTimeFieldHander extends GeneralizedFieldHandler {
    private static String dateFormatPattern;

    @Override
    public void setConfiguration(Properties config) throws ValidityException {
        dateFormatPattern = config.getProperty("date-format");
    }

    @Override
    public Object convertUponGet(Object o) {
        DateTime dateTime = (DateTime) o;
        return format(dateTime);
    }

    @Override
    public Object convertUponSet(Object o) {
        String dateTimeString = (String) o;
        return parse(dateTimeString);
    }

    @Override
    public Class<DateTime> getFieldType() {
        return DateTime.class;
    }

    protected static String format(final DateTime dateTime) {
        String dateTimeString = "";
        if (dateTime != null) {
            DateTimeFormatter dateTimeFormatter =
                    DateTimeFormat.forPattern(dateFormatPattern);
            dateTimeString = dateTimeFormatter.print(dateTime);

        }
        return dateTimeString;
    }

    protected static DateTime parse(final String dateTimeString) {
        DateTime dateTime = new DateTime();
        if (dateTimeString != null) {
            DateTimeFormatter dateTimeFormatter =
                    DateTimeFormat.forPattern(dateFormatPattern);
            dateTime = dateTimeFormatter.parseDateTime(dateTimeString);

        }
        return dateTime;
    }
}
