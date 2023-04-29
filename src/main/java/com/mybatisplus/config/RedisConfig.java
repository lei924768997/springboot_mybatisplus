package com.mybatisplus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @ClassName
 * @Author: leiming
 * @Description:  配置redis的序列化工具
 * @Date: 2023/4/12 16:33
 * @Version 1.0.0
 */

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){

        //创建RedisTemplate对象
        RedisTemplate<String,Object> temp = new RedisTemplate<>();
        //设置连接工厂
        temp.setConnectionFactory(redisConnectionFactory);
        //创建json序列化工具
        GenericJackson2JsonRedisSerializer json = new GenericJackson2JsonRedisSerializer();
        //设置key的序列化
        temp.setKeySerializer(RedisSerializer.string());
        temp.setHashKeySerializer(RedisSerializer.string());
        //设置value的序列化
        temp.setValueSerializer(json);
        temp.setHashValueSerializer(json);
        return temp;
    }
}
