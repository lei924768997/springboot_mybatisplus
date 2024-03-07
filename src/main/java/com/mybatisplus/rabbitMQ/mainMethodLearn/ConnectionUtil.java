package com.mybatisplus.rabbitMQ.mainMethodLearn;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName com.mybatisplus.rabbitMQ
 * @Author: leiming
 * @CreateTime: 2024/3/4
 * @Description:
 */
public class ConnectionUtil {

    /**
     * @author leim
     * @date 2024/3/4
     * @description 创建rabbitmq的连接
     */
    public static Connection getConnection() throws IOException, TimeoutException {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        Connection connection = connectionFactory.newConnection();
        return connection;
    }
}
