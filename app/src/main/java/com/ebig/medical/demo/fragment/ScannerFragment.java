package com.ebig.medical.demo.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ebig.annotation.ThreadMain;
import com.ebig.idl.CommonCall;
import com.ebig.log.ELog;
import com.ebig.medical.demo.R;
import com.ebig.medical.demo.R2;
import com.ebig.socket.common.AndPipe;

import butterknife.BindView;
import butterknife.OnClick;

public class ScannerFragment extends BaseFrament {
    @BindView(R2.id.btn_start)
    Button btnStart;
    @BindView(R2.id.btn_result)
    EditText btnResult;

    @Override
    protected String getTagName() {
        return this.getClass().getName();
    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_scanner;
    }

    @Override
    protected void getParentView(View view) {

    }

    @Override
    protected void onCreate() {
    }

    @ThreadMain
    private void setResult(EditText btnResult, String result) {
        btnResult.setText(result);
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

    @OnClick(R2.id.btn_start)
    public void onClick() {
        AndPipe.scanner().scan(new CommonCall<String>() {
            @Override
            public void onCommonCall(String result) {
                ELog.print("onCommonCall:"+result);
                setResult(btnResult,result);
            }
        }).sendTo(1, 0, 0);
    }
}
