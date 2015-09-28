package com.shzhangji.javasandbox.concurrency;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousCloseException;
import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class NIOInterruption {

    public static class NIOBlocked implements Runnable {

        private final SocketChannel sc;

        public NIOBlocked(SocketChannel sc) {
            this.sc = sc;
        }

        @Override
        public void run() {

            System.out.println("Waiting for read() in " + this);

            try {
                sc.read(ByteBuffer.allocate(1));
            } catch (ClosedByInterruptException e) {
                System.out.println("ClosedByInterruptException");
            } catch (AsynchronousCloseException e) {
                System.out.println("AsynchronousCloseException");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            System.out.println("Exiting NIOBlocked.run() " + this);
        }

    }

    public static void main(String[] args) throws Exception {

        ExecutorService executor = Executors.newCachedThreadPool();
        ServerSocket server = new ServerSocket(8913);

        InetSocketAddress isa = new InetSocketAddress("localhost", 8913);
        SocketChannel sc1 = SocketChannel.open(isa);
        SocketChannel sc2 = SocketChannel.open(isa);

        Future<?> f = executor.submit(new NIOBlocked(sc1));
        executor.execute(new NIOBlocked(sc2));;
        executor.shutdown();

        TimeUnit.SECONDS.sleep(1);
        f.cancel(true);
        TimeUnit.SECONDS.sleep(1);
        sc2.close();

        server.close();
    }

}
