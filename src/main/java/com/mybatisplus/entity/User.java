package com.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

/**
 * @ClassName com.mybatisplus.entity
 * @Author: leiming
 * @CreateTime: 2023/4/30 11:50
 * @Description:
 */
@Data
@TableName("tab_person")
@ToString
public class User {

    private Long id;

    private String name;

    private String idcard;

    private String sex;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private String createTime;

    //逻辑删除字段，0代表正常  1代表被删除
    @TableLogic(value = "0",delval = "1")
    private Integer deleted;

    //乐观锁，从1开始   需要添加拦截器
    @Version
    private Integer version;

    private Student student;
}