package com.mybatisplus.rabbitMQ.sample.controller;
import com.baomidou.mybatisplus.extension.api.R;
import com.mybatisplus.pojo.SysUser;
import com.mybatisplus.service.SysUserService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName com.mybatisplus.controller
 * @Author: leiming
 * @CreateTime: 2024/3/6
 * @Description:
 */
@RestController
@RequestMapping("/system/user")
@AllArgsConstructor
public class SampleMQController {

    private final SysUserService sysUserService;
    private  final AmqpTemplate amqpTemplate;

    @GetMapping("/sendMsg")
    public R sendMsg(){
        SysUser sysuser = sysUserService.getById("32041146735807370122");
        //向队列中发送消息
        amqpTemplate.convertAndSend("MQ_01",sysuser);

        return R.ok("发送成功");
    }

}
