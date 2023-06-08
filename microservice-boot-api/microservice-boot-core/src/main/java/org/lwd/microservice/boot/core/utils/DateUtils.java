package org.lwd.microservice.boot.core.utils;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.exceptions.UtilException;
import cn.hutool.core.util.StrUtil;
import org.lwd.microservice.boot.core.constant.DateFormatConstant;

import java.lang.management.ManagementFactory;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 时间工具类
 * 多线程情况谨慎使用格式转换操作
 *
 * @author virtil
 */
public class DateUtils extends DateUtil {


    /**
     * 时间转字符串
     *
     * @param datePattern
     * @param aDate
     * @return
     */
    public static String convertDateToString(String datePattern, Date aDate) {
        if (aDate == null) {
            return null;
        }
        return (new SimpleDateFormat(datePattern)).format(aDate);
    }

    /**
     * 字符串转时间
     *
     * @param datePattern
     * @param strDate
     * @return
     * @throws ParseException
     */
    public static final Date convertStringToDate(String datePattern, String strDate) {
        SimpleDateFormat df = null;
        Date date = null;
        df = new SimpleDateFormat(datePattern);
        try {
            date = df.parse(strDate);
        } catch (ParseException pe) {
            pe.printStackTrace();
            throw new UtilException(pe);
        }
        return (date);
    }

