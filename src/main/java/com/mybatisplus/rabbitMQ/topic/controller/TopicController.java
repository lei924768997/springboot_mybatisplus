package com.mybatisplus.rabbitMQ.topic.controller;

import lombok.AllArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName com.mybatisplus.rabbitMQ.topic.controller
 * @Author: leiming
 * @CreateTime: 2024/3/7
 * @Description:
 */
@RestController
@RequestMapping("/topic")
@AllArgsConstructor
public class TopicController {

    private final AmqpTemplate amqpTemplate;

    @GetMapping("/sendMsg")
    public String sendMsg(){
        amqpTemplate.convertAndSend("exchange_01","order.xian","西安的不夜城");

        return "发送成功";
    }

    @GetMapping("/sendMsg2")
    public String sendMsg2(){
        amqpTemplate.convertAndSend("exchange_01","order.cehngdou","成都的大熊猫");

        return "发送成功";
    }
}
