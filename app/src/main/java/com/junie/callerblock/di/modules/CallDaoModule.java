package com.junie.callerblock.di.modules;

import com.junie.callerblock.gen.CallNumberDao;

import dagger.Module;

/**
 * Created by niejun on 2018/1/29.
 */
@Module
public class CallDaoModule {

    CallNumberDao provideDao() {
        return new CallNumberDao();
    }



}
