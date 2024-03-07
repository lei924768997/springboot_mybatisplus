package com.mybatisplus.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName com.mybatisplus.entity
 * @Author: leiming
 * @CreateTime: 2023/7/22
 * @Description:
 */
public class ThreadTest {


    public static void main(String[] args) {

        Tickten t = new Tickten();
        Thread thread = new Thread(t);
        thread.setName("窗口一");
        thread.start();

        Thread thread2 = new Thread(t);
        thread2.setName("窗口四");
        thread2.start();
    }
}
@Data
 class Tickten implements  Runnable {

    private int num = 100;


    /**
     * @author leim
     * @date 2023/7/22
     * @description 方式一：使用synchronized关键字加锁
     */

   private Object o = new Object();
   @Override
    public void run() {
        while (true) {
            synchronized(o){
                if (num > 0) {
                    System.out.println(Thread.currentThread().getName()+ "售出" + num);
                    num--;
                }
            }
        }
    }

    /**
     * @author leim
     * @date 2023/7/22
     * @description  方式二：使用ReentrantLock类的lock.lock()和lock.unlock(); 加锁和解锁对代码实现同步
     */
   /* ReentrantLock lock = new ReentrantLock();
    public void run() {
        while (true) {
            lock.lock();
            if (num > 0) {
                System.out.println(Thread.currentThread().getName()+ "售出" + num);
                num--;
            }
            lock.unlock();
        }
    }*/

}