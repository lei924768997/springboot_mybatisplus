package com.mybatisplus.rabbitMQ.mainMethodLearn.exchange.topic;

import com.mybatisplus.rabbitMQ.mainMethodLearn.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName com.mybatisplus.rabbitMQ.mainMethodLearn.publiserAndConsumer.topic
 * @Author: leiming
 * @CreateTime: 2024/3/6
 * @Description: 主题模式   又叫通配符模式
 */
public class Produce {

    private static final String EXANGE_NAME = "topic_01";

    public static void main(String[] args) throws IOException, TimeoutException {
        //获取连接
        Connection connection = ConnectionUtil.getConnection();
        //创建通道
        Channel channel = connection.createChannel();
        //声明交换机，模式是主题模式又叫通配符模式
        channel.exchangeDeclare(EXANGE_NAME,"topic");

        channel.basicPublish(EXANGE_NAME,"order.xian",null,new String("西安的订单").getBytes());
        channel.basicPublish(EXANGE_NAME,"order.jiangsu",null,new String("江苏的订单").getBytes());
        channel.basicPublish(EXANGE_NAME,"rollback.xian",null,new String("西安的订单").getBytes());
        channel.basicPublish(EXANGE_NAME,"rollback.xian",null,new String("西安的订单").getBytes());
    }
}
