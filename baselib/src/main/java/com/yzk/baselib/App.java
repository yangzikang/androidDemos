package com.yzk.baselib;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.yzk.baselib.tools.LogUtil;

public class App extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        initFresco();

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            private static final String TAG = "ActivityLifecycle";
            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
                LogUtil.d(TAG + " onCreated " + activity.getClass().getSimpleName());
            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {
                LogUtil.d(TAG + " onStarted " + activity.getClass().getSimpleName());
            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {
                LogUtil.d(TAG + " onResumed " + activity.getClass().getSimpleName());
            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {
                LogUtil.d(TAG + " onPaused " + activity.getClass().getSimpleName());
            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {
                LogUtil.d(TAG + " onStopped " + activity.getClass().getSimpleName());
            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {
                LogUtil.d(TAG + " onSaveInstanceState " + activity.getClass().getSimpleName());
            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {
                LogUtil.d(TAG + " onDestroyed " + activity.getClass().getSimpleName());
            }
        });
    }

    private void initFresco() {
        Fresco.initialize(this);
    }

    public static Context getContext() {
        return context;
    }
}
