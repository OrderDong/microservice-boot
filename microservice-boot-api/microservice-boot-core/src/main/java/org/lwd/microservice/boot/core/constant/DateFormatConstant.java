package org.lwd.microservice.boot.core.constant;

/**
 * @author virtiL
 * @date 2022/6/6
 * @see
 * @since 1.0.0.0
 */
public class DateFormatConstant {

    public static String YYYY = "yyyy";

    public static String YYYY_MM = "yyyy-MM";

    public static String YYYY_MM_DD = "yyyy-MM-dd";

    public static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static String YYYYMMDDHHMM = "yyyyMMddHHmm";

    public static String YYYY_MM_DD_HHMMSS = "yyyy-MM-dd HH:mm:ss";

    public static String YYYY_MM_DD_HHMM = "yyyy-MM-dd HH:mm";

    public static String YYYYMMDD_HH_MM_SS = "yyyy/MM/dd HH:mm:ss";
    public static String YYYYMMDD_HH_MM = "yyyy/MM/dd HH:mm";

    public static final String YYYYMMDD_CHINESE = "yyyy年MM月dd日";


    public static final String MMDDHHMMSS = "MMddHHmmss";

    public static final String[] CHINESE_WEEK_DAYS = {"星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"};


    public static final String DATETIME_VAILD_RULE = "^[0-9]{4}-(((0[13578]|(10|12))-(0[1-9]|[1-2][0-9]|3[0-1]))|(02-(0[1-9]|[1-2][0-9]))|((0[469]|11)-(0[1-9]|[1-2][0-9]|30)))\\s+([0-1]?[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$";

    public static final String DATE_VAILD_RULE = "^[0-9]{4}-(((0[13578]|(10|12))-(0[1-9]|[1-2][0-9]|3[0-1]))|(02-(0[1-9]|[1-2][0-9]))|((0[469]|11)-(0[1-9]|[1-2][0-9]|30)))$";

}
