package com.mybatisplus.annotation;

/**
 * @ClassName com.mybatisplus.annotation
 * @Author: leiming
 * @CreateTime: 2024/2/23 9:47
 * @Description: 注解的使用   注解的本质就是接口   注解中定义的属性就是抽象方法
 *           抽象方法的返回值限制是：基本数据类型、string、枚举、注解和这些类型的数组形式
 *
 */
public @interface MyAnnotation {
    int aaa() default  1;

    String bbb();

    String[] ccc();
}
