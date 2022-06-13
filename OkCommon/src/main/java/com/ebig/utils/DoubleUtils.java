package com.ebig.utils;

import java.text.DecimalFormat;

public class DoubleUtils {
    public static double with2(double d){
        DecimalFormat df = new DecimalFormat("#.00");
        return Double.valueOf(df.format(d));
    }
}
