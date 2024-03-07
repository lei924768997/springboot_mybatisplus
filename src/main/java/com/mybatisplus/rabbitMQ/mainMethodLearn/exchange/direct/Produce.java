package com.mybatisplus.rabbitMQ.mainMethodLearn.exchange.direct;

import com.mybatisplus.rabbitMQ.mainMethodLearn.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName com.mybatisplus.rabbitMQ.mainMethodLearn.publiserAndConsumer.direct
 * @Author: leiming
 * @CreateTime: 2024/3/6
 * @Description:  发布和订阅者模式2 ：路由模式
 */
public class Produce {

    private static final String EXCHANGENAME="exchange_02";
    private static final String QUEUENAME_1="lei_03";
    private static final String QUEUENAME_2="lei_04";

    public static void main(String[] args) throws IOException, TimeoutException {

        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        //direct模式：交换模式
        channel.exchangeDeclare(EXCHANGENAME,"direct");

        String msg = "green";
        //创建队列
        channel.queueDeclare(QUEUENAME_1,false,false,false,null);
        //队列和交换机绑定，“green”是路由键
        channel.queueBind(QUEUENAME_1,EXCHANGENAME,"green");
        //发送消息到这个交换机下的green路由键中
        channel.basicPublish(EXCHANGENAME,"green",null,msg.getBytes());

        msg = "yellow";
        channel.queueDeclare(QUEUENAME_2,false,false,false,null);
        channel.queueBind(QUEUENAME_2,EXCHANGENAME,"yellow");
        channel.basicPublish(EXCHANGENAME,"yellow",null,msg.getBytes());
    }
}
