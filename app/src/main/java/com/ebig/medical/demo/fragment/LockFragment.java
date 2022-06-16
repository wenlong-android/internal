package com.ebig.medical.demo.fragment;

import android.view.View;
import android.widget.Button;

import com.ebig.medical.demo.R;
import com.ebig.medical.demo.R2;
import com.ebig.socket.common.AndPipe;

import butterknife.BindView;
import butterknife.OnClick;

public class LockFragment extends BaseFrament {
    @BindView(R2.id.btn_openMainDoor)
    Button btnOpenMainDoor;
    @BindView(R.id.btn_openFrontDoor)
    Button btnOpenFrontDoor;
    @BindView(R.id.btn_openbackDoor)
    Button btnOpenbackDoor;

    @Override
    protected String getTagName() {
        return LockFragment.this.getClass().getName();
    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_lock;
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


    @OnClick({R2.id.btn_openMainDoor, R.id.btn_openFrontDoor, R.id.btn_openbackDoor})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_openMainDoor:
                AndPipe.lock().openFrontDoor()
//                        .addListenner(new SenderLockListenner() {
//                    @Override
//                    public void lockCall() {
//                        showMsg("门锁自动打开或关闭");
//                    }
//
//                    @Override
//                    public void doorOpen() {
//                        showMsg("门手动打开");
//                    }
//
//                    @Override
//                    public void doorClose() {
//                        showMsg("门手动关闭");
//                    }
//
//                    @Override
//                    public void onFail(String msg) {
//
//                    }
//
//        })
                        .sendTo(255,255,255);
                break;
            case R.id.btn_openFrontDoor:
                AndPipe.lock().openBackDoor()
//                        .addListenner(new SenderLockListenner() {
//                    @Override
//                    public void lockCall() {
//
//                    }
//
//                    @Override
//                    public void doorOpen() {
//
//                    }
//
//                    @Override
//                    public void doorClose() {
//                        showMsg("门手动关闭");
//                    }
//
//                    @Override
//                    public void onFail(String msg) {
//
//                    }
//                })
                        .sendTo(255,255,255);
                break;
            case R.id.btn_openbackDoor:
                AndPipe.lock().openBothDoor()
//                        .addListenner(new SenderLockListenner() {
//                    @Override
//                    public void lockCall() {
//                       ELog.print("lockCall");
//                    }
//
//                    @Override
//                    public void doorOpen() {
//                       ELog.print("doorOpen");
//                    }
//
//                    @Override
//                    public void doorClose() {
//                       ELog.print("doorClose");
//                    }
//
//                    @Override
//                    public void onFail(String msg) {
//                       ELog.print("onFail:"+msg);
//                    }
//                })
                        .sendTo(255,255,255);
                break;
        }
    }
}
