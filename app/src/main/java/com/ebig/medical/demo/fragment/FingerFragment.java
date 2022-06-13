package com.ebig.medical.demo.fragment;

import android.view.View;
import android.widget.Button;

import com.ebig.log.ELog;
import com.ebig.medical.demo.R;
import com.ebig.medical.demo.R2;
import com.ebig.socket.entity.FingerResult;
import com.ebig.socket.dispatchWrite.finger.FingerParam;
import com.ebig.socket.idl.FingerGetAllIdListenner;
import com.ebig.socket.idl.SenderListenner;
import com.ebig.socket.listenner.IFingerRegistListenner;
import com.ebig.socket.listenner.Listenner4Finger;
import com.ebig.utils.HexUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class FingerFragment extends BaseFrament implements IFingerRegistListenner {
    @BindView(R2.id.btn_regist)
    Button btnRegist;
    @BindView(R2.id.btn_cancle)
    Button btnCancle;
    @BindView(R2.id.btn_delete)
    Button btnDelete;
    @BindView(R2.id.btn_deleteAll)
    Button btnDeleteAll;
    @BindView(R2.id.btn_getAllId)
    Button btnGetAllId;
    @BindView(R2.id.btn_getIdTemplates)
    Button btnGetIdTemplates;
    @BindView(R2.id.btn_loadTemplates)
    Button btnLoadTemplates;

    @Override
    protected String getTagName() {
        return this.getClass().getName();
    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_finger;
    }

    @Override
    protected void getParentView(View view) {

    }

    @Override
    protected void onCreate() {
        Listenner4Finger.setRegistListenner(FingerFragment.this);
       String fingerId= HexUtils.getFingerId(1,1);
       ELog.print("fingerId:"+fingerId);
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

    @OnClick({R2.id.btn_regist, R2.id.btn_cancle, R2.id.btn_delete, R2.id.btn_deleteAll, R2.id.btn_getAllId, R2.id.btn_getIdTemplates, R2.id.btn_loadTemplates})
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.btn_regist) {
            //之前支持 pid 18 gid 36
            jesse.commander().withFinger()
                    .regist(new FingerParam(FingerResult.regist, 1, 1)).sendTo(1, 0, 0);
        } else if (viewId == R.id.btn_cancle) {
            jesse.commander().withFinger()
                    .cancle(new FingerParam(FingerResult.cancle, 1, 1)).sendTo(1, 0, 0);
        } else if (viewId == R.id.btn_delete) {

            jesse.commander().withFinger().delete(new FingerParam(FingerResult.delete, 1, 1)).sendTo(1, 0, 0);
        } else if (viewId == R.id.btn_deleteAll) {

            jesse.commander().withFinger().deleteAll(new FingerParam(FingerResult.deleteAll, 1, 1)).sendTo(1, 0, 0);

        } else if (viewId == R.id.btn_getAllId) {
            jesse.commander().withFinger()
                    .getFingerId(new FingerParam(FingerResult.getAllId, 1, 1))
                    .addListenner(new FingerGetAllIdListenner() {
                        @Override
                        public void onResult(List<String> result) {
                            for (int i = 0; i < result.size(); i++) {
                               ELog.print("获取到数据id "+i+" :" + result.get(i));
                            }

                        }

                        @Override
                        public void onFail(String msg) {

                        }
                    }).sendTo(1, 0, 0);
        } else if (viewId == R.id.btn_getIdTemplates) {
        } else if (viewId == R.id.btn_loadTemplates) {
        }
    }

    @Override
    public void startAndPutFinger2Regiest() {
        ELog.print("FingerFragment startAndPutFinger2Regiest");
    }

    @Override
    public void firstRegistSuccess() {
        ELog.print("FingerFragment firstRegistSuccess");
    }

    @Override
    public void secondTimePutFinger2Regiest() {
        ELog.print("FingerFragment secondTimePutFinger2Regiest");
    }

    @Override
    public void secondRegistSuccess() {
        ELog.print("FingerFragment secondRegistSuccess");
    }

    @Override
    public void thirdAndPutFinger2Regiest() {
        ELog.print("FingerFragment thirdAndPutFinger2Regiest");
    }

    @Override
    public void thirdRegistSuccess() {
        ELog.print("FingerFragment thirdRegistSuccess");
    }

    @Override
    public void tooMuchDifferenceRegisterAgain() {
        ELog.print("FingerFragment tooMuchDifferenceRegisterAgain");
    }

    @Override
    public void registFinallySuccess() {
    ELog.print("FingerFragment registFinallySuccess");
    }
}
