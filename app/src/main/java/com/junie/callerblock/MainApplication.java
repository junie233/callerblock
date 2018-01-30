package com.junie.callerblock;

import android.app.Application;

import com.junie.callerblock.receiver.CallManager;

/**
 * Created by niejun on 2018/1/29.
 */

public class MainApplication extends Application {

    private static  MainApplication application;
    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        CallManager.initInCallListener(this);
    }

    public static MainApplication getApplication() {
        return application;
    }
}
