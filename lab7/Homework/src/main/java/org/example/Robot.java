package org.example;

import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Robot implements Runnable {
    public String name;
    private ExplorationMap map;
    private int row, col;
    private SharedMemory mem;
    private boolean running, paused;
    private int mapSize;
    private Lock lock;

    public Robot(String name, ExplorationMap map) {
        this.name = name;
        running = true;
        paused = false;

        this.map = map;
        this.mapSize = map.getSize();

        row = (int) (Math.random() * mapSize);
        col = (int) (Math.random() * mapSize);


        mem = new SharedMemory(mapSize * mapSize * mapSize);

        lock = new ReentrantLock();
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

        while (running) {
            while (paused) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //lock.lock();
            //try {
//                Random random = new Random();
//                int newRow = random.nextInt(map.getSize());
//                int newCol = random.nextInt(map.getSize());

            if (map.getPositionLength() == 0)
                break;

            int newRow = map.getPosition() / map.getSize();
            int newCol = map.getPosition() % map.getSize();

            if (map.visit(newRow, newCol, this)) {
                this.row = newRow;
                this.col = newCol;
                List<Token> tokens = mem.extractTokens(map.getSize());
                map.setTokens(row, col, tokens);
                map.delPosition();
            }
//            }
//            finally
//            {
//                System.out.println("Unlock the lock (lmao again) for "+getName());
//                lock.unlock();
//            }
//            lock.lock();
//            try {
//                int newRow = row;
//                int newCol = col;
//                int attempts = 0;
//
//                while (attempts < 4) {
//                    //SOUTH
//                    if (newRow + 1 < map.getSize())
//                        if (map.visit(newRow + 1, newCol, this)) {
//                            this.row = newRow + 1;
//                            this.col = newCol;
//                            List<Token> tokens = mem.extractTokens(map.getSize());
//                            map.setTokens(row, col, tokens);
//                            break;
//                        }
//
//                    //WEST
//                    if (newCol - 1 >= 0)
//                        if (map.visit(newRow, newCol - 1, this)) {
//                            this.row = newRow;
//                            this.col = newCol - 1;
//                            List<Token> tokens = mem.extractTokens(map.getSize());
//                            map.setTokens(row, col, tokens);
//                            break;
//                        }
//
//                    //NORTH
//                    if (newRow - 1 >= 0)
//                        if (map.visit(newRow - 1, newCol, this)) {
//                            this.row = newRow - 1;
//                            this.col = newCol;
//                            List<Token> tokens = mem.extractTokens(map.getSize());
//                            map.setTokens(row, col, tokens);
//                            break;
//                        }
//
//                    //EAST
//                    if (newCol + 1 < map.getSize())
//                        if (map.visit(newRow, newCol + 1, this)) {
//                            this.row = newRow;
//                            this.col = newCol + 1;
//                            List<Token> tokens = mem.extractTokens(map.getSize());
//                            map.setTokens(row, col, tokens);
//                            break;
//                        }
//
//                    attempts++;
//
//                    //we tried all directions
//                    if (attempts == 4) {
//                        if (row > 0 && map.checkMatrixCell(row - 1, col)) {
//                            row--;
//                        } else if (col > 0 && map.checkMatrixCell(row, col - 1)) {
//                            col--;
//                        } else if (row < map.getSize() - 1 && map.checkMatrixCell(row + 1, col)) {
//                            row++;
//                        } else if (col < map.getSize() - 1 && map.checkMatrixCell(row, col + 1)) {
//                            col++;
//                        }
//                        return;
//                    }
//                }
//            } finally {
//                System.out.println("unlocking the lock (lmao) for " + getName());
//                lock.unlock();
//            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.println("One thread might have been stopped");
    }

    @Override
    public String toString() {
        return getName() + " la locatia [" + row + " ," + col + "]";
    }
}
