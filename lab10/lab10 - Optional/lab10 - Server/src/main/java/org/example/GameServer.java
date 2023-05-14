package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {
    public static final int PORT = 3456;

    public GameServer()
    {
        try
        {
            ServerSocket serverSocket=new ServerSocket(PORT);
            System.out.println("Server is running!");

            while(true)
            {
                new ClientThread(serverSocket.accept()).start();
            }
        }
        catch(IOException e)
        {
            System.err.println("IOException: ServerSocket (15) || start (18)");
        }
    }
}
