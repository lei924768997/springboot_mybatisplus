package com.mybatisplus.rabbitMQ.topic.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName com.mybatisplus.rabbitMQ.config
 * @Author: leiming
 * @CreateTime: 2024/3/7
 * @Description:
 */
@Configuration
public  class TopicConfig {
    /**
     *创建一个队列
     */
    @Bean
    public Queue queue2(){

        return new Queue("MQ_02");
    }
    /**
     *创建一个队列
     */
    @Bean
    public Queue queue3(){

        return new Queue("MQ_03");
    }
    /**
     *创建一个交换机
     */
    @Bean
    public TopicExchange getExchange(){
        return new TopicExchange("exchange_01");
    }

    /**
     * 队列和交换机绑定，路由键是order.*
     */
    @Bean
    public Binding exchangeAndQueueBinding1(TopicExchange exchange,Queue queue2){

        return BindingBuilder.bind(queue2).to(exchange).with("order.*");
    }

    /**
     * 队列和交换机绑定，路由键是order.xian
     */
    @Bean
    public Binding exchangeAndQueueBinding2(TopicExchange exchange,Queue queue3){

        return BindingBuilder.bind(queue3).to(exchange).with("order.xian");
    }
}
