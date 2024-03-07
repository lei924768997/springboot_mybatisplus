package com.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    private String username;

    private String password;

    private Integer age;


}