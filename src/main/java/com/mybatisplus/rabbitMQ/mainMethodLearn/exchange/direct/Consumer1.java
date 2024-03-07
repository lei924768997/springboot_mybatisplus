package com.mybatisplus.rabbitMQ.mainMethodLearn.exchange.direct;

import com.mybatisplus.rabbitMQ.mainMethodLearn.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName com.mybatisplus.rabbitMQ.mainMethodLearn.publiserAndConsumer.direct
 * @Author: leiming
 * @CreateTime: 2024/3/6
 * @Description:
 */
public class Consumer1 {

    private static final String QUEUENAME_1="lei_03";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUENAME_1,false,false,false,null);

        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(new String(body));
            }
        };
        channel.basicConsume(QUEUENAME_1,true,defaultConsumer);
    }
}
