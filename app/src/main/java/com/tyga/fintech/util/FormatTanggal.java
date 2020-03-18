package com.tyga.fintech.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class FormatTanggal {

    public static String formatTanggal(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("DD MM yyyy");
        Date date1 = null;
        try {
            date1 = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return String.valueOf(date1);
    }

    public static String formatJam(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("kk:mm");
        Date date1 = null;
        try {
            date1 = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return String.valueOf(date1);
    }

}
