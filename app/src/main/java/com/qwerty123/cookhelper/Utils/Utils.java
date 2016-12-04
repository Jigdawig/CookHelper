package com.qwerty123.cookhelper.Utils;

import android.content.Context;

public class Utils
{
    private static Context applicationContext = null;

    public static void setApplicationContext(Context context)
    {
            applicationContext = context;
    }

    public static Context getApplicationContext()
    {
        return applicationContext;
    }

    public static boolean isAlpha(String string)
    {
        return string.matches("[a-zA-Z]+");
    }

    public static boolean isNum(String string)
    {
        return string.matches("[0-9]+");
    }


    public static String createDisplayName(String name)
    {
       StringBuilder builder = new StringBuilder();

            for(int i = 0; i < name.length(); ++i)
            {
                if(name.charAt(i) == '_')
                {
                    builder.append(' ');
                }
                else
                {
                    builder.append(name.charAt(i));
                }
            }

            return builder.toString();
    }
}
