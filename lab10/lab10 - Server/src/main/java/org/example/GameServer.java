package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {
    public static final int PORT = 3456;
    public static volatile boolean running=true;

    public GameServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            while (running) {
                System.out.println("Server opened. Waiting for a client...");

                try {
                    Socket socket = serverSocket.accept();
                    new ClientThread(socket).start();
                }
                catch (java.io.InterruptedIOException e)
                {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Server stopped");
    }
}
