package com.junie.callerblock.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.junie.callerblock.R;
import com.junie.callerblock.contract.MainContract;

public class MainActivity extends AppCompatActivity implements MainContract.View{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {

    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {

    }
}
