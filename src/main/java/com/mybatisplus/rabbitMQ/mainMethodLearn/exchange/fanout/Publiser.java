package com.mybatisplus.rabbitMQ.mainMethodLearn.exchange.fanout;

import com.mybatisplus.rabbitMQ.mainMethodLearn.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName com.mybatisplus.rabbitMQ.publiserAndSubscribe
 * @Author: leiming
 * @CreateTime: 2024/3/5
 * @Description: rabbitmq的发布和订阅模式
 *  发布者
 */
public class Publiser {
    private static final String  EXCHANGE_NAME="exchange_01";

    public static void main(String[] args) throws IOException, TimeoutException {
        //获取连接
        Connection connection = ConnectionUtil.getConnection();
        //创建通道
        Channel channel = connection.createChannel();
        /*
            创建交换机
            该方法中的第二个参数：1、fanout 扇出，无意识的广播，不在乎消费者是否存在
                              2、direct  3、headers  4、topic
         */
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");

        for (int i = 0; i < 100; i++) {
            String msg = "hello word" +i;
            channel.basicPublish(EXCHANGE_NAME,"",null,msg.getBytes());
        }


    }
}
