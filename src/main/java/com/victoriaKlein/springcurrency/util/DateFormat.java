package com.victoriaKlein.springcurrency.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.JulianFields;
import java.util.*;

public class DateFormat {
    public static String getPrevDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(System.currentTimeMillis()- 1 * 24 * 60 * 60 * 1000));
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String prevDate = simpleDateFormat.format(calendar.getTime());
        return prevDate;
    }
    public static String getActualPublishDate (Integer timestamp){
        Date d = new Date((long)timestamp*1000);
        return d.toString();
    }

}
