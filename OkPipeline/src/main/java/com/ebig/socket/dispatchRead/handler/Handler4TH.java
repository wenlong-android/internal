package com.ebig.socket.dispatchRead.handler;

import com.ebig.socket.common.AndPipe;
import com.ebig.socket.listenner.Listenner4Th;
import com.ebig.temperature_humidity.ThCacheFactory;
import com.ebig.utils.DoubleUtils;
import com.ebig.utils.HexUtils;
import com.ebig.socket.entity.CmdResultInfo;
import com.ebig.socket.entity.CmdType;

/*温湿度*/
public class Handler4TH implements BaseHandler {
    private ChainHandler handler;

    @Override
    public void bind(ChainHandler baseHandler) {
        this.handler = baseHandler;
    }

    @Override
    public void onNextHanlder(CmdResultInfo info) {
        if (CmdType.isTHUp(info.getOrder())) {
            String th = info.getData();
            String temperature = th.substring(0, 4);
            String humidity = th.substring(4, 8);
            String fianlT = temperature.substring(2, 4) + temperature.substring(0, 2);
            String fianlH = humidity.substring(2, 4) + humidity.substring(0, 2);
            double temperatureInt = (HexUtils.hex2int(fianlT) * 175.72) / 65536.0 - 46.85;
            double humidityInt = (HexUtils.hex2int(fianlH) * 125.0) / 65536.0 - 6;
            long internal = System.currentTimeMillis() - TimeInternal.thStart;
            AndPipe.l().onThCall(temperatureInt, humidityInt, internal);
            TimeInternal.thStart = System.currentTimeMillis();
            double t = DoubleUtils.with2(temperatureInt);
            double h = DoubleUtils.with2(humidityInt);
//            ThEntity.MachineRuntimeStateListBean beanT=new ThEntity.MachineRuntimeStateListBean("temperature",t);
//            ThEntity.MachineRuntimeStateListBean beanH=new ThEntity.MachineRuntimeStateListBean("humidity",h);
//            List< ThEntity.MachineRuntimeStateListBean>list=new ArrayList<>();
//            list.add(beanT);
//            list.add(beanH);
//            ThEntity entity = new ThEntity(1,info.getUuid(),list);
            Listenner4Th.onThRecive(t, h, internal);
            ThCacheFactory.onOrder(info.getHost(), info.getUuid(), t, h);

        } else {
            handler.nextIndex(info);
        }
    }
}
