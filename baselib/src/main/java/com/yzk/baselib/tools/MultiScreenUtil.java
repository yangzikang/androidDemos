package com.yzk.baselib.tools;

import android.app.Activity;
import android.os.Build;

/**
 * 分屏工具类
 */
public class MultiScreenUtil {
    public static boolean isInMultiWindowMode = false;

    public static boolean getMultiStatus(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            try {
                return activity.isInMultiWindowMode();
            } catch (NoSuchFieldError error) {
                return false;
            }
        } else {
            return isInMultiWindowMode;
        }
    }

}