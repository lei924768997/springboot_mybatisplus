package com.mybatisplus.rabbitMQ.mainMethodLearn.sample;

import com.mybatisplus.rabbitMQ.mainMethodLearn.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName com.mybatisplus.rabbitMQ
 * @Author: leiming
 * @CreateTime: 2024/3/5
 * @Description:
 */
public class ConsumerMsg {

    private static  final String QUEUENAME="leim_01";

    /**
     * @author leim
     * @date 2024/3/5
     * @description rabbitmq可以分为3种模式：
     *      1、简单模式 一个消息队列和一个消费者
     *      2、工作模式 一个消息队列和多个消费者
     *      3、订阅模式 多个消息队列多个消费者
     *     注意：生产者的消息队列的配置 必须和消费者的配置一样
     */
    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接
        Connection connection = ConnectionUtil.getConnection();
        //创建一个渠道 通道
        Channel channel = connection.createChannel();
        //每次拉取1条消息
        channel.basicQos(1);
        //map设置消息的配置
        Map<String,Object> map = new HashMap<>();
        map.put("x-max-length",100);
        //设置溢出规则 x-overflow的配置： 例如：消息队列中有20条消息  1、drop-head代表去掉头部的消息，只读取20条后面的10条消息    2、reject-publish代表去掉尾部的消息  只读取20条前面的10条消息
        map.put("x-overflow","reject-publish");
        //设置该队列的过期时间 5秒后自动删除
        map.put("x-expires",1000*5);
        //设置超出多少字节后删除 单位：字节
        map.put("x-max-length-bytes",10);
        //设置是否持久化，当rabbitmq重启时，设置成持久化，不会丢失消息
        boolean durable = true;
        channel.queueDeclare(QUEUENAME,durable,false,false,map);
        //如果重写handleDelivery方法，获取到里面的消息
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel){
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String s = new String(body);
                System.out.println(s);
                //获取消息队列中这条消息的下标
                long deliveryTag = envelope.getDeliveryTag();
                //只确认当前下标的消息
                channel.basicAck(deliveryTag,false);
                //确认当前下标之前的所有消息
                channel.basicAck(deliveryTag,true);
            }
        };
        //接收消息  第二个参数是自动确认消息，收到消息就确认这条消息 false时需要确认消息
        channel.basicConsume(QUEUENAME, false, defaultConsumer);
    }
}
