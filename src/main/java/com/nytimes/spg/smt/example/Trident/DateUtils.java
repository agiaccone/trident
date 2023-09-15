package com.nytimes.spg.smt.example.Trident;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public  static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

    public static Date addDaysToDate(Date startDate, Integer addDays) {
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.add(Calendar.DATE, addDays);
        return c.getTime();
    }

    public static Date subtractDaysToDate(Date startDate, Integer subDays) {
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.add(Calendar.DATE, (-1 * subDays));
        return c.getTime();
    }

}
