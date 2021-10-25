package com.yzk.androiddemos

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.yzk.androidbase.BaseMainActivity
import com.yzk.baselib.base.BaseActivity

/**
 * 启动页：设置SingleTask后，app后台，点击图标进入app，会重建MainActivity
 */
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.tv_next).setOnClickListener {
            startActivity(Intent(this, BaseMainActivity::class.java))
            //finish() //Log Tag ActivityLifecycle
        }

        if (!this.isTaskRoot && intent != null) {
            val action = intent.action
            if (intent.hasCategory(Intent.CATEGORY_LAUNCHER) && Intent.ACTION_MAIN == action) {
                startActivity(Intent(this, BaseMainActivity::class.java))
                finish()
                return
            }
        }
    }
}