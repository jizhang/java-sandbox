package com.shzhangji.javasandbox.patterns.behavioral;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class Observer {

    public static interface IObserver {
        void update();
    }

    public static abstract class Subject {
        private Vector<IObserver> observers = new Vector<>();
        protected void notifyObservers() {
            for (IObserver observer : observers) {
                observer.update();
            }
        }
        public void registerObserver(IObserver observer) {
            observers.add(observer);
        }
    }

    public static interface TimeSource {
        Date getTime();
    }

    public static class Clock extends Subject implements TimeSource {
        private Date time = new Date();
        @Override
        public Date getTime() {
            return time;
        }
        public void setTime(Date time) {
            this.time = time;
            notifyObservers();
        }
    }

    public static class DigitalClock implements IObserver {
        private Clock clock;
        public DigitalClock(Clock clock) {
            this.clock = clock;
        }
        @Override
        public void update() {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println("Time: " + df.format(clock.getTime()));
        }
    }

    public static void main(String[] args) throws Exception {

        Clock clock = new Clock();
        clock.registerObserver(new DigitalClock(clock));
        for (int i = 0; i < 5; ++i) {
            clock.setTime(new Date());
            TimeUnit.SECONDS.sleep(1);
        }

    }

}
