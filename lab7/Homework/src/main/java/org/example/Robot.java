package org.example;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Robot implements Runnable {
    public String name;
    private ExplorationMap map;
    private int row, col;
    private SharedMemory mem;
    private boolean running, paused;
    private int mapSize;
    private List<Integer> visitedCells;

    public Robot(String name, ExplorationMap map)
    {
        this.name = name;
        running = true;
        paused = false;

        this.map = map;
        this.mapSize = map.getSize();

        row = (int) (Math.random() * mapSize);
        col = (int) (Math.random() * mapSize);

        mem = new SharedMemory(mapSize * mapSize * mapSize);
        visitedCells=new ArrayList<>();
        visitedCells.add(row*mapSize+col);
    }

    public void start() {
        running = true;
        paused = false;
        System.out.println("Thread has started: running is " + running + " and paused is " + paused);
    }

    public void pause(int time) {
        paused = true;

        if (time > 0) {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    paused = false;
                    System.out.println("Thread resumend after " + time + " seconds");
                }
            }, time * 1000);
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

        while (running)
        {
            while (paused) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            while (!paused)
            {
                boolean done=false;
                while(!done) {
                    //NORTH
                    if (row>0 && map.visit(row - 1, col, this)) {
                        this.row = row - 1;
                        List<Token> tokens = mem.extractTokens(map.getSize());
                        map.setTokens(row, col, tokens);
                        visitedCells.add(row * mapSize + col);
                        done=true;
                    }
                    //EAST
                    else if (col<map.getSize()-1 && map.visit(row, col + 1, this)) {
                        this.col = col + 1;
                        List<Token> tokens = mem.extractTokens(map.getSize());
                        map.setTokens(row, col, tokens);
                        visitedCells.add(row * mapSize + col);
                        done=true;
                    }
                    //SOUTH
                    else if (row<map.getSize()-1 && map.visit(row + 1, col, this)) {
                        this.row = row + 1;
                        List<Token> tokens = mem.extractTokens(map.getSize());
                        map.setTokens(row, col, tokens);
                        visitedCells.add(row * mapSize + col);
                        done=true;
                    }
                    //WEST
                    else if (col>0 && map.visit(row, col - 1, this)) {
                        this.col = col - 1;
                        List<Token> tokens = mem.extractTokens(map.getSize());
                        map.setTokens(row, col, tokens);
                        visitedCells.add(row * mapSize + col);
                        done=true;
                    }
                    //no unvisited neighbor
                    else {
                        if(visitedCells.size()!=0)
                            visitedCells.remove(visitedCells.size() - 1);

                        if (visitedCells.size() == 0) {
                            stop();
                            break;
                        }
                        else {
                            this.row = visitedCells.get(visitedCells.size() - 1) / 5;
                            this.col = visitedCells.get(visitedCells.size() - 1) % 5;
                        }
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("One thread might have been stopped");
    }

    @Override
    public String toString() {
        return getName() + " la locatia [" + row + " ," + col + "]";
    }
}
