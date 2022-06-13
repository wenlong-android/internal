package com.ebig.push;

import com.ebig.http.ApiParams;
import com.ebig.http.APushRaw;
import com.ebig.http.ApushEntity;
import com.ebig.idl.Common2Call;
import com.ebig.log.ELog;
import com.ebig.utils.StrUtils;
import com.google.gson.Gson;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;
public class MqManager {
    private String host;
    public final static String serverHostApan = "/dynamic/masterDetailQuery";
    private static HashMap<String, String> map = new HashMap<>();
    private static class L {
        public static MqManager mqManager = new MqManager();
    }
    public static MqManager l() {
        if (map.size() == 0) {
            intiMap();
        }
        return L.mqManager;
    }

    private static void intiMap() {
        map.put("mitool-upms-biz", "upms");
        map.put("mitool-base-biz", "base");
        map.put("mitool-storage-biz", "storage");
        map.put("mitool-machine-biz", "machine");
    }




    public void init(APush aPush, Common2Call<ApushEntity, String> listenner) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(aPush.host);
        factory.setPort(aPush.prot);
        factory.setUsername(aPush.userName);
        factory.setPassword(aPush.passWord);
        try {
            //连接
            Connection connection = factory.newConnection();
            //通道
            final Channel channel = connection.createChannel();
            //实现Consumer的最简单方法是将便捷类DefaultConsumer子类化。可以在basicConsume 调用上传递此子类的对象以设置订阅：
            channel.basicConsume(aPush.method, false, new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    super.handleDelivery(consumerTag, envelope, properties, body);
                    String msg = new String(body);
                    long deliveryTag = envelope.getDeliveryTag();
                    channel.basicAck(deliveryTag, false);
                    //从message池中获取msg对象更高效
                    try {
                        ELog.print("收到信息：" + msg);
                        if (msg != null && StrUtils.notEmpty(msg)) {
                            APushRaw raw = new Gson().fromJson(msg, APushRaw.class);
                            ApiParams params = new ApiParams(1, 10, raw.getQueryName(), raw.getIds());
                            String key = map.get(raw.getServiceId());
                            ApushEntity entity = new ApushEntity(aPush.serverHost + key + serverHostApan, params);
                            listenner.onCommonCall(entity);
                        }
                    } catch (Exception e) {

                    }

                    listenner.onOpposite(msg);
                }
            });
        } catch (IOException e) {
            listenner.onOpposite(e.getMessage());
        } catch (TimeoutException e) {
            listenner.onOpposite(e.getMessage());
        }
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
