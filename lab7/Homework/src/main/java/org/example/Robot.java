package org.example;

import java.util.*;

public class Robot implements Runnable {
    public String name;
    private final ExplorationMap map;
    private int row, col;
    private final SharedMemory mem;
    private boolean running, paused;
    private final int mapSize;
    private final List<Integer> visitedCells;
    private int noOfCells;

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

        map.visit(row,col,this);
        noOfCells=1;
    }

    public void start() {
        running = true;
        paused = false;
    }

    public void pause(int time) {
        paused = true;

        if (time > 0) {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    paused = false;
                    System.out.println("Thread resumed after " + time + " seconds");
                }
            }, time * 1000L);
        }
    }

    public void stop() {
        running = false;
        paused = true;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run()
    {
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
                        noOfCells++;
                    }
                    //EAST
                    else if (col<map.getSize()-1 && map.visit(row, col + 1, this)) {
                        this.col = col + 1;
                        List<Token> tokens = mem.extractTokens(map.getSize());
                        map.setTokens(row, col, tokens);
                        visitedCells.add(row * mapSize + col);
                        done=true;
                        noOfCells++;
                    }
                    //SOUTH
                    else if (row<map.getSize()-1 && map.visit(row + 1, col, this)) {
                        this.row = row + 1;
                        List<Token> tokens = mem.extractTokens(map.getSize());
                        map.setTokens(row, col, tokens);
                        visitedCells.add(row * mapSize + col);
                        done=true;
                        noOfCells++;
                    }
                    //WEST
                    else if (col>0 && map.visit(row, col - 1, this)) {
                        this.col = col - 1;
                        List<Token> tokens = mem.extractTokens(map.getSize());
                        map.setTokens(row, col, tokens);
                        visitedCells.add(row * mapSize + col);
                        done=true;
                        noOfCells++;
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
        noOfCells=noOfCells*mapSize;
        System.out.println(getName()+" HAS PLACED "+noOfCells+" TOKENS");
    }

    @Override
    public String toString() {
        return getName() + " located at [" + row + " ," + col + "]";
    }
}
