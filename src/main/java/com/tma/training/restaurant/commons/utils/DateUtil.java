package com.tma.training.restaurant.commons.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static String getCurrentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(new Date());
    }
}
