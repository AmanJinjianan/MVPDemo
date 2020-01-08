package com.qixiang.got.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import androidx.core.content.ContextCompat;

public class DeviceInfoUtils {

    private static DeviceInfo deviceInfo = new DeviceInfo();

    public static DeviceInfo getDeviceInfo() {
        return deviceInfo;
    }

    public static void init(Context context) {
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            WindowManager wm = (WindowManager) context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
            Display disp = wm.getDefaultDisplay();

            int API_LEVEL =  android.os.Build.VERSION.SDK_INT;
            if (API_LEVEL >= 17)
            {
                disp.getRealMetrics(displayMetrics);
            }
            else
            {
                disp.getMetrics(displayMetrics);
            }

            int width = displayMetrics.widthPixels;
            int height = displayMetrics.heightPixels;

            deviceInfo.setScreenWidth(width);
            deviceInfo.setScreenHeight(height);

            deviceInfo.setOs(Build.VERSION.RELEASE);
            deviceInfo.setApi(Build.VERSION.SDK_INT);
            deviceInfo.setModel(Build.MODEL);
            deviceInfo.setManufacturer(Build.MANUFACTURER);

            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE)
                    == PackageManager.PERMISSION_GRANTED) {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                String imei = telephonyManager.getDeviceId();
                String sn = telephonyManager.getSimSerialNumber();
                deviceInfo.setSn(sn);
                deviceInfo.setImei(imei);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class DeviceInfo {

        private String sn;
        private String imei;
        private String os;
        private int api;
        private String model;
        private int screenWidth;
        private int screenHeight;
        private String manufacturer;

        public String getSn() {
            return sn;
        }

        public void setSn(String sn) {
            this.sn = sn;
        }

        public String getImei() {
            return imei;
        }

        public void setImei(String imei) {
            this.imei = imei;
        }

        public String getOs() {
            return os;
        }

        public void setOs(String os) {
            this.os = os;
        }

        public int getApi() {
            return api;
        }

        public void setApi(int api) {
            this.api = api;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public int getScreenWidth() {
            return screenWidth;
        }

        public void setScreenWidth(int screenWidth) {
            this.screenWidth = screenWidth;
        }

        public int getScreenHeight() {
            return screenHeight;
        }

        public void setScreenHeight(int screenHeight) {
            this.screenHeight = screenHeight;
        }

        public String getManufacturer() {
            return manufacturer;
        }

        public void setManufacturer(String manufacturer) {
            this.manufacturer = manufacturer;
        }
    }
}
