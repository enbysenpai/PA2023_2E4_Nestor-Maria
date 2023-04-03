package org.example;

import java.util.ArrayList;
import java.util.List;

public class Exploration {
    private final SharedMemory mem;
    private final ExplorationMap map;
    private List<Robot> robots = new ArrayList<>();

    public Exploration(List<Robot> robots,int mapSize)
    {
        this.mem=new SharedMemory(mapSize*mapSize);
        this.map=new ExplorationMap(mapSize);
        this.robots=robots;
    }

    public void start()
    {
        List<Robot> turns=new ArrayList<>();
        List<Thread> threads=new ArrayList<>();
        robots.forEach(p -> turns.add(new Robot(p.getName(), map.getSize())));
        turns.forEach(t -> threads.add(new Thread(t)));
        threads.forEach(Thread::start);

        threads.forEach(t ->
        {
            try
            {
                t.join();
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        });

    }

    public ExplorationMap getMap() {
        return map;
    }

    public SharedMemory getMem() {
        return mem;
    }

    @Override
    public String toString()
    {
        return map.toString();
    }
}
