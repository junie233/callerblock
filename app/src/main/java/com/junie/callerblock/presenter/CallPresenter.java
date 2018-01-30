package com.junie.callerblock.presenter;

import com.junie.callerblock.contract.CallContract;
import com.junie.callerblock.entity.CallNumber;

/**
 * Created by niejun on 2018/1/29.
 */

public class CallPresenter implements CallContract.Presenter{

    private CallContract.View view;

    public CallPresenter(CallContract.View view) {
        this.view = view;
    }


    @Override
    public void start() {
    }

    @Override
    public void handleRing(String number) {
        //视觉处理

        //查找号码

        //保存号码
        saveCallNumber(number);


    }

    private void saveCallNumber(String number) {
        CallNumber callNumber = new CallNumber();
        callNumber.setNumber(number);
        callNumber.setCalltime(System.currentTimeMillis());


    }
}
