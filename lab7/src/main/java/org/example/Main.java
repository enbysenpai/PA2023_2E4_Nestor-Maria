package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String args[]) {

        int mapSize=10;

        List<Robot> robots=new ArrayList<>();
        Robot robot1= new Robot("Wall-E",mapSize);
        Robot robot2= new Robot("R2D2",mapSize);
        Robot robot3=new Robot("Optimus Prime",mapSize);

        robots.add(robot1);
        robots.add(robot2);
        robots.add(robot3);

        Exploration explore = new Exploration(robots,mapSize);

        Token token=new Token(mapSize);

        explore.start();

    }
}