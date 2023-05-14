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

    public static Socket socket;
    public static PrintWriter out;
    public static BufferedReader in;

    public void play()
    {
        try
        {
            socket=new Socket(serverAddress,PORT);
            out=new PrintWriter(socket.getOutputStream(),true);
            in=new BufferedReader(new InputStreamReader(socket.getInputStream()));



            while(true)
            {
                String serverResponse=in.readLine();
                System.out.println(serverResponse);

                serverResponse=in.readLine();
                System.out.println(serverResponse);


                String command=new Scanner(System.in).nextLine();
                out.println(command);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
