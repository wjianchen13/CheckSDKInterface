package com.example.checksdkinterface;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.CarrierConfigManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        Field field = null;
        try {
            field = wifiManager.getClass().getDeclaredField("WIFI_SCAN_AVAILABLE");
            Log.d("ThirdActivity", (String) field.get(wifiManager));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
//        ReflectionUtils
    }

    // 直接在浅灰名单中找的接口
    public void testLightGreyList(View view) {
        ReflectUtils.getAccessibleMethod(TelephonyManager.class, "isMultiSimEnabled");
    }

    // 直接在深灰名单中找的接口
    @TargetApi(Build.VERSION_CODES.M)
    public void testDarkGreyList(View view) {
        ReflectUtils.getFieldValue(CarrierConfigManager.class, "KEY_ALWAYS_PLAY_REMOTE_HOLD_TONE_BOOL");
    }

    // 直接在黑名单中找的接口
    public void testBlackList(View view) {
        try {
            ReflectUtils.getAccessibleMethod(ReflectUtils.getUserClass("android.net.util.IpUtils"), "ipChecksum", ByteBuffer.class, int.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}