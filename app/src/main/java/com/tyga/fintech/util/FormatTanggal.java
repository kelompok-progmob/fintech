package com.tyga.fintech.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class FormatTanggal {

    public static String formatTanggal(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("DD MM YYYY");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        return dateFormat.format(date);
    }

    public static String formatJam(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("kk:mm");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        return dateFormat.format(date);
    }

}
