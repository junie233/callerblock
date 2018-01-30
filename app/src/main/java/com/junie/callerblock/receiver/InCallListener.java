package com.junie.callerblock.receiver;

import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.junie.callerblock.contract.CallContract;
import com.junie.callerblock.di.DaggerCallComponent;
import com.junie.callerblock.di.modules.CallModule;

import javax.inject.Inject;

/**
 * Created by niejun on 2018/1/29.
 */

public class InCallListener extends PhoneStateListener implements CallContract.View {

    private final static String TAG = InCallListener.class.getSimpleName();

    @Inject
    CallContract.Presenter presenter;

    public InCallListener() {
        DaggerCallComponent.builder().callModule(new CallModule(this)).build();
    }


    @Override
    public void onCallStateChanged(int state, String incomingNumber) {
        super.onCallStateChanged(state, incomingNumber);
        switch(state) {
            case TelephonyManager.CALL_STATE_IDLE:
                Log.i(TAG,"挂断:" + incomingNumber);
                break;
            case TelephonyManager.CALL_STATE_RINGING:
                Log.i(TAG,"来电响铃" + incomingNumber);
                presenter.handleRing(incomingNumber);
                break;
            case TelephonyManager.CALL_STATE_OFFHOOK:
                Log.i(TAG,"来电接通" + incomingNumber);
                break;
        }
    }

    @Override
    public void setPresenter(CallContract.Presenter presenter) {

    }
}
