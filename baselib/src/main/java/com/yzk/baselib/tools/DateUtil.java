package com.yzk.baselib.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    /**
     *     yyyy-MM-dd 类型String 转时间戳
     */
    public static long date2Timestemp(String dateString) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sf.parse(dateString);
        } catch (ParseException e) {
            LogUtil.e("DateUtil时间戳转换异常");
        }
        if (date == null) {
            return 0l;
        } else {
            return date.getTime();
        }
    }
}