    /**
     * 获取当前时间所在年的实际天数
     *
     * @return
     */
    public static int getYearDays(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, 2, 1);
        calendar.add(Calendar.DATE, -1);
        int day = calendar.get(Calendar.DATE);
        if (day == 29) {
            return 366;
        } else {
            return 365;
        }
    }


    /**
     * 获取指定日期（分钟）
     * minutes 大于0表示推迟几分钟
     * minutes 小于0表示提前几分钟
     *
     * @param date
     * @param minutes
     * @return
     */
    public static Date addMinutes(Date date, int minutes) {
        return offsetMinute(date, minutes);
    }

    /**
     * 获取指定日期（天）
     * day 大于0表示推后几天
     * day 小于0表示提前几天
     *
     * @param date
     * @param day
     * @return
     */
    public static Date addDate(Date date, int day) {
        return offsetDay(date, day);
    }

    /**
     * 获取指定日期（月）
     * month 大于0表示推后几个月
     * month 小于0表示提前几个月
     *
     * @param date
     * @param month
     * @return
     */
    public static Date addMonth(Date date, int month) {
        return offsetMonth(date, month);
    }

    /**
     * 获取指定日期（年）
     * month 大于0表示推后几个年
     * month 小于0表示提前几个年
     *
     * @param date
     * @param year
     * @return
     */
    public static Date addYear(Date date, int year) {
        return offset(date, DateField.YEAR, year);
    }

    /**
     * 将long型的日期转化为Date型
     *
     * @param time
     * @return
     */
    public static Date parseDate(long time) {
        Date rtnDate = new Date();
        rtnDate.setTime(time);
        return rtnDate;
    }

    /**
     * 查询某年第一天
     *
     * @param year
     * @return
     */
    public static Date getYearFirst(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }

    /**
     * 某年最后一天
     *
     * @param year
     * @return
     */
    public static Date getYearLast(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();
        return currYearLast;
    }

    /**
     * 某月第一天
     *
     * @param month
     * @return
     */
    public static Date getMonthFirst(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        return calendar.getTime();
    }

    /**
     * 某月最后一天
     *
     * @param month
     * @return
     */
    public static Date getMonthLast(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.roll(Calendar.DAY_OF_MONTH, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }


    /**
     * 某个季度第一天
     *
     * @param year
     * @param season
     * @return
     */
    public static Date getSeasonFirst(int year, int season) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        switch (season) {
            case 1:
                calendar.set(Calendar.MONTH, 0);
                break;
            case 2:
                calendar.set(Calendar.MONTH, 3);

                break;
            case 3:
                calendar.set(Calendar.MONTH, 6);

                break;
            case 4:
                calendar.set(Calendar.MONTH, 9);
                break;
            default:
                break;
        }
        return calendar.getTime();
    }

    /**
     * 某个季度最后一天
     *
     * @param year
     * @param season
     * @return
     */
    public static Date getSeasonLast(int year, int season) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        switch (season) {
            case 1:
                calendar.set(Calendar.MONTH, 2);
                break;
            case 2:
                calendar.set(Calendar.MONTH, 5);
                break;
            case 3:
                calendar.set(Calendar.MONTH, 8);

                break;
            case 4:
                calendar.set(Calendar.MONTH, 11);
                break;
            default:
                break;
        }
        calendar.roll(Calendar.DAY_OF_MONTH, -1);
        return calendar.getTime();

    }

    /**
     * 获取指定月份有多少天
     *
     * @param year  年份
     * @param month 月份
     * @return
     */
    public static int getDaysByYearMonth(int year, int month) {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }


    /**
     * 获取指定日期凌晨n点
     * day 大于0表示推后几天
     * day 小于0表示提前几天
     *
     * @param date
     * @param day
     * @param hour 指定几点
     * @return
     */
    public static Date nextDate(Date date, int day, int hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取某个时间点hour前后的时间
     *
     * @param date  当前时间
     * @param hours 当前时间的前后时间间隔 3：后三小时时间，-3：前三小时时间
     * @return Date
     */
    public static Date getTimeBeforeHours(Date date, int hours) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, hours);
        return cal.getTime();
    }

    /**
     * 根据字符串日期计算对应的星期
     *
     * @param date yyyy-MM-dd
     * @return
     */
    public static String getWeekToDate(String date) {
        int idx = LocalDate.parse(date).getDayOfWeek().getValue();
        return DateFormatConstant.CHINESE_WEEK_DAYS[idx - 1];
    }

    /**
     * 指定日期是否在开始时间和结束时间之间(包含开始和结束)
     *
     * @param date  待检验日期
     * @param begin 开始时间
     * @param end   结束时间
     * @return
     */
    public static boolean isInterval(LocalDate date, LocalDate begin, LocalDate end) {
        boolean interval = false;

        if (date.isEqual(begin) || date.isEqual(end) || (date.isAfter(begin)) && date.isBefore(end)) {
            interval = true;
        }

        return interval;
    }

    /**
     * 根据字符串日期计算对应的星期
     *
     * @param date
     * @return date(星期xx)
     */
    public static String getDateWeek(String date) {
        String dateWeek = getWeekToDate(date);
        return date + "(" + dateWeek + ")";
    }


    /**
     * 判断日期是周几
     *
     * @return
     */
    public static Integer getWeekByDate(Date date) {
        Integer week = null;
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int weekday = c.get(Calendar.DAY_OF_WEEK);
        if (weekday == 1) {
            week = 7;
        } else if (weekday == 2) {
            week = 1;
        } else if (weekday == 3) {
            week = 2;
        } else if (weekday == 4) {
            week = 3;
        } else if (weekday == 5) {
            week = 4;
        } else if (weekday == 6) {
            week = 5;
        } else if (weekday == 7) {
            week = 6;
        }
        return week;
    }

    /**
     * 验证日期时间字符串是否合法
     *
     * @param vaildDateTime 年-月-日 时:分:秒
     * @return
     */
    public static boolean vaildDateTimeString(String vaildDateTime) {
        if (StrUtil.isNotEmpty(vaildDateTime)) {
            Pattern p = Pattern.compile(DateFormatConstant.DATETIME_VAILD_RULE);
            Matcher m = p.matcher(vaildDateTime);
            return m.matches();
        }
        return false;
    }

    /**
     * 验证日期字符串是否合法
     *
     * @param vaildDate 年-月-日
     * @return
     */
    public static boolean vaildDateString(String vaildDate) {
        if (StrUtil.isNotEmpty(vaildDate)) {
            Pattern p = Pattern.compile(DateFormatConstant.DATE_VAILD_RULE);
            Matcher m = p.matcher(vaildDate);
            return m.matches();
        }
        return false;
    }


    /**
     * 获取相差天数
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static int getRangeDays(Date startTime, Date endTime) {
        Calendar scal = Calendar.getInstance();
        scal.setTime(startTime);
        Calendar ecal = Calendar.getInstance();
        ecal.setTime(endTime);
        int s = scal.get(Calendar.DAY_OF_MONTH);
        int e = ecal.get(Calendar.DAY_OF_MONTH);
        if (s == e) {
            return 0;
        } else {
            return e - s;
        }
    }

    /**
     * date2比date1多的天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDays(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 != year2) {//不同一年
            int timeDistance = 0;
            if (year1 <= year2) {
                for (int i = year1; i < year2; i++) {
                    if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {//闰年
                        timeDistance += 366;
                    } else {//不是闰年
                        timeDistance += 365;
                    }
                }
            } else {
                for (int i = year2; i < year1; i++) {
                    if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {//闰年
                        timeDistance -= 366;
                    } else {//不是闰年
                        timeDistance -= 365;
                    }
                }
            }
            return timeDistance + (day2 - day1);
        } else { //同一年
            //System.out.println("判断day2 - day1 : " + (day2 - day1));
            return day2 - day1;
        }
    }

    /**
     * 是否闰年
     *
     * @param year 年
     * @return 是否闰年
     */
    public static boolean isLeapYear(int year) {
        return new GregorianCalendar().isLeapYear(year);
    }

    /**
     * 通过时间秒毫秒数判断两个时间的间隔
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDaysByMillisecond(Date date1, Date date2) {
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24));
        return days;
    }

    /**
     * 获取相差整小时数
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static String getRangeHours(String startTime, String endTime) {
        String endReslut = null;
        long start = getTime(startTime);
        long end = getTime(endTime);
        long time = end - start;
        if (time > 0) {
            BigDecimal b = new BigDecimal(time);
            endReslut = b.divide(new BigDecimal(60 * 60 * 1000), BigDecimal.ROUND_HALF_UP).toString();
        }
        return endReslut;
    }

    /**
     * 获取相差半小时数
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static String getRangeSemihs(String startTime, String endTime) {
        String endReslut = null;
        long start = getTime(startTime);
        long end = getTime(endTime);
        long time = end - start;
        if (time > 0) {
            BigDecimal b = new BigDecimal(time);
            endReslut = b.divide(new BigDecimal(30 * 60 * 1000), BigDecimal.ROUND_HALF_UP).toString();
        }
        return endReslut;
    }

    /**
     * 字符串转时间戳
     *
     * @param str
     * @return
     */
    public static long getTime(String str) {
        SimpleDateFormat format = new SimpleDateFormat(DateFormatConstant.YYYYMMDD_HH_MM_SS);
        try {
            Date date = format.parse(str);
            return date.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取同一天内的时间段内对应的半小时数
     * 比如:2019-09-20 12:00:00  和 2019-09-20 13:00:00 他们之间的半小时数有2个,分别是12点到12点半,12点半到13点
     * 返回的数据根据区间返回的,一天有24小时,也就是48个半小时,区间就是0-47,返回的是对应的区间数
     * 比如:
     * 00:00:00 - 01:00:00 返回的就是[0,1]
     * 01:00:00 - 02:00:00 返回的就是[2,3]
     * 02:00:00 - 03:00:00 返回的就是[4,5]
     *
     * @param startTime 必须和结束时间是同一天,而且必须小于结束时间,如果不同天返回的是空数组
     * @param endTime   必须和结束时间是同一天,而且必须大于开始时间,如果不同天返回的是空数组
     * @return
     */
    public static List<Integer> getSemihAllNumber(Date startTime, Date endTime) {
        List<Integer> times = new ArrayList<>();
        String start = convertDateToString(DateFormatConstant.YYYYMMDD_HH_MM_SS, startTime);
        String end = convertDateToString(DateFormatConstant.YYYYMMDD_HH_MM_SS, endTime);
        int days = getRangeDays(startTime, endTime);
        String hours = getRangeHours(start, end);
        String semihs = getRangeSemihs(start, end);
        if (days == 0) {
            Calendar calStart = Calendar.getInstance();
            calStart.setTime(startTime);
            Calendar calEnd = Calendar.getInstance();
            calEnd.setTime(endTime);
            //同一个小时
            if (new BigDecimal(hours).compareTo(BigDecimal.ZERO) == 0) {
                //相差的半小时数
                int c = Integer.parseInt(semihs);
                //时
                int h = calStart.get(Calendar.HOUR_OF_DAY);
                int i = h << 1;
                if (c == 1) {
                    times.add(i);
                } else {
                    times.add(i);
                    times.add(i + 1);
                }
                //不同小时
            } else {
                //开始的点
                int s = calStart.get(Calendar.HOUR_OF_DAY);
                //开始的分钟
                int sf = calStart.get(Calendar.MINUTE);
                //开始时间是半点,去除首部
                if (sf != 0) {
                    //开始半小时数
                    int num = s << 1;
                    //相差的半小时数
                    int cha = Integer.parseInt(semihs);
                    for (int i = num + 1; i <= cha + num; i++) {
                        times.add(i);
                    }
                } else {
                    //开始半小时数
                    int num = s << 1;
                    //相差的半小时数
                    int cha = Integer.parseInt(semihs);
                    for (int i = num; i < cha + num; i++) {
                        times.add(i);
                    }
                }
            }
        }
        return times;
    }

    /**
     * 今天是星期几
     *
     * @param reserveDate
     * @return
     */
    public static int getDayOfWeek(String reserveDate) {
        Date date = convertStringToDate(DateFormatConstant.YYYY_MM_DD, reserveDate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int i = cal.get(Calendar.DAY_OF_WEEK);
        if (i == 1) {
            i = 7;
        } else {
            i--;
        }
        return i;
    }

    /**
     * 月第几天
     *
     * @param date
     * @return
     */
    public static int getDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int i = cal.get(Calendar.DAY_OF_MONTH);
        return i;
    }

    /**
     * 年第几天
     *
     * @param date
     * @return
     */
    public static int getDayOfYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int i = cal.get(Calendar.DAY_OF_YEAR);
        return i;
    }

    /**
     * 获取年
     *
     * @param date
     * @return
     */
    public static int getYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int i = cal.get(Calendar.YEAR);
        return i;
    }

    /**
     * 获取月
     *
     * @param date
     * @return
     */
    public static int getMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取月日
     *
     * @param date
     * @return
     */
    public static int getDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取月日
     *
     * @param date
     * @return
     */
    public static String getMonthDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int m = cal.get(Calendar.MONTH) + 1;
        int d = cal.get(Calendar.DAY_OF_MONTH);
        return m + "月" + d + "日";
    }

    /**
     * 第几月
     *
     * @param date
     * @return
     */
    public static int getMonthOfYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int i = cal.get(Calendar.MONTH) + 1;
        return i;
    }

    /**
     * 通用获取当前日期  避免系统间new Date()日期不一致等情况。（建议每个系统不再使用new Date()）
     * add by 20170616
     *
     * @return
     */
    public static Date currentDate() {
        return new Date();
    }


    /**
     * 当前时间推迟一年时间
     *
     * @return
     */
    public static Date afterOneYearDate() {
        Calendar curr = Calendar.getInstance();
        curr.set(Calendar.DATE, curr.get(Calendar.DATE) + 10);
        //  curr.set(Calendar.DATE, curr.get(Calendar.DATE) + 10);
        Date date = curr.getTime();

        return date;
    }

    /**
     * 当前时间所在周周一日期
     *
     * @return
     */
    public static Date getCurrentWeekDayStartTime(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        try {
            int weekday = c.get(Calendar.DAY_OF_WEEK) - 2;
            c.add(Calendar.DATE, -weekday);
            c.setTime(c.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c.getTime();
    }

    /**
     * 当前时间所在月第一天
     *
     * @return
     */
    public static Date getCurrentMonthStartTime(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        Date now = null;
        try {
            c.set(Calendar.DATE, 1);
            now = c.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 当前时间所在年第一天
     *
     * @return
     */
    public static Date getCurrentYearStartTime(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        Date now = null;
        try {
            c.set(Calendar.MONTH, 0);
            c.set(Calendar.DATE, 1);
            now = c.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 当前时间所在季度的开始时间
     *
     * @return
     */
    public static Date getCurrentQuarterStartTime(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int currentMonth = c.get(Calendar.MONTH) + 1;
        Date now = null;
        try {
            if (currentMonth >= 1 && currentMonth <= 3) {
                c.set(Calendar.MONTH, 0);
            } else if (currentMonth >= 4 && currentMonth <= 6) {
                c.set(Calendar.MONTH, 3);
            } else if (currentMonth >= 7 && currentMonth <= 9) {
                c.set(Calendar.MONTH, 6);
            } else if (currentMonth >= 10 && currentMonth <= 12) {
                c.set(Calendar.MONTH, 9);
            }
            c.set(Calendar.DATE, 1);
            now = c.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 当前时间前/后半年的开始时间
     *
     * @return
     */
    public static Date getHalfYearStartTime(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int currentMonth = c.get(Calendar.MONTH) + 1;
        Date now = null;
        try {
            if (currentMonth >= 1 && currentMonth <= 6) {
                c.set(Calendar.MONTH, 0);
            } else if (currentMonth >= 7 && currentMonth <= 12) {
                c.set(Calendar.MONTH, 6);
            }
            c.set(Calendar.DATE, 1);
            now = c.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;

    }

    /**
     * 当前时间所在季度的开始时间
     *
     * @return
     */
    public static Date getCurrentDoubbleStartTime(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int currentMonth = c.get(Calendar.MONTH) + 1;
        Date now = null;
        try {
            if (currentMonth >= 1 && currentMonth <= 2) {
                c.set(Calendar.MONTH, 0);
            } else if (currentMonth >= 3 && currentMonth <= 4) {
                c.set(Calendar.MONTH, 2);
            } else if (currentMonth >= 5 && currentMonth <= 6) {
                c.set(Calendar.MONTH, 4);
            } else if (currentMonth >= 7 && currentMonth <= 8) {
                c.set(Calendar.MONTH, 6);
            } else if (currentMonth >= 9 && currentMonth <= 10) {
                c.set(Calendar.MONTH, 8);
            } else if (currentMonth >= 11 && currentMonth <= 12) {
                c.set(Calendar.MONTH, 10);
            }
            c.set(Calendar.DATE, 1);
            now = c.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 还有几天到期
     *
     * @param endTime
     * @return
     */
    public static int dateDue(Date endTime) {
        Date date = currentDate();
        return differentDays(date, endTime);
    }


    public static Date convertDateToDate(String datePattern, Date aDate) {
        return convertStringToDate(datePattern, convertDateToString(datePattern, aDate));
    }

    /**
     * 获取服务器启动时间
     */
    public static Date getServerStartDate() {
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        return new Date(time);
    }
}
