package com.cs202.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerMain {
    public static void main(String[] args) throws Exception {
        int port = 5050;
        ExecutorService pool = Executors.newCachedThreadPool();
        try (ServerSocket ss = new ServerSocket(port)) {
            System.out.println("Server started on " + port);
            while (true) {
                Socket s = ss.accept();
                pool.submit(new ClientHandler(s));
            }
        }
    }
}
