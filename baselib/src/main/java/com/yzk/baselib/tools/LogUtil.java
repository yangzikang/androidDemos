package com.yzk.baselib.tools;

import android.util.Log;

public class LogUtil {
    public static void e(String content) {
        Log.e("yangzikang:", " " + content);
    }

    public static void w(String content) {
        Log.w("yangzikang:", " " + content);
    }

    public static void d(String content) {
        Log.d("yangzikang:", " " + content);
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
