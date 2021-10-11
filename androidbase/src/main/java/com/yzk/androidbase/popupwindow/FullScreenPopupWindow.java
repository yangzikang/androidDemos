package com.yzk.androidbase.popupwindow;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

import com.yzk.androidbase.R;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class FullScreenPopupWindow extends BasePopupWindow {

    public FullScreenPopupWindow(Activity activity) {
        View contentView = LayoutInflater.from(activity).inflate(R.layout.popup_full_screen, null);
        setContentView(contentView);
        initView(contentView);
        setWidth(MATCH_PARENT); //设置宽度
        setHeight(MATCH_PARENT); //设置高度 状态栏 + 导航栏
        setClippingEnabled(false); //关闭剪切 沉浸
        setFocusable(true);
        View rootView = activity.getWindow().getDecorView();
        showAtLocation(rootView, Gravity.TOP, 0, 0); //margin button
    }

    //高度需要依附的activiy 获取到当前展示的ViewGroup高度（docerView的子view高度）
    public FullScreenPopupWindow(Activity activity, int viewHeight) {
        View contentView = LayoutInflater.from(activity).inflate(R.layout.popup_full_screen, null);
        setContentView(contentView);
        initView(contentView);
        setWidth(MATCH_PARENT); //设置宽度
        setHeight(viewHeight); //设置高度
        setClippingEnabled(false);
        setFocusable(true);
        View rootView = activity.getWindow().getDecorView();
        showAtLocation(rootView, Gravity.TOP, 0, 0); //margin button
    }

    public void initView(View rootView) {
        rootView.findViewById(R.id.iv_show).setOnClickListener(v -> dismiss());
    }
}
