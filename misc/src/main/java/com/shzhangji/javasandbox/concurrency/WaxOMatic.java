package com.shzhangji.javasandbox.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WaxOMatic {

    public static class Car {
        private boolean waxOn = false;
        public synchronized void waxed() {
            waxOn = true;
            notifyAll();
        }
        public synchronized void buffed() {
            waxOn = false;
            notifyAll();
        }
        public synchronized void waitForWaxing() throws InterruptedException {
            while (waxOn == false) {
                wait();
            }
        }
        public synchronized void waitForBuffing() throws InterruptedException {
            while (waxOn == true) {
                wait();
            }
        }
    }

    public static class WaxOn implements Runnable {
        private Car car;
        public WaxOn(Car car) {
            this.car = car;
        }
        @Override
        public void run() {
            try {
                while (!Thread.interrupted()) {
                    System.out.print("Wax On! ");
                    TimeUnit.MILLISECONDS.sleep(200);
                    car.waxed();
                    car.waitForBuffing();
                }
            } catch (InterruptedException e) {
                System.out.println("Exiting via interrupt.");
            }
            System.out.println("Ending Wax On task.");
        }
    }

    public static class WaxOff implements Runnable {
        private Car car;
        public WaxOff(Car car) {
            this.car = car;
        }
        @Override
        public void run() {
            try {
                while (!Thread.interrupted()) {
                    car.waitForWaxing();
                    System.out.print("Wax Off! ");
                    TimeUnit.MILLISECONDS.sleep(200);
                    car.buffed();
                }
            } catch (InterruptedException e) {
                System.out.println("Exiting via interrupt.");
            }
            System.out.println("Ending Wax Off task.");
        }
    }

    public static void main(String[] args) throws Exception {
        Car car = new Car();
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(new WaxOff(car));
        executor.execute(new WaxOn(car));
        TimeUnit.SECONDS.sleep(5);
        executor.shutdownNow();
    }

}
