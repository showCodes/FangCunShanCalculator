package com.hcppy.sychronized.chapter1;

import java.util.stream.IntStream;

public class BankVersion1 {

    public static void main(String[] args) {

        SynchronizedRunnable synchronizedRunnable = new SynchronizedRunnable();

        IntStream.of(1, 2, 3).mapToObj(i -> new Thread(synchronizedRunnable, i + " 号窗口")).forEach(Thread::start);
    }
}
