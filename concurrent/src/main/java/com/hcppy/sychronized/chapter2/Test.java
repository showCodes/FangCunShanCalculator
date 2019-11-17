package com.hcppy.sychronized.chapter2;

import java.util.concurrent.TimeUnit;

/**
 * @author lizhulin
 */
public class Test {

    public static void main(String[] args) {
        KindsOfLock kindsOfLock = new KindsOfLock();
        new Thread(kindsOfLock::method1).start();
        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(kindsOfLock::method2).start();
    }
}
