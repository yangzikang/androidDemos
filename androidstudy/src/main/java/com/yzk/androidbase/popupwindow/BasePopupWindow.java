package com.yzk.androidbase.popupwindow;

import android.view.View;
import android.widget.PopupWindow;

import com.yzk.baselib.tools.LogUtil;


public class BasePopupWindow extends PopupWindow {

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        try {
            super.showAtLocation(parent, gravity, x, y);
        } catch (Exception e) {
            LogUtil.w(" 展示失败:" + e.toString());
        }
    }


    @Override
    public void dismiss() {
        try {
            if (getContentView().getWindowToken() != null) {
                super.dismiss();
            } else {
                LogUtil.w(" windowToken 为空");
            }
        } catch (Exception e) {
            LogUtil.w(" 其他异常" + e.toString());
        }
    }

}

