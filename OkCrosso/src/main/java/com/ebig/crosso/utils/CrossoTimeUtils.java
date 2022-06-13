package com.ebig.crosso.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Pattern;


/**
 * Created by Administrator on 2017/3/7.
 */

public class CrossoTimeUtils {


    public static long time2string(String datename) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(datename);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date==null){
            return 0;
        }
        return date.getTime();
    }




    public static String getDateFormat(long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(time);
    }




    public static String toJSONArray(List<String> stringlist) {
        JSONArray array = new JSONArray(stringlist);
        return array.toString();
    }

    public static ArrayList<String> get7DayList() {
        String sencodtime = getlong2StringDate(System.currentTimeMillis());
        ArrayList<String> list = new ArrayList<String>();
        list.add(sencodtime + ":00");
        for (int i = 0; i < 6; i++) {
            sencodtime = getSpecifiedDayBefore(sencodtime);
            list.add(sencodtime + ":00");
        }
        return list;
    }

    public static long string2longDate(String string) {
        return Long.parseLong(string);
    }

    public static String getlong2StringDate(long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(time).split(" ")[0];
    }

    public static String getlong2StringTime(long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(time).split(" ")[1];
    }

    public static String getSpecifiedDayBefore(String specifiedDay) {
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - 1);

        String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        return dayBefore;
    }


    public static String getPathName(String params) {
        String date = params.replace("VSTA004952VTDSE_", "").replace(":", " ").replace("_", ":");
        date.substring(0, date.length() - 2);
        long time = time2string(date.substring(0, date.length() - 3));
        return time + "-" + (time + 10000);
    }

    //时分秒转换
    public static String getHMS_String(long time) {
        // time 毫秒数
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");//初始化Formatter的转换格式。
        return formatter.format(time);
        // 可得hms 的值为  00：05：00. 即0时5分0秒。
    }

    public static long getHMS_long(String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse("0000-00-00 " + time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date.getTime();
    }


    public static List<String> getJSONObjectKey(String json) {
        List<String> list = new ArrayList<>();
        try {
            JSONObject object = new JSONObject(json);
            Iterator<String> iterator = object.keys();
            while (iterator.hasNext()) {
                list.add(iterator.next().toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static String getDateString(String string) {
        return string.substring(0, 4) + "-" + string.substring(4, 6) + "-" + string.substring(6, 8);
    }

    public static String getDateString2Sceond(String time) {

        if (time == null) {
            return "";
        }

        return time.substring(0, 4) + "-" + time.substring(4, 6) + "-" + time.substring(6, 8) +
                " " + time.substring(8, 10) + ":" + time.substring(10, 12) + ":" + time.substring(12, 14);

    }


    public static String TimeStamp2Date(String timestampString) {
        Long timestamp = Long.parseLong(timestampString) * 1000;
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA).format(new Date(timestamp));
        return date;
    }

    public static String getDate(String timestampString) {
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(timestampString));
        return date;
    }
    /**
     * 获取上个月的月份 201706
     */
    public static String getthisMonth(String time) {
        int YearInt = Integer.parseInt(time.substring(0, 4));
        String YearStr;
        String MonthStr = String.valueOf(Integer.parseInt(time.substring(4, 6)) );
        if (MonthStr.equals("00") || MonthStr.equals("0")) {
            YearStr = String.valueOf(YearInt - 1);
        } else {
            YearStr = String.valueOf(YearInt);
        }
        if (MonthStr.equals("00") || MonthStr.equals("0")) {
            MonthStr = "12";
        }
        if (MonthStr.length() == 1) {
            MonthStr = "0" + MonthStr;
        }
        return YearStr + MonthStr;
    }

    /**
     * 获取上个月的月份 201706
     */
    public static String getlastMonth(String time) {
        int YearInt = Integer.parseInt(time.substring(0, 4));
        String YearStr;
        String MonthStr = String.valueOf(Integer.parseInt(time.substring(4, 6)) - 1);

        if (MonthStr.equals("00") || MonthStr.equals("0")) {
            YearStr = String.valueOf(YearInt - 1);
        } else {
            YearStr = String.valueOf(YearInt);
        }

        if (MonthStr.equals("00") || MonthStr.equals("0")) {
            MonthStr = "12";
        }
        if (MonthStr.length() == 1) {
            MonthStr = "0" + MonthStr;
        }
        return YearStr + MonthStr;
    }

    //  判断字符串是否为数字
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    public static String get6FormDate(String time) {
        return time.substring(0, 4) + "-" + time.substring(4, 6) + "-" + time.substring(6, 8);
    }


    public static String get6FormMonth(String time) {
        return   time.substring(4, 6)+ "/" +time.substring(0, 4);
    }


    public static String getLastSecond(long time) {
        SimpleDateFormat format = new SimpleDateFormat("mm:ss");
        return format.format(time);
    }

    //想要获取日志的日期 格式：201703
    public static String getMonthString() {
        Calendar cal = Calendar.getInstance();
//        Date now = ca.getTime();
//        SimpleDateFormat sf = new SimpleDateFormat("yyyyMM");
//        return sf.format(now);
        int day = cal.get(Calendar.DATE);       //日
        int month = cal.get(Calendar.MONTH) + 1;//月
        int year = cal.get(Calendar.YEAR);      //年
        if (month>9){
            return year+""+month;
        }else {
            return year+"0"+month;
        }
    }

    //想要获取日志的日期 格式：20170303
    public static String getDayhString() {
        Calendar ca = Calendar.getInstance();
        Date now = ca.getTime();
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        return sf.format(now);
    }

    //想要获取日志的日期 格式：20170303
    public static String getDayStringWithLine() {
        Calendar ca = Calendar.getInstance();
        Date now = ca.getTime();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        return sf.format(now);
    }

    //想要获取日志的日期 格式：20170303
    public static String getMonthStringWithLine() {
        Calendar ca = Calendar.getInstance();
        Date now = ca.getTime();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
        return sf.format(now);
    }

    public static ArrayList getMapTimeArr(String date,int timeSegment) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = timeSegment*6; i < (timeSegment+1)*6; i++) {
            String j = i + "";
            if (j.length() == 1) {
                j = "0" + j;
            }
            list.add(date + ":" + j);

        }
        for (int i = 0; i < list.size(); i++) {
         }

        return list;

    }
    public static long TimeX_Value=0;
    private static long timeX=0;
    public static long getTimeX() {
            String charT = "";
            String nub = "";
            TimeZone tz = TimeZone.getDefault();//GMT+08:00
            String s = tz.getDisplayName(false, TimeZone.SHORT);
            s = s.replace("GMT", "");
            charT = s.substring(0, 1);
            nub = s.substring(1, 3);
            if (nub.startsWith("0")) {
                nub = nub.substring(1, 2);
            }
            System.out.println("===" + charT + nub);
            timeX=Long.valueOf(nub);
            if (charT.equals("-")) {
                timeX=0-timeX;
            }
        return timeX*3600000;
    }
    public static  String getTimeByHour2(long time,int hour){
        //time-1000*60*60;
        return getRightString(getDateFormat(time-1000*60*60*hour- CrossoTimeUtils.getTimeX()));
    }

    private static String getRightString(String string){
        //2017-06-20 17:46:34
        return string.split(" ")[0]+":"+string.split(" ")[1].substring(0,2);
    }
    public static String getToday() {
        long time = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(time);
        return simpleDateFormat.format(date);

    }
}

