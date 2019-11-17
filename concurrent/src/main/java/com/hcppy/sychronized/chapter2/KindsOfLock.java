package com.hcppy.sychronized.chapter2;

import java.util.concurrent.TimeUnit;

/**
 * 测试 synchronized 关键字锁不同位置的情况
 */
public class KindsOfLock {

    public synchronized void method1() {
        System.out.println(Thread.currentThread().getName() + " method1 is running!");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void method2() {
        System.out.println(Thread.currentThread().getName() + " method2 is running!");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
