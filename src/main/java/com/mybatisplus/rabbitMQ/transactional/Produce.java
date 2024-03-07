package com.mybatisplus.rabbitMQ.transactional;

import com.mybatisplus.rabbitMQ.mainMethodLearn.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName com.mybatisplus.rabbitMQ.transactional
 * @Author: leiming
 * @CreateTime: 2024/3/7
 * @Description: rabbitMQ事务的使用
 */
public class Produce {

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        //声明消息队列
        channel.queueDeclare("tran_01",false,false,false,null);
        try{
            //开启事务
            channel.txSelect();
            for (int i = 0; i < 10; i++) {

                channel.basicPublish("","tran_01", MessageProperties.TEXT_PLAIN,"hello word!".getBytes());
            }
            //关闭事务
            channel.txCommit();
        }catch (Exception e){
            e.printStackTrace();
            //回滚事务
            channel.txRollback();
        }

    }
}
