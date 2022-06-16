package com.ebig.medical.demo.fragment;

import android.view.View;
import android.widget.Button;

import com.ebig.http.ApiParamsAll;
import com.ebig.log.ELog;
import com.ebig.medical.demo.R;
import com.ebig.medical.demo.R2;
import com.ebig.socket.common.AndPipe;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.OnClick;

public class BackLightFragment extends BaseFrament {
    @BindView(R2.id.btn_turnOn)
    Button btnTurnOn;
    @BindView(R2.id.btn_turnOff)
    Button btnTurnOff;

    @Override
    protected String getTagName() {
        return this.getClass().getName();
    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_back_light;
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

    @OnClick({R2.id.btn_turnOn, R2.id.btn_turnOff})
    public void onClick(View view) {
        int viewId = view.getId();
        System.out.println("addTask sendTo println");
        ELog.print("addTask sendTo btn_turnOff...");
        if (viewId == R.id.btn_turnOn) {
            print();
            AndPipe.getSender().backLight().on().sendTo(1, 0, 0);
        } else if (viewId == R.id.btn_turnOff) {
            AndPipe.getSender(). backLight().off().sendTo(1, 0, 0);
        }

    }
    private void print(){
        long nowTome=System.currentTimeMillis();
        long start=System.currentTimeMillis()-1000*60*60*24*7;
        ApiParamsAll params=new ApiParamsAll(1,1000,"deviceBaseUser",start,nowTome);
        ELog.print(new Gson().toJson(params));
    }
}
