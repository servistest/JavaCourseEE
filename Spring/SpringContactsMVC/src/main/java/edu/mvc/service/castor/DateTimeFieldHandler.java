package edu.mvc.service.castor;

import org.exolab.castor.mapping.GeneralizedFieldHandler;
import org.exolab.castor.mapping.ValidityException;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * Created by ALex on 09.10.2016.
 */
public class DateTimeFieldHandler extends GeneralizedFieldHandler {
    private static String dateFormatPattern;

    @Override
    public void setConfiguration(Properties config) throws ValidityException {
        dateFormatPattern = config.getProperty("date-format");
    }

//    @Override
//    public Object convertUponGet(Object o) {
//        DateTime dateTime = (DateTime) o;
//        return format(dateTime);
//    }
    @Override
    public Object convertUponGet(Object o) {
        Date dateTime = (Date) o;
        return format(dateTime);
    }



//    @Override
//    public Object convertUponSet(Object o) {
//        String dateTimeString = (String) o;
//        return parse(dateTimeString);
//    }
    @Override
    public Object convertUponSet(Object o) {
        String dateTimeString = (String) o;
        return parse(dateTimeString);
    }


//    @Override
//    public Class<DateTime> getFieldType() {
//        return DateTime.class;
//    }
    @Override
    public Class<Date> getFieldType() {
        return Date.class;
    }

//    protected static String format(final DateTime dateTime) {
//        String dateTimeString = "";
//        if (dateTime != null) {
//            DateTimeFormatter dateTimeFormatter =
//                    DateTimeFormat.forPattern(dateFormatPattern);
//            dateTimeString = dateTimeFormatter.print(dateTime);
//        }
//        return dateTimeString;
//    }
    protected static String format(final Date dateTime) {
        String dateTimeString = "";
        if (dateTime != null) {
            dateTimeString=new SimpleDateFormat(dateFormatPattern).format(dateTime);
        }
        return dateTimeString;
    }

//    protected static DateTime parse(final String dateTimeString) {
//        DateTime dateTime = new DateTime();
//        if (dateTimeString != null) {
//            DateTimeFormatter dateTimeFormatter =
//                    DateTimeFormat.forPattern(dateFormatPattern);
//            dateTime = dateTimeFormatter.parseDateTime(dateTimeString);
//        }
//        return dateTime;
//    }
        protected static Date parse(final String dateTimeString)  {
            Date dateTime = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat(dateFormatPattern);
            if (dateTimeString != null) {
                try {
                    dateTime=formatter.parse(dateTimeString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            return dateTime;
}
}
