package com.mybatisplus.entity;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/*和数据库的字段进行映射
@TableField("incident_id")

select = false 代表查询数据库时，默认这个字段不做查询
@TableField(value="offence_code",select = false)

表示表中没有这个字段，设置成不存在
@TableField(exist = false)

设置表的id  AUTO代表数据库自己生成   INPUT代表自己输入   ASSIGN_ID代表自动生成的ID（雪花算法）  ASSIGN_UUID代表生成UUID
@TableId(type = IdType.ASSIGN_ID)
*/


/**
 * @ClassName
 * @Author: leiming
 * @Description:
 * @Date: 2023/4/9 14:07
 * @Version 1.0.0
 */
@Data
@TableName("test1_data")
@ToString
public class CrimeInfo {

    private Long id;

    //和数据库的字段进行映射
    @TableField("incident_id")
    private String incidentId;

    private String offenceCode;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private String dispatchTime;

    private String victims;

    private String crimeName1;

    private String crimeName2;

    private String crimeName3;

    private String city;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private String startDateTime;

    //逻辑删除字段，0代表正常  1代表被删除
    @TableLogic(value = "0",delval = "1")
    private Integer deleted;

    //乐观锁，从1开始   需要添加拦截器
    @Version
    private Integer version;

    private List<User> users;

    private Student student;
}
