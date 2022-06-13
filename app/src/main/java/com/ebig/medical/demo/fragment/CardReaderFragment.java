package com.ebig.medical.demo.fragment;

import android.view.View;

import com.ebig.medical.demo.R;

public class CardReaderFragment extends BaseFrament{
    @Override
    protected String getTagName() {
        return this.getClass().getName();
    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_card_reader;
    }

    @Override
    protected void getParentView(View view) {

    }

    @Override
    protected void onCreate() {

    }

    @Override
    protected void onDestory() {

    }

    @Override
    protected void onFragmentPause() {

    }

    @Override
    protected void onFragmentResume() {

    }

    @Override
    public void initSocketListenner() {

    }

    @Override
    public void onClientConnected() {

    }

    @Override
    public void onClientDisConnected() {

    }

}
