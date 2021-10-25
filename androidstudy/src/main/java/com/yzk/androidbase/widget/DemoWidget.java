package com.yzk.androidbase.widget;


import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.yzk.androidbase.R;
import com.yzk.baselib.App;

public class DemoWidget extends AppWidgetProvider {

    private static final String TAG = "WIDGET";

    public static final String REFRESH_WIDGET = "com.oitsme.REFRESH_WIDGET";
    public static final String COLLECTION_VIEW_ACTION = "com.oitsme.COLLECTION_VIEW_ACTION";
    public static final String COLLECTION_VIEW_EXTRA = "com.oitsme.COLLECTION_VIEW_EXTRA";
    private static Handler mHandler=new Handler();
    private Runnable runnable=new Runnable() {
        @Override
        public void run() {
            hideLoading(App.getContext());
            Toast.makeText(App.getContext(), "刷新成功", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        Log.d(TAG, "ListWidgetProvider onUpdate");
        for (int appWidgetId : appWidgetIds) {
            // 获取AppWidget对应的视图
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_demo);

            // 设置响应 “按钮(bt_refresh)” 的intent
            Intent btIntent = new Intent().setAction(REFRESH_WIDGET);
            PendingIntent btPendingIntent = PendingIntent.getBroadcast(context, 0, btIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.tv_refresh, btPendingIntent);

        }
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        if (action.equals(COLLECTION_VIEW_ACTION)) {
            // 接受“ListView”的点击事件的广播
            int type = intent.getIntExtra("Type", 0);
            int appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
            int index = intent.getIntExtra(COLLECTION_VIEW_EXTRA, 0);
            switch (type) {
                case 0:
                    Toast.makeText(context, "item" + index, Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    Toast.makeText(context, "lock"+index, Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Toast.makeText(context, "unlock"+index, Toast.LENGTH_SHORT).show();
                    break;
            }
        } else if (action.equals(REFRESH_WIDGET)) {
            // 接受“bt_refresh”的点击事件的广播
//            Toast.makeText(context, "刷新...", Toast.LENGTH_SHORT).show();
//            final AppWidgetManager mgr = AppWidgetManager.getInstance(context);
//            final ComponentName cn = new ComponentName(context,ListWidgetProvider.class);
//            ListRemoteViewsFactory.refresh();
//            mgr.notifyAppWidgetViewDataChanged(mgr.getAppWidgetIds(cn),R.id.lv_device);
//            mHandler.postDelayed(runnable,2000);
//            showLoading(context);
        }
        super.onReceive(context, intent);
    }

    /**
     * 显示加载loading
     *
     */
    private void showLoading(Context context) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_demo);
        remoteViews.setViewVisibility(R.id.tv_refresh, View.VISIBLE);
        remoteViews.setViewVisibility(R.id.progress_bar, View.VISIBLE);
        remoteViews.setTextViewText(R.id.tv_refresh, "正在刷新...");
        refreshWidget(context, remoteViews, false);
    }

    /**
     * 隐藏加载loading
     */
    private void hideLoading(Context context) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_demo);
        remoteViews.setViewVisibility(R.id.progress_bar, View.GONE);
        remoteViews.setTextViewText(R.id.tv_refresh, "刷新");
        refreshWidget(context, remoteViews, false);
    }



    /**
     * 刷新Widget
     */
    private void refreshWidget(Context context, RemoteViews remoteViews, boolean refreshList) {
//        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
//        ComponentName componentName = new ComponentName(context, ListWidgetProvider.class);
//        appWidgetManager.updateAppWidget(componentName, remoteViews);
//        if (refreshList)
//            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetManager.getAppWidgetIds(componentName), R.id.lv_device);
    }
}