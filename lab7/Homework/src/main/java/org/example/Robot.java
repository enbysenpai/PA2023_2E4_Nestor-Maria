package org.example;

import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Robot implements Runnable {
    public String name;
    private ExplorationMap map;
    private int row, col;
    private SharedMemory mem;
    private boolean running, paused;

    public Robot(String name, int mapSize) {
        this.name = name;
        running = true;
        paused = false;

        row = (int) (Math.random() * mapSize);
        col = (int) (Math.random() * mapSize);

        map = new ExplorationMap(mapSize);
        mem = new SharedMemory(mapSize * mapSize * mapSize);
    }

    public void start() {
        running = true;
        paused = false;
        System.out.println("Thread has started: running is " + running + " and paused is " + paused);
    }

    public void pause(int time) {
        paused = true;

        if(time>0)
        {
            Timer timer=new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    paused=false;
                    System.out.println("Thread resumend after "+time+" seconds");
                }
            },time*1000);
        }

        System.out.println("Thread has paused");
    }

    public void stop() {
        running = false;
        paused = true;
        System.out.println("Thread has stopped");
    }

    public String getName() {
        return name;
    }

    public void setName() {
        this.name = name;
    }

    public void run() {
        while (running) {
            while (paused)
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            Random random = new Random();
            int newRow = random.nextInt(map.getSize());
            int newCol = random.nextInt(map.getSize());

            if (map.visit(newRow, newCol, this)) {
                this.row = newRow;
                this.col = newCol;
                List<Token> tokens = mem.extractTokens(map.getSize());
                map.setTokens(row, col, tokens);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.println("One thread might have bees stopped");
    }

    @Override
    public String toString() {
        return getName() + " la locatia [" + row + " ," + col + "]";
    }
}
