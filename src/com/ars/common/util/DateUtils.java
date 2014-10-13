package com.ars.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils
{
    public static Date parse2Date(String inputedDate)
    {
        try
        {
            return new SimpleDateFormat("yyyy-MM-dd").parse(inputedDate);
        }
        catch (ParseException e)
        {
            return new Date();
        }

    }

    public static Date currentDate()
    {
        return new Date();

    }

    public static String parse2String(Date date)
    {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);

    }

    public static Date addDate(int addYear, int addMonth, int addDay)
    {
        Calendar instance = Calendar.getInstance();
        int year = instance.get(Calendar.YEAR);// 获取年份
        int month = instance.get(Calendar.MONTH);// 获取月份
        int day = instance.get(Calendar.DATE);// 获取日
        instance.set(year + addYear, month + addMonth, day + addDay);
        return instance.getTime();
    }

    public static String currentDateString()
    {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }

    // 2个日期相差多少月
    public static int getMonthDiff(Date minDate, Date maxDate)
    {
        if (minDate.after(maxDate))
        {
            Date tmp = minDate;
            minDate = new Date(maxDate.getTime());
            maxDate = new Date(tmp.getTime());
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(minDate);
        int year1 = calendar.get(Calendar.YEAR);
        int month1 = calendar.get(Calendar.MONTH);
        int day1 = calendar.get(Calendar.DATE);

        calendar.setTime(maxDate);
        int year2 = calendar.get(Calendar.YEAR);
        int month2 = calendar.get(Calendar.MONTH);
        int day2 = calendar.get(Calendar.DATE);

        int months = 0;
        if (day2 >= day1)
        {
            months = month2 - month1;
        }
        else
        {
            months = month2 - month1 - 1;
        }
        return (year2 - year1) * 12 + months;
    }

}
