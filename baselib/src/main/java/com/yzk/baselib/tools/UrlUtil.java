package com.yzk.baselib.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlUtil {
    /**
     * 检查URL是否合法
     */
    public static boolean isUrlValidate(String url) {
        if (url == null) {
            return false;
        }
        String realurl;
        realurl = url.substring(0, url.indexOf("#") >= 0 ? url.indexOf("#") : url.length());
        if (realurl.lastIndexOf("/") == realurl.length() - 1) {
            realurl = realurl.substring(0, realurl.length() - 1);
        }
        Pattern p = Pattern.compile("^(https|http|www|ftp|)?(://)?[-A-Za-z0-9.]+(:\\d+)?(/[-A-Za-z0-9.]+)*(\\?)?[-A-Za-z0-9.%:+&=]*$", Pattern.CASE_INSENSITIVE); //精度丢失
        Matcher m = p.matcher(realurl);
        return m.matches();
    }
}
