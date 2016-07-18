/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.framework.common.util;

import cn.vansky.framework.common.constant.Constant;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 * <code>DatUtil</code>
 *
 * @author CK
 */
public class DateUtil {
    public static final String yyyyMMdd = "yyyyMMdd";
    public static final String yyyy_MM_dd = "yyyy-MM-dd";
    public static final String ddMMyy = "ddMMyy";
    public static final String yyyyMM = "yyyyMM";
    public static final String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
    public static final String yyMMddHHmmss = "yyMMddHHmmss";
    public static final String yyyyMMddHH = "yyyyMMddHH";
    public static final String yyyyMMddHHmmss = "yyyyMMddHHmmss";
    public static final String HH_mm_ss = "HH:mm:ss";
    public static final String HHmmss = "HHmmss";
    public static final String HH_mm = "HH:mm";

    public static final SimpleDateFormat dateFormat = new SimpleDateFormat(yyyyMMdd);
    public static final SimpleDateFormat dateFormat2 = new SimpleDateFormat(yyyy_MM_dd);
    public static final SimpleDateFormat dateFormat3 = new SimpleDateFormat(ddMMyy);
    public static final SimpleDateFormat dateFormat4 = new SimpleDateFormat(yyyyMM);
    public static final SimpleDateFormat dateTimeFormat = new SimpleDateFormat(yyyy_MM_dd_HH_mm_ss);
    public static final SimpleDateFormat dateTimeFormat2 = new SimpleDateFormat(yyMMddHHmmss);
    public static final SimpleDateFormat dateTimeFormat3 = new SimpleDateFormat(yyyyMMddHH);
    public static final SimpleDateFormat dateTimeFormat4 = new SimpleDateFormat(yyyyMMddHHmmss);
    public static final SimpleDateFormat timeFormat = new SimpleDateFormat(HH_mm_ss);
    public static final SimpleDateFormat timeFormat2 = new SimpleDateFormat(HHmmss);
    public static final SimpleDateFormat timeFormat3 = new SimpleDateFormat(HH_mm);

    /**
     * 秒
     */
    public static final long SECOND_TIME = 1000;
    /**
     * 分钟
     */
    public static final long MINUTE_TIME = SECOND_TIME * 60;
    /**
     * 小时
     */
    public static final long HOUR_TIME = MINUTE_TIME * 60;
    /**
     * 天数
     */
    public static final long DAY_TIME = HOUR_TIME * 24;

    /**
     * 格式化日期
     * @param format 格式
     * @param date 日期字符串
     * @return 日期字符串
     */
    public static String format(String format, Date date) {
        return DateEnum.getFormatByString(format).format(date);
    }

    /**
     * 格式化日期
     * @param formatStr 格式
     * @param date 日期字符串
     * @return 日期字符串
     */
    public static Date parse(String formatStr, String date) {
        Date d = null;
        try {
            d = DateEnum.getFormatByString(formatStr).parse(date);
        } catch (ParseException e) {
            // ignore
        }
        return d;
    }

    /**
     * 指定日期加减 获取字符串日期时间
     *
     * @param date  日期
     * @param formatStr user-defined date format like "yyyy-MM-dd HH:mm:ss"
     * @param i   <p>年{@link java.util.Calendar#YEAR}</p>
     *            <p>月{@link java.util.Calendar#MONTH}</p>
     *            <p>周{@link java.util.Calendar#WEEK_OF_YEAR}</p>
     *            <p>天{@link java.util.Calendar#DATE}</p>
     *            <p>小时{@link java.util.Calendar#HOUR}</p>
     *            <p>分钟{@link java.util.Calendar#MINUTE}</p>
     *            <p>秒{@link java.util.Calendar#SECOND}</p>
     * @param j         加减数量
     * @return 字符串日期时间
     */
    public static String getDateStr(Date date, String formatStr, int i, int j) {
        Date newDate = getDateByCalendar(date, i, j);
        return DateEnum.getFormatByString(formatStr).format(newDate);
    }

