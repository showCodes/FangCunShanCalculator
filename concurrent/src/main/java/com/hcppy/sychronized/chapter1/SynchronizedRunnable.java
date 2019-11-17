package com.hcppy.sychronized.chapter1;

import java.util.concurrent.TimeUnit;

public class SynchronizedRunnable implements Runnable {

    private int index = 1;

    private final static int MAX = 500;

    public void run() {
        while (!ticket()) {
            try {
                TimeUnit.MILLISECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private synchronized boolean ticket() {
        if (index > MAX) {
            return true;
        }
        System.out.println(Thread.currentThread().getName() + "的号码是：" + (index++));
        return false;
    }
}
