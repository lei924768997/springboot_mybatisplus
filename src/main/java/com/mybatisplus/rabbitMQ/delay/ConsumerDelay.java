package com.mybatisplus.rabbitMQ.delay;

import com.mybatisplus.rabbitMQ.mainMethodLearn.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName com.mybatisplus.rabbitMQ.delay
 * @Author: leiming
 * @CreateTime: 2024/3/8
 * @Description:
 */
public class ConsumerDelay {

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        //创建延时的消息队列
        channel.queueDeclare("delay_01",false,false,false,null);
        //接收到延时消息
        channel.basicConsume("delay_01",false,new DefaultConsumer(channel){
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("收到延时消息"+new String(body));
                //false是手工确认  true是自动确认
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });
    }
}
