package com.qwerty123.cookhelper;

/**
 * Created by David on 2016-12-01.
 */
public class Utils
{
    public static boolean isAlpha(String string)
    {
        return string.matches("[a-zA-Z]+");
    }

    public static boolean isNum(String string)
    {
        return string.matches("[0-9]+");
    }
}
