package com.yzk.baselib.tools;

import android.os.SystemClock;

/**
 * Created by cjl on 16/3/8.
 */
public class BtnClickUtil {
    private static long lastClickTime;

    public synchronized static boolean isFastClick() {
        long time = SystemClock.uptimeMillis();
        if (time - lastClickTime < 800) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    /**
     * 防抖动灵活设置时间 add by changhaiyang
     *
     * @param timeOffet
     * @return
     */
    public synchronized static boolean isFastClick(long timeOffet) {
        long time = SystemClock.uptimeMillis();
        if (time - lastClickTime < timeOffet) {
            return true;
        }
        lastClickTime = time;
        return false;
    }
}
