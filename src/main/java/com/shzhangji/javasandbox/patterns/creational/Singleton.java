package com.shzhangji.javasandbox.patterns.creational;

import java.io.Serializable;

// private constructor, lazy loading, thread-safe, performance, serialization, classloader
public class Singleton {

    public static class Simple {
        private Simple() {}
        private static Simple instance = new Simple();
        public static Simple getInstance() { return instance; }
    }

    public static class Lazy {
        private Lazy() {}
        private static Lazy instance;
        public static Lazy getInstance() {
            if (instance == null) {
                instance = new Lazy();
            }
            return instance;
        }
    }

    public static class Synchronized {
        private Synchronized() {}
        private static Synchronized instance;
        public static synchronized Synchronized getInstance() {
            if (instance == null) {
                instance = new Synchronized();
            }
            return instance;
        }
    }

    // http://www.cs.umd.edu/~pugh/java/memoryModel/DoubleCheckedLocking.html
    public static class DoubleCheckedLocking {
        private DoubleCheckedLocking() {}
        private static volatile DoubleCheckedLocking instance; // volatile
        public static DoubleCheckedLocking getInstance() {
            if (instance == null) {
                synchronized (DoubleCheckedLocking.class) {
                    if (instance == null) {
                        instance = new DoubleCheckedLocking();
                    }
                }
            }
            return instance;
        }
    }

    public static class InnerStaticClass {
        private InnerStaticClass() {}
        private static class SingletonHolder {
            static InnerStaticClass instance = new InnerStaticClass();
        }
        public static InnerStaticClass getInstance() { return SingletonHolder.instance; }
    }

    public static class SerializableSingleton implements Serializable {
        private static final long serialVersionUID = 1L;
        private static SerializableSingleton instance = new SerializableSingleton();
        public static SerializableSingleton getInstance() { return instance; }
        protected Object readResolve() { return getInstance(); }
    }

    public static enum EnumToTheRescue {
        INSTANCE;
        EnumToTheRescue() {}
    }

}