    /**
     * 是否为闰年
     *
     * @param year 年份
     * @return <code>boolean</code>
     * <p>是<code>true</code> 否<code>false</code></p>
     */
    public static boolean isLeapYear(int year) {
        return (year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0));
    }

    /**
     * 某月最大天数
     *
     * @param year  年份
     * @param month 月份
     * @return 某月最大天数
     */
    public static int maxDayOfMonth(int year, int month) {
        if (month <= 0 || month > 12) {
            return -1;
        }
        int day = 0;
        switch (month) {
            case 2:
                day = 28;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                day = 30;
                break;
            default:
                day = 31;
        }
        if (month == 2 && isLeapYear(year)) {
            day++;
        }
        return day;
    }

    /**
     * 获取当前年份
     *
     * @param date   需要计算的日期字符串<code>String</code>
     * @return 当前年份
     */
    public static int year(Date date) {
        return getCurrentCalendar(date).get(Calendar.YEAR);
    }

    /**
     * 获取当前月份
     *
     * @param date   需要计算的日期字符串<code>String</code>
     * @return 当前月份
     */
    public static int month(Date date) {
        return getCurrentCalendar(date).get(Calendar.MONTH) + 1;
    }

    /**
     * 获取当前天数
     *
     * @param date   需要计算的日期字符串<code>String</code>
     * @return 当前天数
     */
    public static int day(Date date) {
        return getCurrentCalendar(date).get(Calendar.DATE);
    }

    /**
     * 获取当前类型的数字
     *
     * @param date   需要计算的日期字符串<code>String</code>
     * @param i   <p>年{@link java.util.Calendar#YEAR}</p>
     *               <p>月{@link java.util.Calendar#MONTH}</p>
     *               <p>周{@link java.util.Calendar#WEEK_OF_YEAR}</p>
     *               <p>天{@link java.util.Calendar#DATE}</p>
     *               <p>小时{@link java.util.Calendar#HOUR}</p>
     *               <p>分钟{@link java.util.Calendar#MINUTE}</p>
     *               <p>秒{@link java.util.Calendar#SECOND}</p>
     * @return 当前类型的数字
     */
    public static int getTimeNumber(Date date, int i) {
        return getCurrentCalendar(date).get(i);
    }

    /**
     * 获取本周第几天
     *
     * @param date   需要计算的日期字符串<code>yyyy-MM-dd</code>
     * @return 获取本周第几天
     */
    public static int week(Date date) {
        int week = getCurrentCalendar(date).get(Calendar.DAY_OF_WEEK) - 1;
        if (0 == week) {
            week = 7;
        }
        return week;
    }

    /**
     * 获取当前季度
     *
     * @param date      需要计算的日期字符串<code>yyyy-MM-dd</code>
     * @return 当前季度
     */
    public static int getQuarter(Date date) {
        return (month(date) - 1) / 3 + 1;
    }

    /**
     * 获取当前季度所在的天数
     *
     * @param date      需要计算的日期字符串<code>yyyy-MM-dd</code>
     * @return 当前季度所在的天数
     */
    public static int dayOfQuarter(Date date) {
        int month = month(date);
        int year = year(date);
        int day = 0;
        for (int i = month - (month - 1) % 3; i < month; i++) {
            day += maxDayOfMonth(year, i);
        }
        day += day(date);
        return day;
    }

    /**
     * 返回日期自公元0年开始以来的天数
     *
     * @param date      需要计算的日期字符串<code>yyyy-MM-dd</code>
     * @return 日期自公元0年开始以来的天数
     */
    public static int days(Date date) {
        int days = 0;
        int year = year(date) - 1;
        days += year * 365;// one year have 365 day
        days += year / 4;// 4 year one leap year
        days -= year / 100;// 100 year no leap year
        days += year / 400;// 400 year one leap year
        days += dayOfYear(date);
        return days;
    }

    /**
     * 获取当天在当年的天数
     *
     * @param date      需要计算的日期字符串<code>yyyy-MM-dd</code>
     * @return 当天在当年的天数
     */
    public static int dayOfYear(Date date) {
        int month = month(date);
        int year = year(date);
        int day = 0;
        for (int i = 1; i < month; i++) {
            day += maxDayOfMonth(year, i);
        }
        day += day(date);
        return day;
    }

    /**
     * 获取月份第一天
     * 根据指定日期字符串减去<code>n</code>月
     *
     * @param date      需要计算的日期字符串<code>yyyy-MM-dd</code>
     * @param n         计算数量
     * @return 月份第一天
     */
    public static String dateRedMonthBegin(Date date, int n) {
        String str = "";
        int year = year(date);
        int month = month(date);
        if (n >= 12) {
            int m = n % 12;
            int s = n / 12;
            month = 12 - m + month;
            year = year - s;
        } else {
            month = month - n;
        }
        if (month == 0) {
            year = year - 1;
            month = 12;
        }
        if (month < 10) {
            str = "0" + month;
        } else {
            str = month + "";
        }
        return year + "-" + str + "-01";
    }

    /**
     * 获取月份最大天
     * 根据指定日期字符串减去<code>n</code>月
     *
     * @param date      需要计算的日期字符串<code>yyyy-MM-dd</code>
     * @param n         计算数量
     * @return 月份最大天
     */
    public static String dateRedMonthEnd(Date date, int n) {
        String str = "";
        int year = year(date);
        int month = month(date);
        if (n >= 12) {
            int m = n % 12;
            int s = n / 12;
            month = 12 - m + month;
            year = year - s;
        } else {
            month = month - n;
        }
        if (month == 0) {
            year = year - 1;
            month = 12;
        }
        if (month < 10) {
            str = "0" + month;
        } else {
            str = month + "";
        }
        return year + "-" + str + "-" + maxDayOfMonth(year, month);
    }

    /**
     * 通过{@link java.util.Calendar}获取日期
     *
     * @param date 需要计算的日期<code>date</code>
     * @param i    <p>年{@link java.util.Calendar#YEAR}</p>
     *             <p>月{@link java.util.Calendar#MONTH}</p>
     *             <p>周{@link java.util.Calendar#WEEK_OF_YEAR}</p>
     *             <p>天{@link java.util.Calendar#DATE}</p>
     *             <p>小时{@link java.util.Calendar#HOUR}</p>
     *             <p>分钟{@link java.util.Calendar#MINUTE}</p>
     *             <p>秒{@link java.util.Calendar#SECOND}</p>
     * @param j    加减数量
     * @return 获取日期
     */
    public static Date getDateByCalendar(Date date, int i, int j) {
        Calendar c = getCurrentCalendar(date);
        c.add(i, j);
        return c.getTime();
    }

    /**
     * 获取Calendar
     * @param date 日期
     * @return Calendar
     */
    private static Calendar getCurrentCalendar(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c;
    }

    /**
     * 计算2个日期相差数
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @param time 类型
     * @see cn.vansky.framework.common.util.DateUtil#SECOND_TIME 秒
     * @see cn.vansky.framework.common.util.DateUtil#MINUTE_TIME 分钟
     * @see cn.vansky.framework.common.util.DateUtil#HOUR_TIME 小时
     * @see cn.vansky.framework.common.util.DateUtil#DAY_TIME 天数
     * @return 小时数
     */
    public static long getBetweenDiff(Date startDate, Date endDate, long time) {
        long startDateTime = startDate.getTime();
        long endDateTime = endDate.getTime();
        long hours = (endDateTime - startDateTime) / time;
        return hours;
    }

    /**
     * 验证DB默认日期
     * @param date 日期
     * @return true：是,false：否
     */
    public static boolean isDBDefaultDate(Date date) {
        if (null == date) {
            return false;
        }
        String dbDefault = format(yyyy_MM_dd_HH_mm_ss, date);
        if (Constant.DB_DEFAULT_DATE.equals(dbDefault)) {
            return true;
        }
        return false;
    }

    public static enum DateEnum {
        yyyyMMdd(DateUtil.yyyyMMdd, DateUtil.dateFormat),
        yyyy_MM_dd(DateUtil.yyyy_MM_dd, DateUtil.dateFormat2),
        ddMMyy(DateUtil.ddMMyy, DateUtil.dateFormat3),
        yyyyMM(DateUtil.yyyyMM, DateUtil.dateFormat4),
        yyyy_MM_dd_HH_mm_ss(DateUtil.yyyy_MM_dd_HH_mm_ss, DateUtil.dateTimeFormat),
        yyMMddHHmmss(DateUtil.yyMMddHHmmss, DateUtil.dateTimeFormat2),
        yyyyMMddHH(DateUtil.yyyyMMddHH, DateUtil.dateTimeFormat3),
        yyyyMMddHHmmss(DateUtil.yyyyMMddHHmmss, DateUtil.dateTimeFormat4),
        HH_mm_ss(DateUtil.HH_mm_ss, DateUtil.timeFormat),
        HHmmss(DateUtil.HHmmss, DateUtil.timeFormat2),
        HH_mm(DateUtil.HH_mm, DateUtil.timeFormat3);

        private String dateEnum;

        private SimpleDateFormat simpleDateFormat;

        private DateEnum(String dateEnum, SimpleDateFormat simpleDateFormat) {
            this.dateEnum = dateEnum;
            this.simpleDateFormat = simpleDateFormat;
        }

        /**
         * 通过<code>DateEnum</code>获取{@link java.text.SimpleDateFormat}
         *
         * @param formatStr 日期枚举<code>DateEnum</code>
         * @return <code>SimpleDateFormat</code>
         */
        public static SimpleDateFormat getFormatByDateEnum(DateEnum formatStr) {
            for (DateEnum dateEnum : DateEnum.values()) {
                if (dateEnum.getDateEnum().equals(formatStr.dateEnum)) {
                    return dateEnum.simpleDateFormat;
                }
            }
            return DateEnum.yyyy_MM_dd_HH_mm_ss.simpleDateFormat;
        }

        /**
         * 通过<code>String</code>获取{@link java.text.SimpleDateFormat}
         *
         * @param formatStr 日期字符串<code>String</code>
         * @return <code>SimpleDateFormat</code>
         */
        public static SimpleDateFormat getFormatByString(String formatStr) {
            for (DateEnum dateEnum : DateEnum.values()) {
                if (dateEnum.getDateEnum().equals(formatStr)) {
                    return dateEnum.simpleDateFormat;
                }
            }
            return DateEnum.yyyy_MM_dd_HH_mm_ss.simpleDateFormat;
        }

        public String getDateEnum() {
            return dateEnum;
        }

        public SimpleDateFormat getSimpleDateFormat() {
            return simpleDateFormat;
        }
    }
}
