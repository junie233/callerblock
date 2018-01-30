package com.junie.callerblock.db.adapter;

import com.junie.callerblock.entity.CallNumber;

/**
 * Created by niejun on 2018/1/29.
 */

public class CallDao extends AbstractDao<Long,CallNumber> {

    @Override
    protected org.greenrobot.greendao.AbstractDao getAbstractDao() {
        return daoSession.getCallNumberDao();
    }




}
