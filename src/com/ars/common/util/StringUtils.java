package com.ars.common.util;

public class StringUtils
{
    public static final String EMPTY = "";
    public static final String COMMA = ",";

    public static Double parse2Double(String str)
    {
        return Double.parseDouble(str.trim());
    }

    public static boolean isEmpty(String str)
    {
        return str == null || str.length() == 0;
    }

    public static String getFirstChar(String str)
    {
        if (isEmpty(str))
        {
            return EMPTY;
        }
        return str.substring(0, 1);
    }
}
