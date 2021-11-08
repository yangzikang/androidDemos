package com.yzk.androidbase.webview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.yzk.androidbase.R;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

//        daliymotion莫名其妙闪退
//        2021-11-08 11:34:17.745 11753-11753/com.downloaderexpert.vds A/zygote64: java_vm_ext.cc:534] JNI DETECTED ERROR IN APPLICATION: JNI NewObjectV called with pending exception java.lang.IllegalStateException:
//        2021-11-08 11:34:17.746 11753-11753/com.downloaderexpert.vds A/zygote64: java_vm_ext.cc:534]   at android.media.PlaybackParams android.media.MediaPlayer.getPlaybackParams() (MediaPlayer.java:-2)
//        2021-11-08 11:34:17.746 11753-11753/com.downloaderexpert.vds A/zygote64: java_vm_ext.cc:534]   at void org.chromium.media.MediaPlayerBridge.setPlaybackRate(double) (chromium-SystemWebViewGoogle.aab-stable-460608503:4)
//        2021-11-08 11:34:17.746 11753-11753/com.downloaderexpert.vds A/zygote64: java_vm_ext.cc:534]   at void android.os.MessageQueue.nativePollOnce(long, int) (MessageQueue.java:-2)
//        2021-11-08 11:34:17.746 11753-11753/com.downloaderexpert.vds A/zygote64: java_vm_ext.cc:534]   at android.os.Message android.os.MessageQueue.next() (MessageQueue.java:331)
//        2021-11-08 11:34:17.746 11753-11753/com.downloaderexpert.vds A/zygote64: java_vm_ext.cc:534]   at void android.os.Looper.loop() (Looper.java:149)
//        2021-11-08 11:34:17.746 11753-11753/com.downloaderexpert.vds A/zygote64: java_vm_ext.cc:534]   at void android.app.ActivityThread.main(java.lang.String[]) (ActivityThread.java:6651)
//        2021-11-08 11:34:17.746 11753-11753/com.downloaderexpert.vds A/zygote64: java_vm_ext.cc:534]   at java.lang.Object java.lang.reflect.Method.invoke(java.lang.Object, java.lang.Object[]) (Method.java:-2)
//        2021-11-08 11:34:17.746 11753-11753/com.downloaderexpert.vds A/zygote64: java_vm_ext.cc:534]   at void com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run() (RuntimeInit.java:547)
//        2021-11-08 11:34:17.746 11753-11753/com.downloaderexpert.vds A/zygote64: java_vm_ext.cc:534]   at void com.android.internal.os.ZygoteInit.main(java.lang.String[]) (ZygoteInit.java:824)
//        2021-11-08 11:34:17.746 11753-11753/com.downloaderexpert.vds A/zygote64: java_vm_ext.cc:534]
//        2021-11-08 11:34:17.746 11753-11753/com.downloaderexpert.vds A/zygote64: java_vm_ext.cc:534]     in call to NewObjectV
//        2021-11-08 11:34:17.746 11753-11753/com.downloaderexpert.vds A/zygote64: java_vm_ext.cc:534]     from android.media.PlaybackParams android.media.MediaPlayer.getPlaybackParams()
//        private static final String DESKTOP_USER_AGENT = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/37.0.2049.0 Safari/537.36";
//        private static final String MOBILE_USER_AGENT = "Mozilla/5.0 (Linux; U; Android 4.4; en-us; Nexus 4 Build/JOP24G) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30";
//        settings.setUserAgentString(MOBILE_USER_AGENT);
    }
}