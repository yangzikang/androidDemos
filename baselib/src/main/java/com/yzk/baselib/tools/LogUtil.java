package com.yzk.baselib.tools;

import android.util.Log;

import com.yzk.baselib.BuildConfig;

public class LogUtil {

    /**
     * 控制非debug状态展示w/e  build.gradle中的debugger
     */
    public static boolean isDebug = BuildConfig.DEBUG;

    public static void e(String content) {
        Log.e("yangzikang:", " " + content);
    }

    public static void w(String content) {
        Log.w("yangzikang:", " " + content);
    }

    public static void d(String content) {
        if (isDebug) {
            Log.d("yangzikang:", " " + content);
        }
    }

    public static void i(String content) {
        if (isDebug) {
            Log.i("yangzikang:", " " + content);
        }
    }

    public static void v(String content) {
        if (isDebug) {
            Log.v("yangzikang", " " + content);
        }
    }

    public static void all(String content) {
        final int MAX_LENGTH = 4000;

        int index = 0;
        while (index < content.length()) {
            if (content.length() <= index + MAX_LENGTH) {
                d(content.substring(index));
            } else {
                d(content.substring(index, index + MAX_LENGTH));
            }
            index += MAX_LENGTH;
        }
    }
}
