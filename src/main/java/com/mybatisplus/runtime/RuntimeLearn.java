package com.mybatisplus.runtime;

import java.io.IOException;

/**
 * @ClassName com.mybatisplus.runtime
 * @Author: leiming
 * @CreateTime: 2024/3/11
 * @Description:
 */
public class RuntimeLearn {

    public static void main(String[] args) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        //获取最大内存
        runtime.maxMemory();
        //获取空闲内存
        runtime.freeMemory();
        //获取总内存
        runtime.totalMemory();
        //获取空闲的进程数
        runtime.availableProcessors();
        //运行指定的命令 打开浏览器
        runtime.exec("C:\\Users\\admin\\AppData\\Local\\Google\\Chrome\\Application\\chrome.exe");
    }
}
