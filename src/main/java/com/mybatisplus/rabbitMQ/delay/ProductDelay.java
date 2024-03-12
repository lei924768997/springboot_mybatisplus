package com.mybatisplus.rabbitMQ.delay;

import com.mybatisplus.rabbitMQ.mainMethodLearn.ConnectionUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName com.mybatisplus.rabbitMQ.delay
 * @Author: leiming
 * @CreateTime: 2024/3/8
 * @Description: 以死信的方式实现延时队列
 */
public class ProductDelay {

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        //延时队列delay_01和延时交换机delayExchange_01，在路由delayRoute_01将队列和交换机绑定
        channel.exchangeDeclare("delayExchange_01","direct");
        channel.queueDeclare("delay_01",false,false,false,null);
        channel.queueBind("delay_01","delayExchange_01","delayRoute_01");

        //申明一个队列，设置将不需要的消息放在另外的队列中
        Map map= new HashMap<>();
        map.put("x-dead-letter-exchange","delayExchange_01");
        map.put("x-dead-letter-routing-key","delayRoute_01");
        channel.queueDeclare("runMq_02",false,false,false,map);

        //设置消息的过期时间为5秒，超时后，发送到延时队列
        AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties.Builder();
        builder.expiration("5000");
        AMQP.BasicProperties build = builder.build();
        //发送消息
        channel.basicPublish("","runMq_02",build,"hello word".getBytes());
        connection.close();
    }
}
