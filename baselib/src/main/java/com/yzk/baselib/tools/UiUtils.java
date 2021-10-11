package com.yzk.baselib.tools;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yzk.baselib.App;

public class UiUtils {

    //用于获取屏幕分辨率
    private synchronized static DisplayMetrics getDisplayMetrics(Activity activity) {
        //注意： 此处DisplayMetrics 不要使用context.getApplicationContext().getResources().getDisplayMetrics();
        //return context.getResources().getDisplayMetrics();
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm;
    }

    //获取屏幕分辨率不准 仅用来获取density
    private synchronized static DisplayMetrics getDisplayMetrics(Context context) {
        return context.getResources().getDisplayMetrics();
    }

    public static float dpToPx(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getDisplayMetrics(App.getContext()));
    }


    public static float pxToDp(float px) {
        return px / getDisplayMetrics(App.getContext()).density;
    }

    //传activity 部分手机直接获取屏幕高度，返回的是不是real height
    public static int getScreenHeight(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getRealMetrics(dm);
        return dm.heightPixels;
    }

    public static int getScreenWidth(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getRealMetrics(dm);
        return dm.widthPixels;
    }

    public static void toast(String content) {
        Toast.makeText(App.getContext(), content, Toast.LENGTH_SHORT).show();
        LogUtil.d(content);
    }

    public static void toastLong(String content) {
        Toast.makeText(App.getContext(), content, Toast.LENGTH_LONG).show();
    }

    public static int getStatusBarHeight() {
        int statusBarHeight = -1;
        //获取status_bar_height资源的ID
        int resourceId = App.getContext().getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = App.getContext().getResources().getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }

    public static int getNavigationBarHeight() {
        int result = -1;
        int resourceId = App.getContext().getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = App.getContext().getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 遍历view树，查看是否真的展示了NavigationBar
     *
     * @param activity
     * @return
     */
    protected boolean isNavigationBarShow(Activity activity) {
        ViewGroup vp = (ViewGroup) activity.getWindow().getDecorView();
        if (vp != null) {
            for (int i = 0; i < vp.getChildCount(); i++) {
                vp.getChildAt(i).getContext().getPackageName();
                View view = vp.getChildAt(i);
                if (view.getId() != -1 && "navigationBarBackground".equals(activity.getResources().getResourceEntryName(view.getId()))) {
                    return view.isShown();
                }
            }
        }
        return false;
    }
}
