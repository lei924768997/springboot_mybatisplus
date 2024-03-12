package com.mybatisplus.rabbitMQ.dead;

import com.mybatisplus.rabbitMQ.mainMethodLearn.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName com.mybatisplus.rabbitMQ.dead
 * @Author: leiming
 * @CreateTime: 2024/3/8
 * @Description:死信消息的接收者
 */
public class ConsumerDead {

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("deadMq_01",false,false,false,null);

        channel.basicConsume("deadMq_01",false,new DefaultConsumer(channel){
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("收到死信消息" + new String(body));
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });

    }
}
