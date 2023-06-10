package com.mybatisplus.exception;

/**
 * @ClassName com.mybatisplus.exception
 * @Author: leiming
 * @CreateTime: 2023/5/27 16:24
 * @Description:   自定义异常类  运行时异常
 */
public class MyException extends  RuntimeException{

    public MyException() {
    }

    public MyException(String message) {
        super(message);
    }
}