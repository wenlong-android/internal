package com.ebig.temperature_humidity;

import com.ebig.annotation.ThreadComputation;
import com.ebig.annotation.ThreadIo;
import com.ebig.common.Team;
import com.ebig.crosso.manager.db.upload.ThDbInfo;
import com.ebig.crosso.manager.db.upload.UploaManager;
import com.ebig.socket.bean.TeHuEntity;
import com.ebig.socket.bean.ThEntity;
import com.ebig.utils.GsonUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class ThCacheFactory {
    private static long currentStamp = System.currentTimeMillis();
    private static ReentrantLock lock = new ReentrantLock();
    private static ConcurrentHashMap<String, List<TeHuEntity>> cacheMap = new ConcurrentHashMap<>();

    public static void submitConfirmation(ThEntity thEntity) {
    }

    public static void onOrder(String host, String uuid, double t, double h) {
        uuid="a00005061-00000001";
        final long timeStamp = System.currentTimeMillis();
        TeHuEntity.DataBean bean = new TeHuEntity.DataBean(t, h);
        TeHuEntity teHuEntity = new TeHuEntity(timeStamp, 1, uuid, bean);
        ThDbInfo info = new ThDbInfo();
        info.setHost(host);
        info.setUuid(uuid);
        info.setTemperatureInt(t);
        info.setHumidityInt(h);
        info.setId(timeStamp);
        //
        inserDbfirst(info, teHuEntity);
    }

    public static void add(String host, TeHuEntity entity) {
        if (cacheMap.containsKey(host)) {
            cacheMap.get(host).add(entity);
        } else {
            List<TeHuEntity> list = new ArrayList<>();
            list.add(entity);
            cacheMap.put(host, list);
        }
        if (needUpdate()) {
            resizeHashMap();
        }
    }

    @ThreadComputation(user = Team.wwl, lib = "pipeline")
    private static void resizeHashMap() {
        lock.lock();
        List<TeHuEntity> uploadList = new ArrayList<>();
        for (String key : cacheMap.keySet()) {
            List<TeHuEntity> list = cacheMap.get(key);
            TeHuEntity entity = getMaxValue(list);
            uploadList.add(entity);
        }
        cacheMap.clear();
        start2Upload(uploadList);
        lock.unlock();

    }

    private static int count = 0;

    private static synchronized void start2Upload(List<TeHuEntity> uploadList) {
        if (uploadList.size() == 0) {
            return;
        }

        count = 0;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                TeHuEntity e = uploadList.get(count);
                count++;
                if (count >= uploadList.size()) {
                    timer.cancel();
                }
            }
        }, 10, 1000);
    }

    private static TeHuEntity getMaxValue(List<TeHuEntity> list) {
        double t = 0;
        double h = 0;
        int type = 1;
        String uuid = "";
        for (TeHuEntity entity : list) {
            TeHuEntity.DataBean dataBean = entity.getData();
            entity.getFactoryCode();
            if (t == 0) {
                t = dataBean.getTemperature();
            } else if (t < dataBean.getTemperature()) {
                t = dataBean.getTemperature();
            }
            if (h == 0) {
                h = dataBean.getTemperature();
            } else if (h < dataBean.getHumidity()) {
                h = dataBean.getHumidity();
            }
        }
        TeHuEntity teHuEntity = null;// new TeHuEntity(type, uuid, new TeHuEntity.DataBean(t, h));
//        inserDb(teHuEntity);
        return teHuEntity;
    }

    @ThreadIo
    private static void inserDbfirst(ThDbInfo info, TeHuEntity teHuEntity) {
        String gson = GsonUtils.toJson(teHuEntity);
        info.setCommit(0);
        info.setJson(gson);
        //入库
        UploaManager.inser(info);
        //提交给服务器
        ThClient.send(gson);
        //UpLoadDb.with(AppGlobals.getApplication()).getTHDao().insert(info);

    }


    private static void send(ThEntity entity) {
        ThClient.send(new Gson().toJson(entity));
    }

    private static boolean needUpdate() {
        boolean needUpdate = (System.currentTimeMillis() - currentStamp) > 300000;
        if (needUpdate) {
            currentStamp = System.currentTimeMillis();
        }
        return needUpdate;
    }
}
