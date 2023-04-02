package org.example;


import java.io.*;

public class Game {
    private GameState gameState;

    public void saveGameState() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("gamestate.ser");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(gameState);
        objectOutputStream.close();
        fileOutputStream.close();
    }

    public void restoreGameState() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("gamestate.ser");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        gameState = (GameState) objectInputStream.readObject();
        objectInputStream.close();
        fileInputStream.close();
    }
}
