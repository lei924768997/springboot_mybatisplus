package com.mybatisplus.rabbitMQ.mainMethodLearn.sample;

import com.mybatisplus.rabbitMQ.mainMethodLearn.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName com.mybatisplus.rabbitMQ
 * @Author: leiming
 * @CreateTime: 2024/3/4
 * @Description:
 */
public class ProduceMsg {

    private static  final String QUEUENAME="leim_01";
    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接
        Connection connection = ConnectionUtil.getConnection();
        //创建一个渠道 通道
        Channel channel = connection.createChannel();
        //删除消息队列
        channel.queueDelete(QUEUENAME);
        //订阅消息队列的消息  设置读取的最大消息数
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

        /**
         *  生成一个队列
         * 1. 队列名称
         *  2. 队列里面的消息是否持久化 默认消息存储在内存中（false表示不持久化，即保存在内存中）；true表示持久化到硬盘中
         *  3. 该队列是否只供一个消费者进行消费 是否进行共享  true  可以多个消费者消费
         *  4. 是否自动删除 最后一个消费者端开连接以后 该队列是否自动删除  true  自动删除
         * 5. 其他参数
         */
        channel.queueDeclare(QUEUENAME,durable,false,false,map);
        for (int i = 0; i < 10; i++) {
            String msg = "我是消息_"+i;
            /**
             * 第一个参数是交换机明朝
             * 第二个参数是队列名称
             * 第三个参数 AMQP.BasicProperties参数是代表存放消息队列的类型
             *  MessageProperties.PERSISTENT_PLAIN  实现消息持久化
             *  MessageProperties.TEXT_PLAIN    实现非消息持久化
             * 第四个参数是 消息体
             */
            channel.basicPublish("",QUEUENAME,null,msg.getBytes());
        }
        connection.close();
        System.out.println("完成");
    }
}
