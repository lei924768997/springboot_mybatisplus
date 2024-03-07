package com.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * @ClassName com.mybatisplus.entity
 * @Author: leiming
 * @CreateTime: 2023/8/13
 * @Description:  反射的相关学习
 */
public class InvokeTest {

    public static void main(String[] args)throws Exception {
        //通过反射有3种方式获取对象
       /* 1、Class clazz = Class.forName("com.mybatisplus.entity.Student");
        2、Class clazz = Student.class;
        3、Student s= new Student();
        Class clazz = s.getClass();*/

        //Class clazz = Class.forName("com.mybatisplus.entity.Student");
        //通过反射创建对象
        //Student test = (Student)clazz.newInstance();
        //通过反射获取构造器后 在创建对象
        //Constructor constructor = clazz.getConstructor();
        //Student test = (Student)constructor.newInstance();
        //System.out.println(test);
        //获取类全部构造器
        /*Constructor[] constructors = clazz.getConstructors();
        for(Constructor c :constructors){
            System.out.println(c);
        }*/

        //有一个ArrayList<String>,向其中添加int类型的数据
        ArrayList<String> list= new ArrayList<>();
        list.add("11");
        list.add("22");
        Class clazz = list.getClass();
        Method add = clazz.getMethod("add",Object.class);
        add.invoke(list,1);
        System.out.println(list);
    }
}
