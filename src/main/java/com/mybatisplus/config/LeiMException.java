package com.mybatisplus.config;

/**
 * @ClassName com.mybatisplus.config
 * @Author: leiming
 * @CreateTime: 2023/4/30 10:35
 * @Description:   自定义异常信息处理类
 */
public class LeiMException extends  RuntimeException{

    private String errMessage;

    public LeiMException(){

        super();
    }

    public LeiMException(String errMessage) {
        super(errMessage);
        this.errMessage = errMessage;
    }

    public static  void cast(String errMessage){

        throw new LeiMException(errMessage);
    }

    public static  void cast(CommonError commonError){

        throw new LeiMException(commonError.getErrMessage());
    }

}