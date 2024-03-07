package com.mybatisplus.rabbitMQ.mainMethodLearn.exchange.topic;

import com.mybatisplus.rabbitMQ.mainMethodLearn.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName com.mybatisplus.rabbitMQ.mainMethodLearn.publiserAndConsumer.topic
 * @Author: leiming
 * @CreateTime: 2024/3/6
 * @Description:
 */
public class Consumer3 {
    private static final String EXANGE_NAME = "topic_01";

    private static  final String QUEUENAME="topic_queue_3";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXANGE_NAME,"topic");
        channel.queueDeclare(QUEUENAME,false,false,false,null);

        channel.queueBind(QUEUENAME,EXANGE_NAME,"#");
        channel.basicConsume(QUEUENAME,true,new DefaultConsumer(channel){
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者3-》"+new String(body));
            }
        });

    }
}
