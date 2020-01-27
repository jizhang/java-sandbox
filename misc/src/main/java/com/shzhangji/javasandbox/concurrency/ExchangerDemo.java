package com.shzhangji.javasandbox.concurrency;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExchangerDemo {

    static int size = 10;
    static int delay = 5;

    static interface Generator<T> {
        T next();
    }

    static class ExchangerProducer<T> implements Runnable {
        private Generator<T> generator;
        private Exchanger<List<T>> exchanger;
        private List<T> holder;
        ExchangerProducer(Exchanger<List<T>> exchanger, Generator<T> generator, List<T> holder) {
            this.exchanger = exchanger;
            this.generator = generator;
            this.holder = holder;
        }
        @Override public void run() {
            try {
                while (!Thread.interrupted()) {
                    for (int i = 0; i < size; ++i) {
                        holder.add(generator.next());
                    }
                    holder = exchanger.exchange(holder);
                }
            } catch (InterruptedException e) {}
        }
    }

    static class ExchangerConsumer<T> implements Runnable {
        private volatile T value;
        private Exchanger<List<T>> exchanger;
        private List<T> holder;
        ExchangerConsumer(Exchanger<List<T>> exchanger, List<T> holder) {
            this.exchanger = exchanger;
            this.holder = holder;
        }
        @Override public void run() {
            try {
                while (!Thread.interrupted()) {
                    holder = exchanger.exchange(holder);
                    for (T x : holder) {
                        value = x;
                        holder.remove(x);
                    }
                }
            } catch (InterruptedException e) {}
            System.out.println("Final value: " + value);
        }
    }

    public static void main(String[] args) throws Exception {

        ExecutorService executor = Executors.newCachedThreadPool();
        Exchanger<List<Long>> exchanger = new Exchanger<>();
        List<Long> producerList = new CopyOnWriteArrayList<>();
        List<Long> consumerList = new CopyOnWriteArrayList<>();

        Generator<Long> generator = new Generator<Long>() {
            long i = 0;
            @Override public Long next() {
                return ++i;
            }
        };

        executor.execute(new ExchangerProducer<Long>(exchanger, generator, producerList));
        executor.execute(new ExchangerConsumer<Long>(exchanger, consumerList));

        TimeUnit.SECONDS.sleep(delay);
        executor.shutdownNow();

    }

}
