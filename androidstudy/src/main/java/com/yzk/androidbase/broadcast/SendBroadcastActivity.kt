package com.yzk.androidbase.broadcast

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.yzk.androidbase.R

class SendBroadcastActivity : AppCompatActivity() {
    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_broadcast)


        findViewById<Button>(R.id.send_btn).setOnClickListener {
            val intent = Intent("com.iflytek.autofly.handMessage")
            intent.setPackage("com.edog.car");
            intent.putExtra("value", "{\n" +
                    "\t\"messageType\": \"REQUEST\",\n" +
                    "\t\"focus\": \"internetRadio\",\n" +
                    "\t\"needResponse\": \"YES\",\n" +
                    "\t\"requestCode\": \"10001\",\n" +
                    "\t\"semantic\": {\n" +
                    "\t\t\"service\": \"INTERNETRADIO\",\n" +
                    "\t\t\"operation\": \"NEXT\",\n" +
                    "\t\t\"category\": \"相声评书\",\n" +
                    "\t\t\"presenter\": \"岳云鹏\"\n" +
                    "\t},\n" +
                    "\t\"version\": \"v1.0\",\n" +
                    "\t\"operationApp\": \"speech\"\n" +
                    "}") //组装后的json push
            intent.addFlags(0x01000000);
            sendBroadcast(intent)
        }

    }
}