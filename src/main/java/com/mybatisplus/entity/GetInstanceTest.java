package com.mybatisplus.entity;

/**
 * @ClassName com.mybatisplus.entity
 * @Author: leiming
 * @CreateTime: 2023/7/30
 * @Description:   单例模式 ：首先私有化构造器 成员变量和方法都是静态的，才能达到效果 唯一的区别在于，对象的创建是程序开启的还是需要用到时创建
 * 饿汉式 ：立即加载，代表很饿，需要立即创建对象           线程安全，即使多线程也不会出现问题，会占用内存
 * 懒汉式： 延时加载 ，代表需要吃饭的时候 在创建对象       线程不安全，在多线程中可能创建多个对象，不占用内存
 *
 */
/**
 * @author leim
 * @date 2023/7/30
 * @description  饿汉式 ：程序启动时 对象已经创建完成
 *
 */
public class GetInstanceTest {

    private static final GetInstanceTest getInstanceTest =new GetInstanceTest();


    private GetInstanceTest(){

    }

    public static GetInstanceTest getInstance(){

        return getInstanceTest;
    }

}

/**
 * @author leim
 * @date 2023/7/30
 * @description 懒汉式：在需要使用时，才会创建对象
 */
class GetInstanceTest2{

    private static  GetInstanceTest2 getInstanceTest2;

    private GetInstanceTest2(){

    }
    public static  GetInstanceTest2 getInstance (){
        if (getInstanceTest2 == null) {
            getInstanceTest2 = new GetInstanceTest2();
        }
        return getInstanceTest2;
    }


}
