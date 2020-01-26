package com.shzhangji.javasandbox.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class DaemonThread {

    public static class DaemonThreadFactory implements ThreadFactory {

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        }

    }

    public static void main(String[] args) {

        ExecutorService executor = Executors.newCachedThreadPool(new DaemonThreadFactory());
        executor.execute(() -> {
            System.out.println("Thread is started.");
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (Exception e) {}
        });

        System.out.println("Main thread is sleeping.");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {}
        System.out.println("Main thread is finished.");

    }

}
