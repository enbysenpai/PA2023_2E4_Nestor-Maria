package org.example.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Player extends Thread {
    private final Socket playerSocket;
    private BufferedReader in;
    private PrintWriter out;

    private final Game game;
    private Player opponent = null;
    private int id;


    public Player(Socket socket, int id, Game game) {
        this.playerSocket = socket;
        this.game = game;
        this.id = id;
    }

    public void setPlayerId(int id) {
        this.id = id;
    }

    public int getPlayerId() {
        return id;
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    public Player getOpponent() {
        return opponent;
    }

    public void run() {

        init();
        processCommand();
    }

    private void init() {
        try {
            in = new BufferedReader(new InputStreamReader(playerSocket.getInputStream()));
            out = new PrintWriter(playerSocket.getOutputStream(), true);

            if (id == 1) {
                game.setCurrentPlayer(this);
                String response = "Welcome, my precious player " + id + "! Wait for a second player to connect...";
                out.println(response);
            } else {
                out.println("Welcome, my precious player" + id + "! Wait for your opponent to make a move...");
                opponent = game.getCurrentPlayer();
                opponent.setOpponent(this);
                opponent.out.println("Player 2 connected! ^^");

            }
        } catch (IOException e) {
            System.err.println("IOException: getInputStream (31) || getOutputStream (32)");
        }
    }

    private void processCommand() {
        try {
            while (true) {
                String command = in.readLine();
                if (command.equals("exit")) {
                    game.players--;
                    System.out.println(command);
                    if(opponent!=null)
                    {
                        opponent.setOpponent(null);
                    }
                    break;
                } else if (command.equals("stop")) {
                    playerSocket.close();
                    opponent.playerSocket.close();
                    System.exit(0);
                } else if (command.equals("restart")) {
                    System.out.println(command);
                    game.restart();
                    out.println("Restart...");
                } else if (command.startsWith("move ")) {
                    System.out.println(command);
                    String[] positions=command.substring(5).split("\\s+");
                    processMoveCommand(Integer.parseInt(positions[0]),Integer.parseInt(positions[1]));
                } else {
                    System.out.println(command);
                    out.println("I don't know what you want me to do...");
                }
            }

        } catch (IOException e) {
            System.err.println("readLine (52)");
        }
    }

    private void processMoveCommand(int x,int y)
    {
        game.addPiece(this,x,y);
        out.println("Piece put in position "+x+","+y);

        if(game.checkWin())
        {
            out.println("You won!");
            opponent.out.println("Your opponent put the piece in position "+x+","+y+". You lost...");
        }
        else if(game.checkDraw())
        {
            out.println("Draw!");
            opponent.out.println("Draw!");
        }
        else
        {
            opponent.out.println("Your opponent put the piece in position "+x+","+y);
        }
    }
}
