package com.mybatisplus.rabbitMQ.sample.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName com.mybatisplus.rabbitMQ.config
 * @Author: leiming
 * @CreateTime: 2024/3/7
 * @Description:
 */
@Configuration
public  class RabbitMQConfig {

    @Bean
    public Queue getQueue(){

        return new Queue("MQ_01");
    }

}
