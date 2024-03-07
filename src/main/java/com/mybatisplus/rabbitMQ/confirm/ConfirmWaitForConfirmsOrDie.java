package com.mybatisplus.rabbitMQ.confirm;

import com.mybatisplus.rabbitMQ.mainMethodLearn.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName com.mybatisplus.rabbitMQ.confirm
 * @Author: leiming
 * @CreateTime: 2024/3/7
 * @Description:批量确认
 */
public class ConfirmWaitForConfirmsOrDie {

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        //声明消息队列
        channel.queueDeclare("tran_01", false, false, false, null);
        //开启消息发送确认模式
        channel.confirmSelect();
        //发布消息
        channel.basicPublish("", "tran_01", MessageProperties.TEXT_PLAIN, "hello word!".getBytes());
        //批量确认
        channel.waitForConfirmsOrDie();
    }
}
