package com.ebig.medical.demo.fragment;

import android.view.View;

import com.ebig.medical.demo.R;
import com.ebig.socket.dispatchWrite.base.EbWriter;
import com.ebig.socket.dispatchWrite.base.ICommand;

public class ScaleFragment extends BaseFrament{
    @Override
    protected String getTagName() {
        return this.getClass().getName();
    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_scale;
    }

    @Override
    protected void getParentView(View view) {

    }

    @Override
    protected void onCreate() {
        ICommand ICommand = EbWriter.l();
        ICommand.scale().upload(true).sendTo(1,0,0);
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
