package com.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

/**
 * @ClassName com.mybatisplus.entity
 * @Author: leiming
 * @CreateTime: 2023/4/30 11:58
 * @Description:
 */

@Data
@ToString
@TableName("tab_student")
public class Student {

    private Long id;

    private String name;

    private String sex;

    private String book;

    private String school;

}