package com.butterfly.spotter;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.util.Date;

/**
 * @author : Nadim
 * @since : 1/11/14
 */
public abstract class DateUtils {
    public static String DEFAULT_TZ = "GMT";
    public static String DATE_FORMAT = "yyyy-MM-dd";

    private DateUtils() {

    }

    public static Date convertDateWithTZ(Date date) {
        if (date == null) {
            return null;
        }
        return convertDateWithTZ(date, DEFAULT_TZ);
    }

    public static Date convertDateWithTZ(Date date, String tz) {
        if (date == null) {
            return null;
        }
        DateTime dt = new DateTime(date, DateTimeZone.forID(tz));
        return dt.toDate();
    }


}
