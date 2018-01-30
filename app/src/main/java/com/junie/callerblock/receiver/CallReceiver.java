package com.junie.callerblock.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

/**
 * Created by niejun on 2018/1/29.
 */

public class CallReceiver extends BroadcastReceiver {

    private final static String TAG = CallReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {

        if(intent == null || TextUtils.isEmpty(intent.getAction())) {
            return;
        }
        String number = intent.getExtras().getString(Intent.EXTRA_PHONE_NUMBER);
        if(intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL)) {

            Log.i(TAG,"去电 ：" + number);
            //做具体去电逻辑处理

        }
    }





}
