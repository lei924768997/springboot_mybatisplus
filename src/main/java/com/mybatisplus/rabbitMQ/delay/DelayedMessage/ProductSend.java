package com.mybatisplus.rabbitMQ.delay.DelayedMessage;

import com.mybatisplus.rabbitMQ.mainMethodLearn.ConnectionUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName com.mybatisplus.rabbitMQ.delay.DelayedMessage
 * @Author: leiming
 * @CreateTime: 2024/3/8
 * @Description:
 */
public class ProductSend {

    private static final String exchange_name = "delay_exchange_01";

    private static final String queue_name = "test_queue";

    private static final String routingKey = "test_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        Map<String,Object> map = new HashMap<>();
        map.put("x-delayed-type","direct");
        //创建路由
        channel.exchangeDeclare(exchange_name,"x-delayed-message",false,false,map);
        //创建队列
        channel.queueDeclare(queue_name,false,false,false,null);
        //绑定路由、队列
        channel.queueBind(queue_name,exchange_name,routingKey);

        //发送
        String msg = "延时消息";
        AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties.Builder();
        Map<String,Object> heads = new HashMap<>();
        heads.put("x-delay",4000);
        builder.headers(heads);
        AMQP.BasicProperties properties = builder.build();
        channel.basicPublish(exchange_name,queue_name,properties,msg.getBytes());
        channel.close();
        connection.close();
    }
}
