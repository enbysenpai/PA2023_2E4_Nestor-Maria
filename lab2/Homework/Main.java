package Homework;

/**
 * The main class
 */
public class Main
{
    /**
     * The main() method is the one that will be called when the program is running
     * @param args Arguments, if any
     */
    public static void main(String[] args)
    {
        //Because I want to check other examples, I don't use the default example
        Location[] locations=new Location[3];
        locations[0]=new Location("Iasi",0.0,10.2);
        locations[1]=new Location("Vaslui",10.2,0.7);
        locations[2]=new Location("Bacau",15.9,25.3);

        Road[] roads=new Road[2];
        roads[0]=new Road("Road1",RoadType.HIGHWAY,locations[0],locations[1],120);
        roads[1]=new Road("Road 2",RoadType.EXPRESS,locations[1],locations[2],90);

        Problem pb=new Problem(locations,roads);
        if(pb.isValid())
        {

            if(pb.travel(locations[0],locations[2]))
                System.out.println("There exists a path!");
            else
                System.out.println("No path found!");
        }
        else
            System.out.println("The problem is not valid!");
    }
}

