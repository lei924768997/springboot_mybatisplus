package com.mybatisplus.annotation;

import java.lang.annotation.*;

/**
 * @ClassName com.mybatisplus.annotation
 * @Author: leiming
 * @CreateTime: 2024/2/23
 * @Description:    元注解 ：用来标注注解的注解
 *               元注解有哪些？  @Target: 约束被该注解所标注的注解的书写范围
 *                               其中比较重要的属性包含：ElementType.TYPE    代表该注解只能放在类名的上面
 *                                                   ElementType.Field  代表该注解只能放在属性名的上面
 *                                                   ElementType.Method  代表该注解只能挡在方法的上面
 *
 *                             @Retention：设置被该注解所标注的注解的的生命时长
 *                                其中包含value属性（返回值是枚举） 枚举RetentionPolicy 包括SOURCE、CLASS、RUNTIME
 *                                RetentionPolicy.SOURCE 存活再源文件中，编译后就消失了
 *                                RetentionPolicy.CLASS  存活在源文件和编译后的字节码文件期间，运行后消失了
 *                                RetentionPolicy.RUNTIME   存在在源文件、编译后、运行时都存在 ，不会消失
 *
 *                             @Documented ： 被该注解标注的注解能够存在于帮助文档中
 *                             @Inherited ：被该注解标注的注解能够被子类继承
 */

@Target({ElementType.METHOD,ElementType.TYPE,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)//其中还包括RetentionPolicy.SOURCE、RetentionPolicy.CLASS
@Documented
@Inherited
public @interface ElementAnnotation {
}
