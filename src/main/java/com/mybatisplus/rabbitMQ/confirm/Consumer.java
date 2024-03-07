package com.mybatisplus.rabbitMQ.confirm;

import com.mybatisplus.rabbitMQ.mainMethodLearn.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName com.mybatisplus.rabbitMQ.confirm
 * @Author: leiming
 * @CreateTime: 2024/3/7
 * @Description:
 */
public class Consumer {

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare("tran_01",false,false,false,null);
        channel.basicConsume("tran_01",false,new DefaultConsumer(channel){
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("收到消息了："+new String("body"));
            }
        });
    }
}
