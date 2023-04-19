package org.example;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ExplorationMap map = new ExplorationMap(5);

        Scanner scanner = new Scanner(System.in);

        List<Robot> robots = new ArrayList<>();
        Robot robot1 = new Robot("Wall-E", map);
        Robot robot2 = new Robot("R2D2", map);
        Robot robot3 = new Robot("Optimus Prime", map);

        robots.add(robot1);
        robots.add(robot2);
        robots.add(robot3);

        Timekeeper timekeeper=new Timekeeper(60);
        Thread timekeeperThread=new Thread(timekeeper);
        timekeeperThread.setDaemon(true);
        timekeeperThread.start();

        List<Thread> threads = new ArrayList<>();
        robots.forEach(t -> threads.add(new Thread(t)));
        threads.forEach(Thread::start);


        while (true) {
            System.out.println("Enter a command: ");
            String command = scanner.nextLine();
            if (command.equals("stop")) {
                for (int i = 0; i < robots.size(); i++)
                    robots.get(i).stop();

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

                //System.exit(0);
            } else if (command.equals("pause")) {
                for (int i = 0; i < robots.size(); i++)
                    robots.get(i).pause(0);
            } else if (command.equals("start")) {
                for (int i = 0; i < robots.size(); i++)
                    robots.get(i).start();
            } else if (command.startsWith("pause")) {
                //pause a specific thread
                String[] parts=command.split(" ");
                //String threadName = command.split(" ")[1];

                if(parts.length==2) {
                    for (Robot robot : robots) {
                        if (robot.getName().equals(parts[1])) {
                            robot.pause(0);
                            break;
                        }
                    }
                }
                else if(parts.length==3)
                {
                    for (Robot robot : robots) {
                        if (robot.getName().equals(parts[1])) {
                            robot.pause(Integer.parseInt(parts[2]));
                            break;
                        }
                    }
                }

            } else if (command.startsWith("start")) {
                //start a specific thread
                String threadName = command.split(" ")[1];
                for (Robot robot : robots) {
                    if (robot.getName().equals(threadName)) {
                        robot.start();
                        break;
                    }
                }
            } else {
                System.out.println("UNKNOWN COMMAND");
            }
        }




    }
}
