package com.yzk.baselib.base;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.yzk.baselib.tools.LogUtil;
import com.yzk.baselib.tools.MultiScreenUtil;

import java.util.ArrayList;
import java.util.List;


public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        LogUtil.d("ActivityLifecycle" + " onNewIntent " + this.getClass().getSimpleName());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtil.d("ActivityLifecycle" + " onRestart " + this.getClass().getSimpleName());
    }

    @Override
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode) {
        super.onMultiWindowModeChanged(isInMultiWindowMode);
        MultiScreenUtil.isInMultiWindowMode = isInMultiWindowMode;
    }

    //permission Util
    private ICheckPermission mICheckPermission = null;

    public interface ICheckPermission {
        void onAllow();

        void onReject(List<String> rejects);
    }

    public void checkPermission(String[] permission, ICheckPermission iCheckPermission) {
        if (Build.VERSION.SDK_INT < 23 || permission.length == 0) {
            if (iCheckPermission != null) {
                iCheckPermission.onAllow();
            }
        } else {
            if (iCheckPermission != null) {
                mICheckPermission = iCheckPermission;
                ActivityCompat.requestPermissions(this, permission, 99);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (mICheckPermission != null && requestCode == 99) {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < grantResults.length; i++) {
                //判断权限是否被允许，只要又一次拒绝就算失败
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    // 1：用户拒绝了该权限，没有勾选"不再提醒"，此方法将返回true。
                    // 2：用户拒绝了该权限，有勾选"不再提醒"，此方法将返回 false。
                    // 3：如果用户同意了权限，此方法返回false
//                    if (!ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[i])) {
//                        UiUtils.toast("你已拒绝此权限，如果需要，可以在设置中打开此权限");
//                    }
                    list.add(permissions[i]);
                    return;
                }
            }

            if (list.size() == 0) {
                mICheckPermission.onAllow();
            } else {
                mICheckPermission.onReject(list);
            }
        }
    }

}
