package com.mybatisplus.rabbitMQ.sample.Consumer;

import com.mybatisplus.pojo.SysUser;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @ClassName com.mybatisplus.rabbitMQ.Consumer
 * @Author: leiming
 * @CreateTime: 2024/3/7
 * @Description:消费者
 */
@Component
public class SampleConsumer {

    @RabbitListener(queues = "MQ_01")
    public void receive(SysUser sysUser, Channel channel, Message message) throws IOException {
        System.out.println("收到用户：" + sysUser);
        //basicAck方法是手动确认，message.getMessageProperties().getDeliveryTag()获取当前消息的下标，false是手动确认
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }
}
