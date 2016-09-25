package com.inHere.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间格式转换工具
 */
public class DateUtil {

    public static String longToDate(long time){
        System.out.println(time);
        Date date = new Date(time);
        SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = format.format(date);
        System.out.println(str);
        return str;
    }

    public static String longToDate(String time){
        return longToDate(Long.parseLong(time));
    }

    public static void main(String[] args){
        longToDate(new Date().getTime());
    }

}
