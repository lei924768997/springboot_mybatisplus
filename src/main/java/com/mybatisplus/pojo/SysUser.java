package com.mybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.ToString;

/**
 * 
 * @TableName SYS_USER
 */
@TableName(value ="SYS_USER")
@Data
@ToString
public class SysUser implements Serializable {
    /**
     * 主键
     */
    private String id;

    /**
     * 用户类型；1-内部用户；2-外部用户
     */
    private String userType;

    /**
     * 所属科室
     */
    private String deptId;

    /**
     * 用户编码
     */
    private String userCode;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 拼音码
     */
    private String pyCode;

    /**
     * 五笔码
     */
    private String wbCode;

    /**
     * 性别；1-男；2-女
     */
    private String gender;

    /**
     * 密码
     */
    private String password;

    /**
     * 执业医师编码
     */
    private String doctorRegNo;

    /**
     * CA签名1
     */
    private String ca1;

    /**
     * CA签名2
     */
    private String ca2;

    /**
     * 证件类型
     */
    private String idType;

    /**
     * 证件号码
     */
    private String idNo;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 上次登录时间
     */
    private LocalDateTime loginTime;

    /**
     * 失败重试次数
     */
    private Integer retryTimes;

    /**
     * 密码修改时间
     */
    private LocalDateTime pwdModifyTime;

    /**
     * 管理员标志
     */
    private String adminFlag;

    /**
     * 创建人员
     */
    private String createUser;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新人员
     */
    private String updateUser;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 删除标志
     */
    private String delFlag;

    /**
     * 机构
     */
    private String orgId;

    /**
     * 用户级别（1-普通用户；5-部门主管；7-公司领导）
     */
    private String userLevel;

    /**
     * 照片
     */
    private String photo;

    /**
     * 签名
     */
    private String signature;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}