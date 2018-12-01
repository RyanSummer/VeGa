package com.jingwei.vega.base;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * 获取Context
         */
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
