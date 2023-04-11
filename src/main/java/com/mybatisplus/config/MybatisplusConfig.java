package com.mybatisplus.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName
 * @Author: leiming
 * @Description:   mybatisplus的分页拦截器
 * @Date: 2023/4/9 16:25
 * @Version 1.0.0
 */
@Configuration
public class MybatisplusConfig {

    @Bean
    public MybatisPlusInterceptor getInterceptor(){

        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        //mybatisplus的分页拦截器
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        //mybatisplus的乐观锁拦截器
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        return interceptor;

    }
}
