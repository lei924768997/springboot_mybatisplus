package com.mybatisplus.enums;

import com.fasterxml.jackson.core.sym.Name3;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.awt.*;

/**
 * @ClassName com.mybatisplus.enums
 * @Author: leiming
 * @CreateTime: 2023/6/10 13:59
 * @Description:
 */
@Getter
@AllArgsConstructor
@ToString

public enum ApplyStatusEnum {
     NAME_ONE("未审核",1),
     NAME_TWO("已审核",2),
    Name_TREE("已过期",3);


   private String lable;
    private Integer value;

    public static  ApplyStatusEnum getName(Integer value){
        if(value == null){

            return null;
        }
        ApplyStatusEnum[] applyStatusEnum = ApplyStatusEnum.values();
        System.out.println(applyStatusEnum[0]);
        for(ApplyStatusEnum a : applyStatusEnum){
            if(a.value == value){
                return a;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        ApplyStatusEnum name = ApplyStatusEnum.getName(1);
        System.out.println(name.getLable());
    }

}