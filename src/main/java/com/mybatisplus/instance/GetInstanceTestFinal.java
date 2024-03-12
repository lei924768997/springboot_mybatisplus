package com.mybatisplus.instance;

/**
 * @ClassName com.mybatisplus.instance
 * @Author: leiming
 * @CreateTime: 2024/3/11
 * @Description: 在多线程 高并发的情况下 使用这种单列模式初始化对象
 *
 */
public class GetInstanceTestFinal {

    //多线程中 使用变量，需要加volatile
    private  static volatile GetInstanceTestFinal getInstanceTest2;

    private GetInstanceTestFinal(){

    }

    /**
     * synchronized关键字：特点是代码先后执行，在多线程下也是单例 不会有问题
     * 但是这种效率低下，getInstance1被调用多次时，该方法也是先后执行，所以效率低
     */
/*    public static  synchronized GetInstanceTestFinal getInstance1 (){
        if (getInstanceTest2 == null) {
            getInstanceTest2 = new GetInstanceTestFinal();
        }
        return getInstanceTest2;
    }*/

    /**
     * 使用双重检查锁 ，高并发时，多个线程调用方法，在new GetInstanceTest2时，使用同步代码块，不会出现对象被创建多次的情况
     * 这种方法的效率比上面的高
     */
    public static  GetInstanceTestFinal getInstance2 (){
        if (getInstanceTest2 == null) {
            synchronized(GetInstanceTest2.class){
                if (getInstanceTest2 == null) {
                    getInstanceTest2 = new GetInstanceTestFinal();
                }
            }
        }
        return getInstanceTest2;
    }
}
