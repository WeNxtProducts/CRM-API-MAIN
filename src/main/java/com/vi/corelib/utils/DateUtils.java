package com.vi.corelib.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public abstract class DateUtils {

    public static Date getDate(Date date) {
        return date !=null ? date : new Date();
    }
    public static Date getFirstDateOfMonth(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(getDate(date));
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }
    public static Date getLastDateOfMonth(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH) -1);
        return cal.getTime();
    }
    public static Date getFirstDayOfWeek(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.setFirstDayOfWeek(Calendar.SUNDAY);
        return cal.getTime();
    }
    public static Date getLastDayOfWeek(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.setFirstDayOfWeek(Calendar.SATURDAY);
        return cal.getTime();
    }
    public static String formatDate(Date date) {
        return new SimpleDateFormat("YYYY-MM-DD").format(date);
    }

    /* All Date function should be written here  User stes */


}
