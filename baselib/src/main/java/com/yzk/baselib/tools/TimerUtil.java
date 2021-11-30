package com.yzk.baselib.tools;

import android.os.CountDownTimer;

public class TimerUtil {

    public static CountDownTimer mCountDownTimer;

    public interface ITimerCallback {
        public void onFinish();

        public void onEachSecond(long second);
    }

    public static void startTimer(int totalSeconds, int eachSeconds, ITimerCallback callback) {
        stopTimer();
        mCountDownTimer = new CountDownTimer(totalSeconds * 1000, eachSeconds * 1000) {
            @Override
            public void onFinish() {
                callback.onFinish();
                cancel();
            }

            @Override
            public void onTick(long millisUntilFinished) {
                callback.onEachSecond(millisUntilFinished / 1000);
            }
        }.start();
    }

    public static void stopTimer() {
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
            mCountDownTimer = null;
        }
    }
}
