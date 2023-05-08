package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class GameClient
{
    String serverAddress="127.0.0.1";
    int PORT=3456;

    public void play()
    {
        try
        {
            Socket socket=new Socket(serverAddress,PORT);
            PrintWriter out=new PrintWriter(socket.getOutputStream(),true);
            BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));

            boolean running=true;
            while(running)
            {
                Scanner scanner=new Scanner(System.in);
                scanner.useDelimiter("\n");
                System.out.println("Enter a request...");
                String command=scanner.next();
                if(command.equals("stop") || command.equals("exit"))
                {
                    running=false;
                }

                out.println(command);

                String serverResponse=in.readLine();
                System.out.println(serverResponse);

                if(command.equals("stop"))
                {
                    scanner.close();
                }
            }
//            socket.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
