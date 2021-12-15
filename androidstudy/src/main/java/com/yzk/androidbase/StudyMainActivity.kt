package com.yzk.androidbase

import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.ViewGroup
import android.widget.Button
import com.yzk.androidbase.broadcast.SendBroadcastActivity
import com.yzk.androidbase.list.RecyclerActivity
import com.yzk.androidbase.nightmode.ChangeNightModeActivity
import com.yzk.androidbase.popupwindow.FullScreenPopupWindow
import com.yzk.baselib.base.BaseActivity
import com.yzk.baselib.tools.LogUtil

class StudyMainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_study_main)

        findViewById<Button>(R.id.btn_showFullPopup1).setOnClickListener {
            FullScreenPopupWindow(this);
        }

        findViewById<Button>(R.id.btn_showFullPopup2).setOnClickListener {
            //默认和当前layout展示的height相同， 即实现全屏 ps: childAt(0)不一定
            val viewHeight = (this.window.decorView as ViewGroup).getChildAt(0).height
            FullScreenPopupWindow(this, viewHeight)
        }

        /**
         *  dumpsys中
         *  mBaseDisplayInfo=DisplayInfo{"内置屏幕", uniqueId "local:0", app 1080 x 2160, real 1080 x 2160, largest app 1080 x 2160, smallest app 1080 x 2160, mode 1, defaultMode 1, modes [{id=1, width=1080, height=2160, fps=60.000004}], colorMode 0, supportedColorModes [0], hdrCapabilities android.view.Display$HdrCapabilities@1d6308, rotation 0, density 480 (397.565 x 449.704) dpi, layerStack 0, appVsyncOff 1000000, presDeadline 16666666, type BUILT_IN, state ON, FLAG_SECURE, FLAG_SUPPORTS_PROTECTED_BUFFERS, removeMode 0}
         *  mOverrideDisplayInfo=DisplayInfo{"内置屏幕", uniqueId "local:0", app 1080 x 2030, real 1080 x 2160, largest app 2030 x 1954, smallest app 1080 x 1004, mode 1, defaultMode 1, modes [{id=1, width=1080, height=2160, fps=60.000004}], colorMode 0, supportedColorModes [0], hdrCapabilities android.view.Display$HdrCapabilities@1d6308, rotation 0, density 440 (397.565 x 449.704) dpi, layerStack 0, appVsyncOff 1000000, presDeadline 16666666, type BUILT_IN, state ON, FLAG_SECURE, FLAG_SUPPORTS_PROTECTED_BUFFERS, removeMode 0}
         */
        findViewById<Button>(R.id.btn_getScreenHeight).setOnClickListener {
            //红米5plus 真实分辨率 2160x1080

            var screenHeight = windowManager.defaultDisplay.height
            LogUtil.w("srceenHeight1:$screenHeight") //2030

            var dm = resources.displayMetrics;
            screenHeight = dm.heightPixels;
            LogUtil.w("srceenHeight2:$screenHeight") //2030

            var dm2 = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(dm2)
            screenHeight = dm2.heightPixels
            LogUtil.w("srceenHeight3:$screenHeight") //2030

            var dm3 = DisplayMetrics()
            windowManager.defaultDisplay.getRealMetrics(dm3)
            screenHeight = dm3.heightPixels
            LogUtil.w("srceenHeight4:$screenHeight") //2160

        }

        findViewById<Button>(R.id.btn_toRecyclerAty).setOnClickListener {
            startActivity(Intent(this, RecyclerActivity::class.java))
        }

        findViewById<Button>(R.id.btn_toChangeTheme).setOnClickListener {
            startActivity(Intent(this, ChangeNightModeActivity::class.java))
        }

        findViewById<Button>(R.id.btn_toBroadcast).setOnClickListener {
            startActivity(Intent(this, SendBroadcastActivity::class.java))
        }
    }
}