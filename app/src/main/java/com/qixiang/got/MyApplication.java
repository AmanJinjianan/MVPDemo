package com.qixiang.got;

import android.app.Application;
import android.content.Context;

import com.qixiang.got.utils.CrashHandler;
import com.qixiang.got.utils.DeviceInfoUtils;

/**
 * 自定的Application
 *
 * @author zhangyazhou
 */
public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        CrashHandler.getInstance().init(getApplicationContext());
        //DeviceInfoUtils.init(this);
    }

    public static Context getContext() {
        return context;
    }
}

