package com.junie.callerblock.di;

import com.junie.callerblock.di.modules.CallModule;
import com.junie.callerblock.receiver.InCallListener;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by niejun on 2018/1/29.
 */

@Singleton
@Component(modules = {CallModule.class})
public interface CallComponent {

    void inject(InCallListener listener);



}
