package com.junie.callerblock.di.modules;

import com.junie.callerblock.contract.CallContract;
import com.junie.callerblock.presenter.CallPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by niejun on 2018/1/29.
 */
@Module
public class CallModule {

    CallContract.View view;


    public CallModule(CallContract.View view) {
        this.view = view;
    }

    @Provides
    CallContract.Presenter providePresenter() {
        return new CallPresenter(view);
    }





}
