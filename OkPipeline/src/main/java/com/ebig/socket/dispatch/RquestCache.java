package com.ebig.socket.dispatch;

import com.ebig.annotation.ThreadIo;
import com.ebig.socket.entity.CmdRequestInfo;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 请求缓存
 */
public class RquestCache {
    //每个请求缓存30秒后清除
    private static final long existTime = 30000;
    private static ConcurrentHashMap<String, CmdRequestCacheInfo> requestMap = new ConcurrentHashMap<>();

    public static void addRequest(String order, CmdRequestInfo info) {
        requestMap.put(order, new CmdRequestCacheInfo(System.currentTimeMillis(), info));
        reSize();
    }

    @ThreadIo
    private static void reSize() {
        for (String key : requestMap.keySet()) {
            CmdRequestCacheInfo info = requestMap.get(key);
            if ((info.t() - System.currentTimeMillis()) > existTime) {
                requestMap.remove(key);
            }
        }
    }

    public static CmdRequestInfo get(String order) {
        if (requestMap.contains(order)) {
            return requestMap.get(order).get();
        }
        return null;
    }

}
