package com.junie.callerblock.receiver;

import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

/**
 * Created by niejun on 2018/1/29.
 */

public class CallManager {



    public static void initInCallListener(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        InCallListener inCallListener = new InCallListener();
        telephonyManager.listen(inCallListener, PhoneStateListener.LISTEN_CALL_STATE);
    }


}
