package com.ebig.socket.dispatchRead.handler;

import com.ebig.log.ELog;
import com.ebig.socket.common.AndPipe;
import com.ebig.socket.entity.FingerCode;
import com.ebig.socket.entity.TypeConstance;
import com.ebig.socket.listenner.Listenner4Finger;
import com.ebig.utils.StrUtils;
import com.ebig.socket.entity.CmdResultInfo;
import com.ebig.socket.entity.FingerCodeUtils;
import com.ebig.socket.entity.FingerResult;
import com.ebig.socket.entity.CmdType;
import com.ebig.utils.HexUtils;

import java.util.Arrays;
import java.util.List;

/*指静脉*/
public class Handler4Finger implements BaseHandler {
    /*单个数据帧固定长度*/
    private static final int SINGLE_FRSME_LEN = 9;
    private ChainHandler handler;

    @Override
    public void bind(ChainHandler baseHandler) {
        this.handler = baseHandler;
    }

    @Override
    public void onNextHanlder(CmdResultInfo info) {
        if (CmdType.isFingerUp(info.getOrder())) {
            AndPipe.l().receive(info);

            List<String> dataArr = StrUtils.spiltHex(info.getData());
            String resultCode = dataArr.get(0);
            String resultType = dataArr.get(2);
            ELog.print("onNextHanlder指静脉处理:" + FingerCodeUtils.match(resultCode) + " ,data:" + info.getData()
                    + " ,返回码 ：" + resultCode + " ,类型 ：" + resultType);

            switch (resultType) {
                case FingerResult.success:
                   if (resultCode.equals(FingerCode.finger_no_id)){
                       Listenner4Finger.unLockRecive( info.getData().substring(8,12));
                   }
                    break;

                case FingerResult.fail:

                    break;

                case FingerResult.startRegist:
                    if (resultCode.equals(FingerCode.finger_regist_start_1)) {
                        Listenner4Finger.onStartAndPutFinger2Regiest();
                    } else if (resultCode.equals(FingerCode.finger_regist_success_1)) {
                        Listenner4Finger.firstRegistSuccess();
                    } else if (resultCode.equals(FingerCode.finger_regist_start_2)) {
                        Listenner4Finger.secondTimePutFinger2Regiest();
                    } else if (resultCode.equals(FingerCode.finger_regist_success_2)) {
                        Listenner4Finger.secondRegistSuccess();
                    } else if (resultCode.equals(FingerCode.finger_regist_start_3)) {
                        Listenner4Finger.thirdAndPutFinger2Regiest();
                    } else if (resultCode.equals(FingerCode.finger_regist_success_3)) {
                        Listenner4Finger.thirdRegistSuccess();
                    }
                    break;

                case FingerResult.regist:
                    if (resultCode.equals(FingerCode.finger_template_re_regist)) {
                        Listenner4Finger.tooMuchDifferenceRegisterAgain();
                    } else if (resultCode.equals(FingerCode.finger_regist_start_1)) {
                        Listenner4Finger.onStartAndPutFinger2Regiest();
                    } else if (resultCode.equals(FingerCode.finger_regist_success_1)) {
                        Listenner4Finger.firstRegistSuccess();
                    } else if (resultCode.equals(FingerCode.finger_regist_start_2)) {
                        Listenner4Finger.secondTimePutFinger2Regiest();
                    } else if (resultCode.equals(FingerCode.finger_regist_success_2)) {
                        Listenner4Finger.secondRegistSuccess();
                    } else if (resultCode.equals(FingerCode.finger_regist_start_3)) {
                        Listenner4Finger.thirdAndPutFinger2Regiest();
                    } else if (resultCode.equals(FingerCode.finger_regist_success_3)) {
                        Listenner4Finger.thirdRegistSuccess();
                    }
                    break;
                case FingerResult.getAllId:
                    if (resultCode.equals(FingerCode.finger_failed)) {
                        ELog.print("Handler指静脉处理:获取所有手指id 为空");
                        Listenner4Finger.onGetAllFingerCall(null);
                    } else {
                        ELog.print("Handler指静脉处理:获取所有手指id");
                        if (dataArr.size() > SINGLE_FRSME_LEN) {
                            if (dataArr.size() > 14) {
                                String bging = dataArr.get(9);
                                String device = dataArr.get(11);
                                if (bging.equals(TypeConstance.C_3e) & device.equals(TypeConstance.C_ff)) {
                                    int dataLen = HexUtils.hex2int(dataArr.get(12));
                                    String realData = info.getData().substring(28, 28 + 6 * dataLen);
                                    List<String> realArr = StrUtils.spiltHex6(realData);
                                    final List<String> finalArr=StrUtils.removeGroupId(realArr);
                                    ELog.print("Handler指静脉处理 len：" + dataLen + " ，" + Arrays.toString(finalArr.toArray()));
                                    Listenner4Finger.onGetAllFingerCall(finalArr);
                                }
                            }
                        } else {

                        }
                    }

                    break;
                case FingerResult.cancle:
                    if (resultCode.equals(FingerCode.finger_regist_finish)) {
                        ELog.print("Handler指静脉处理:整个指静脉注册成功");
                        Listenner4Finger.registFinallySuccess();
                    } else if (resultCode.equals(FingerCode.finger_regist_success)) {
                        ELog.print("Handler指静脉处理:取消注册手指");
                        Listenner4Finger.onRegistCancle(true);
                    }

                    break;
                case FingerResult.delete:
                    ELog.print("Handler指静脉处理:删除指定手指");
                    if (resultCode.equals(FingerCode.finger_delete_success)) {
                        ELog.print("Handler指静脉处理:整个指静脉注册成功");
                        Listenner4Finger.ondelete();
                    }
                    break;

                case FingerResult.deleteAll:
                    ELog.print("Handler指静脉处理:删除指定手指");
                    if (resultCode.equals(FingerCode.finger_delete_all_success)) {
                        ELog.print("Handler指静脉处理:整个指静脉注册成功");
                        Listenner4Finger.onDeleteAll();
                    }
                    break;
            }
            // dispatchSevice.onHearBeat(System.currentTimeMillis());


        } else {
            handler.nextIndex(info);
        }
    }


}
