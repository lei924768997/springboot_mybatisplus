package com.mybatisplus.rabbitMQ.topic.consumer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @ClassName com.mybatisplus.rabbitMQ.topic.consumer
 * @Author: leiming
 * @CreateTime: 2024/3/7
 * @Description:
 */
@Component
public class TopicConsumer {

    @RabbitListener(queues = "MQ_02")
    public void receiveMsg1(Channel channel, Message message,String msg) throws IOException {
        System.out.println("MQ_02收到消息：" + msg);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }

    @RabbitListener(queues = "MQ_03")
    public void receiveMsg2(Channel channel,Message message,String msg) throws IOException {

        System.out.println("MQ_03收到消息：" + msg);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }
}
