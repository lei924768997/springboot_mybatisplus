package com.mybatisplus.annotation;

/**
 * @ClassName com.mybatisplus.annotation
 * @Author: leiming
 * @CreateTime: 2024/2/23
 * @Description:
 */
//@MyAnnotation(aaa = 11)  //对于注解中的属性的使用方法  int aaa();
//@MyAnnotation(ccc = {"11","22"})  //对于注解中的属性的使用方法  String[] ccc();
@MyAnnotation(aaa=11,bbb = "abc",ccc = {"11","22"}) //int aaa(); String bbb(); String[] ccc();
public class MyAnnotationTest {
}
