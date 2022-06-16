package com.ebig.socket.netty;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.netty.channel.socket.SocketChannel;

public class GatewayService {

    private static Map<String, SocketChannel> map = new ConcurrentHashMap<>();

    public static void addGatewayChannel(String id, SocketChannel gateway_channel){
        map.put(id, gateway_channel);
    }

    public static Map<String, SocketChannel> getChannels(){
        return map;
    }

    private static SocketChannel getGatewayChannel(String id){
        return map.get(id);
    }

    public static void removeGatewayChannel(String id){
        map.remove(id);
    }

    /**
     * 可在这里用cargo与channel形成映射关系
     * @param cargo
     * @return
     */
    public static SocketChannel getChannerByCargo(int cargo){
        if (map.size()==0){
            return null;
        }
        if (cargo==1){
            return  getGatewayChannel("192.168.1.189");
        }else if (cargo==2){
            return getGatewayChannel("192.168.1.188");
        }
        return map.get(0);
    }
}
