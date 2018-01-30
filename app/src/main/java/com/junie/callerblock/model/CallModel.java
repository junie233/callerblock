package com.junie.callerblock.model;

import android.text.TextUtils;

import com.junie.callerblock.entity.CallNumber;
import com.junie.callerblock.util.ContactUtils;

/**
 * Created by niejun on 2018/1/29.
 * 电话信息的model管理
 */

public class CallModel {




    /**
     * 查找电话号码
     * @param number
     * @return
     */
    public CallNumber finCaller(String number) {
        if(TextUtils.isEmpty(number)) {
            return null;
        }
        CallNumber callNumber = new CallNumber();

        //联系人查找
        callNumber.setName(ContactUtils.getContactName(number));
        //从数据库中查找


        //从网络查找

        return callNumber;
    }


}
