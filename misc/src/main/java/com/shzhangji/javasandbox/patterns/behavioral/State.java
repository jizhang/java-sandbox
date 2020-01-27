package com.shzhangji.javasandbox.patterns.behavioral;

public class State {

    public static interface TurnstileState {
        void coin(Turnstile t);
        void pass(Turnstile t);
    }

    public static class LockedTurnstileState implements TurnstileState {
        @Override
        public void coin(Turnstile t) {
            t.unlock();
        }
        @Override
        public void pass(Turnstile t) {
            t.alarm();
        }
    }

    public static class UnlockedTurnstileState implements TurnstileState {
        @Override
        public void coin(Turnstile t) {
            t.thankyou();
        }
        @Override
        public void pass(Turnstile t) {
            t.lock();
        }
    }

    public static class Turnstile {
        private static TurnstileState lockedState = new LockedTurnstileState();
        private static TurnstileState unlockedState = new UnlockedTurnstileState();
        private TurnstileState state = lockedState;
        public void coin() {
            state.coin(this);
        }
        public void pass() {
            state.pass(this);
        }
        public void lock() {
            state = lockedState;
            System.out.println("lock");
        }
        public void unlock() {
            state = unlockedState;
            System.out.println("unlock");
        }
        public void alarm() {
            System.out.println("alarm");
        }
        public void thankyou() {
            System.out.println("thankyou");
        }
    }

    public static void main(String[] args) {

        Turnstile t = new Turnstile();
        t.coin(); // unlock
        t.pass(); // lock
        t.pass(); // alarm
        t.coin(); // unlock
        t.coin(); // thankyou

    }

}
