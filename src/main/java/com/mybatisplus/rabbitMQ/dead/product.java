package com.mybatisplus.rabbitMQ.dead;

import com.mybatisplus.rabbitMQ.mainMethodLearn.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName com.mybatisplus.rabbitMQ.dead
 * @Author: leiming
 * @CreateTime: 2024/3/8
 * @Description: 死信队列的学习
 */
public class product {

    public static void main(String[] args) throws IOException, TimeoutException {

        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        //创建一个接收死信的路由和队列
        channel.exchangeDeclare("deadExchange_01","direct");
        channel.queueDeclare("deadMq_01",false,false,false,null);
        channel.queueBind("deadMq_01","deadExchange_01","deadRoutMq_01");

        Map<String,Object> map = new HashMap<>();
        map.put("x-max-length",10);//最大接收10条消息
        map.put("x-dead-letter-exchange","deadExchange_01");//配置超出10条消息进入死信交换机
        map.put("x-dead-letter-routing-key","deadRoutMq_01");//配置死信队列的路由键
        map.put("x-overflow","drop-head");//不设置时默认drop-head参数，代表只接收后10条消息，前10条进入死信队列
        channel.queueDeclare("runMq_01",false,false,false,map);

        for (int i = 0; i < 20; i++) {
            channel.basicPublish("","runMq_01",null,new String("hello word_" + i).getBytes());
        }

        connection.close();
    }
}
