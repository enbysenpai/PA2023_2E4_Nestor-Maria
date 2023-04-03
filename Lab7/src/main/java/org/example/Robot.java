package org.example;

import java.util.List;
import java.util.Random;

public class Robot implements Runnable
{
    public String name;
    private boolean running;
    private Exploration explore;

    private int mapSize;
    private int row,col;
    private final ExplorationMap map;
    private final SharedMemory mem;


    public Robot(String name,int mapSize)
    {
        this.name = name;
        running=false;

        this.mapSize=mapSize;
        row=(int)(Math.random()*mapSize);
        col=(int)(Math.random()*mapSize);

        map=new ExplorationMap(mapSize);
        mem=new SharedMemory(mapSize*mapSize);
    }

    public String getName() {
        return name;
    }

    public void start()
    {
        running=true;
    }

    public void stop()
    {
        running=false;
    }

    public void printName()
    {
        System.out.println(this.name);
    }

    public void run() {
        while (true)
        {
            //pick a new cell to explore
            Random random=new Random();
            int newRow= random.nextInt(map.getSize());
            int newCol= random.nextInt(map.getSize());

            if(map.visit(newRow, newCol, this))
            {
                this.row=newRow;
                this.col=newCol;
                List<Token> tokens=mem.extractTokens(mapSize);
                map.setTokens(row,col,tokens);
            }

            //make the robot sleep for some time
            try
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }

        }
    }

    @Override
    public String toString()
    {
        return getName();
    }

}
