package com.dodo.kanbagis.utils;

import androidx.annotation.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

public class StringUtils {
    private static String[] dateFormatters = new String[]{"dd.MM.yyyy", "yyyy-MM-dd", "yyyy-MM-dd HH:mm"};

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static boolean isGreaterThanSix(@NonNull String str) {
        return str.length()<=6;
    }

    public static String emptyDefault(String str, String def) {
        return isNullOrEmpty(str) ? def : str;
    }

    public static boolean isEmail(String str) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (str == null)
            return false;
        return pat.matcher(str).matches();
    }

    public static Date parseDate(String date) {
        try {
            for (String fmt : dateFormatters) {
                SimpleDateFormat dateFormat = new SimpleDateFormat(fmt, Locale.getDefault());
                return dateFormat.parse(date);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String formatDate(Date date, String format) {
        SimpleDateFormat toFormat = new SimpleDateFormat(format, Locale.getDefault());
        return toFormat.format(date);
    }

    public static String formatDate(String date, String format) {
        Date dt = parseDate(date);
        if (dt == null) return date;

        SimpleDateFormat toFormat = new SimpleDateFormat(format, Locale.getDefault());
        return toFormat.format(dt);
    }
}
