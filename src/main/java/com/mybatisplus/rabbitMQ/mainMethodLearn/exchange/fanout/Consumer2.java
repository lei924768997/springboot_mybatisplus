package com.mybatisplus.rabbitMQ.mainMethodLearn.exchange.fanout;

import com.mybatisplus.rabbitMQ.mainMethodLearn.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName com.mybatisplus.rabbitMQ.mainMethodLearn.publiserAndConsumer
 * @Author: leiming
 * @CreateTime: 2024/3/5
 * @Description: 消费者
 */
public class Consumer2 {

    private static final String  EXCHANGE_NAME="exchange_01";
    private static  final String QUEUENAME="leim_03";
    public static void main(String[] args) throws IOException, TimeoutException {
        //获取连接
        Connection connection = ConnectionUtil.getConnection();
        //创建通道
        Channel channel = connection.createChannel();
        //限流 每次进来1条
        channel.basicQos(1);
         /*
            创建交换机
            该方法中的第二个参数：1、fanout 扇出，无意识的广播，不在乎消费者是否存在
                              2、direct  3、headers  4、topic
         */
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");
        //声明消息队列
        channel.queueDeclare(QUEUENAME,false,false,false,null);
        //交换机和队列绑定  在广播模式下，第三个参数无效，这个参数意思时路由键
        channel.queueBind(QUEUENAME,EXCHANGE_NAME,"");
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel){
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者2接收了消息");
                //手工确认当前消息
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };
        //接收消息  第二个参数是自动确认消息，收到消息就确认这条消息 false时需要确认消息
        channel.basicConsume(QUEUENAME, false, defaultConsumer);
    }
}
