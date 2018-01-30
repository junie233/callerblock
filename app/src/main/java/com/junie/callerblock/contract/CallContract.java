package com.junie.callerblock.contract;

/**
 * Created by niejun on 2018/1/29.
 */

public interface CallContract {

    interface View extends BaseView<Presenter> {

    }
    interface Presenter extends BasePresenter {

        void handleRing(String number);

    }

}
