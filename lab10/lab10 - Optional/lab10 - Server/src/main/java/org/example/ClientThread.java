package org.example;

import org.example.game.Game;
import org.example.game.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class ClientThread extends Thread
{
    private final Socket clientSocket;
    private static volatile List<Game> activeGames=new ArrayList<>();

    public ClientThread(Socket clientSocket)
    {
        this.clientSocket=clientSocket;
    }

    public void run()
    {
        //remove games with no players
        activeGames.removeIf(game -> game.players == 0);

        boolean found=false;

        for(Game game:activeGames) {
            if (game.players==1) {
                found=true;
                game.players++;
                new Player(clientSocket, 2, game).start();
                break; //for exiting the loop
            }
        }
        if(!found)
        {
            Game game=new Game();
            activeGames.add(game);
            new Player(clientSocket, 1, game).start();
        }
    }

}
