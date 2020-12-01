package com.colourful.colourful.pay.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static String getSeqString() {
        SimpleDateFormat fm = new SimpleDateFormat("yyyyMMddHHmmss"); // "yyyyMMdd G
        return fm.format(new Date());
    }

    /**
     * @title:calendarMinute
     * @Description: 日期格式化 获取当前时间后几分钟
     * @Param: [minute]
     * @return: java.util.Date
     * @Auther: 图南
     * @Date: 2019/8/13 17:47
     */
    public static Date calendarMinute(Integer minute) {
        Calendar cal = Calendar.getInstance();
        //下面的就是把当前日期加一个月
        cal.add(Calendar.MINUTE, minute);

        return cal.getTime();
    }
}
