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
 * @Description: 异步确认
 */
public class ConfirmListener {

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        //声明消息队列
        channel.queueDeclare("tran_01", false, false, false, null);
        //开启消息发送确认模式
        channel.confirmSelect();

        for (int i = 0; i < 10; i++) {
            //发布消息
            channel.basicPublish("", "tran_01", MessageProperties.TEXT_PLAIN, "hello word!".getBytes());
        }
        channel.addConfirmListener(new com.rabbitmq.client.ConfirmListener() {
            //第一个参数：消息的下标
            //第二个参数：消息的确认方式 true:多条一起确认 false:单条被确认
            @Override
            public void handleAck(long l, boolean b) throws IOException {
                System.out.println("消息"+l+"--->"+b);
            }

            @Override
            public void handleNack(long l, boolean b) throws IOException {
                System.out.println("消息"+l+"--->"+b);
            }
        });
    }
}
