package com.yzk.baselib;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

public class App extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        initFresco();
    }

    private void initFresco() {
        Fresco.initialize(this);
    }

    public static Context getContext() {
        return context;
    }
}
