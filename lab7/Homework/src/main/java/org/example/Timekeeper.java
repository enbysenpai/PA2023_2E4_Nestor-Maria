package org.example;

import java.util.concurrent.TimeUnit;

public class Timekeeper implements Runnable
{
    private final long timeLimitMillis;

    public Timekeeper(long timeLimitMillis)
    {
        this.timeLimitMillis= TimeUnit.SECONDS.toMillis(timeLimitMillis);
    }

    @Override
    public void run()
    {
        long startTime=System.currentTimeMillis();
        while(true)
        {
            long elapsedTimeMillis=System.currentTimeMillis()-startTime;
            System.out.println("Running time: "+elapsedTimeMillis/1000+" sec");

            if(elapsedTimeMillis>timeLimitMillis)
            {
                System.out.println("Time exceeded");
                System.exit(0);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
