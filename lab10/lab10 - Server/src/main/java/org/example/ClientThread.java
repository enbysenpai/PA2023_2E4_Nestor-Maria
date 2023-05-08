package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static org.example.GameServer.running;

public class ClientThread extends Thread
{
    private Socket clientSocket;

    public ClientThread(Socket clientSocket)
    {
        this.clientSocket=clientSocket;
    }

    private BufferedReader in;
    private PrintWriter out;

    public void run()
    {
        try
        {
            in=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out=new PrintWriter(clientSocket.getOutputStream(),true);

            boolean running=true;
            while(running)
            {

                String command=in.readLine();

                if(command.equals("stop"))
                {
                    System.out.println("Server stopped!");
                    GameServer.running=false;
                    running=false;
                    out.println("Server stopped!");
                    clientSocket.close();
                    in.close();
//                    out.close();
                }
                else if(command.equals("exit"))
                {
                    running=false;
                    out.println("Our client is gone :(");
                }
                else
                {
                    System.out.println("Server received the request "+command);
                    out.println(command);
                }
            }

//            clientSocket.close();
//            in.close();
//            out.close();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally {
            try
            {
                clientSocket.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

}
