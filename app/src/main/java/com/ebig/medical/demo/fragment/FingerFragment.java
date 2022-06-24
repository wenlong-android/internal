package com.ebig.medical.demo.fragment;

import android.view.View;
import android.widget.Button;

import com.ebig.idl.CommonCall;
import com.ebig.log.ELog;
import com.ebig.medical.demo.R;
import com.ebig.medical.demo.R2;
import com.ebig.socket.common.AndPipe;
import com.ebig.socket.entity.FingerResult;
import com.ebig.socket.dispatchWrite.finger.FingerParam;
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
       Listenner4Finger.setUnLockListenner(new CommonCall<String>() {
           @Override
           public void onCommonCall(String s) {
               ELog.print("收到手指id:"+s);
           }
       });
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
            AndPipe.finger()
                    .regist(new FingerParam(FingerResult.regist, 1, 1), new IFingerRegistListenner() {
                        @Override
                        public void startAndPutFinger2Regiest() {

                        }

                        @Override
                        public void firstRegistSuccess() {

                        }

                        @Override
                        public void secondTimePutFinger2Regiest() {

                        }

                        @Override
                        public void secondRegistSuccess() {

                        }

                        @Override
                        public void thirdAndPutFinger2Regiest() {

                        }

                        @Override
                        public void thirdRegistSuccess() {

                        }

                        @Override
                        public void tooMuchDifferenceRegisterAgain() {

                        }

                        @Override
                        public void registFinallySuccess() {

                        }
                    }).sendTo(1, 0, 0);
        } else if (viewId == R.id.btn_cancle) {
            AndPipe.finger()
                    .cancle(new FingerParam(FingerResult.cancle, 1, 1), new CommonCall<Boolean>() {
                        @Override
                        public void onCommonCall(Boolean aBoolean) {
                            ELog.print("onCommonCall:"+aBoolean);
                        }
                    }).sendTo(1, 0, 0);
        } else if (viewId == R.id.btn_delete) {

            AndPipe.finger().delete(new FingerParam(FingerResult.delete, 1, 1), new CommonCall<Boolean>() {
                @Override
                public void onCommonCall(Boolean aBoolean) {
                    ELog.print("onCommonCall:"+aBoolean);
                }
            }).sendTo(1, 0, 0);
        } else if (viewId == R.id.btn_deleteAll) {
            AndPipe.finger().deleteAll(new FingerParam(FingerResult.deleteAll, 1, 1), new CommonCall<Boolean>() {
                @Override
                public void onCommonCall(Boolean aBoolean) {
                    ELog.print("onCommonCall:"+aBoolean);
                }
            }).sendTo(1, 0, 0);

        } else if (viewId == R.id.btn_getAllId) {
            AndPipe.finger()
                    .getFingerId(new FingerParam(FingerResult.getAllId, 1, 1),
                            new CommonCall<List<String>>() {
                                @Override
                                public void onCommonCall(List<String> list) {
                                    if (list!=null)
                                    ELog.print("onCommonCall:"+list.toString());
                                }
                            }
                    ).sendTo(1, 0, 0);
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
