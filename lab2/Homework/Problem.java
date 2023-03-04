package Homework;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * The class representing a problem
 */
public class Problem {
    private Location[] locations;
    private Road[] roads;
    /**
     * A constructor which initializes the problem with the given parameters
     * @param locations The given locations
     * @param roads The given roads
     */
    public Problem(Location[] locations,Road[] roads)
    {
        this.locations=locations.clone();
        this.roads=roads.clone();
    }
    /**
     * The default constructor, it will create a new problem using some examples given here
     */
    public Problem() {
        this.locations = new Location[]
                {
                        new City("L1", 0.0, 0.0, 100),
                        new Airport("L2", 10.0, 20.0, 4),
                        new GasStation("L3", 5.6, 3.6, 8)
                };

        this.roads = new Road[]
                {
                        new Road("R1", RoadType.HIGHWAY, locations[0], locations[1], 120),
                        new Road("R2", RoadType.EXPRESS, locations[0], locations[2], 100),
                        new Road("R3", RoadType.HIGHWAY, locations[1], locations[2], 120)
                };
    }

    /**
     * Creates a string containing all locations and returns it
     * @return The string containing all the locations
     */
    public String getLocations()
    {
        String locationsString="";
        for(int i=0;i<locations.length;i++)
        {
            locationsString+=locations[i]+"\n";
        }
        return locationsString;
    }
    /**
     * Creates a string containing all roads and returns it
     * @return The string containing all the roads
     */
    public String getRoads()
    {
        String roadsString="";
        for(int i=0;i< roads.length;i++)
        {
            roadsString=roadsString+roads[i]+"\n";
        }
        return roadsString;
    }
    /**
     * Creates a string containing all the information about the problem and returns it
     * @return The String containing the information about the problem
     */
    @Override
    public String toString()
    {
        return getLocations()+getRoads();
    }

    /**
     * Verifies if the instances of the problem are correct (if exists and road/location, roads/locations with different names)
     * @return False if there are no locations or roads, or if two roads/locations have the same name; True otherwise
     */
    public boolean isValid() {
        if (roads.length == 0 || locations.length == 0) {
            return false;
        }

        for (int i = 0; i < roads.length - 1; i++)
            for (int j = i + 1; j < roads.length; j++)
                if (roads[i].equals(roads[j]))
                {
                    System.out.println("There are two roads with same name!");
                    return false;
                }



        for (int i = 0; i < locations.length - 1; i++)
            for (int j = i + 1; j < locations.length; j++)
                if (locations[i].equals(locations[j]))
                {
                    System.out.println("There are two locations with same name!");
                    return false;
                }

        return true;
    }

    /**
     * Verifies if there exists a path between two locations
     * @param locationStart The location from where it starts the search
     * @param locationEnd The location where the search should end
     * @return True if there exists such a path; False otherwise
     */
    public boolean travel(Location locationStart,Location locationEnd)
    {
        boolean[] visited=new boolean[locations.length];
        Location[] previousVisited=new Location[locations.length];
        Location[] toVisit=new Location[locations.length];

        toVisit[0]=locationStart;
        int visitIndex=0;

        while(visitIndex>=0)
        {
            Location current=toVisit[visitIndex];
            visitIndex--;

            if(current.equals(locationEnd))
            {
                Location[] path=new Location[locations.length];
                int pathIndex=0;
                path[pathIndex]=locationEnd;
                Location previous=previousVisited[getIndex(locationEnd)];
                while(previous!=null)
                {
                    pathIndex++;
                    path[pathIndex]=previous;
                    if(previous.equals(locationStart)==false) {
                        previous = previousVisited[getIndex(previous)];
                    }
                    else
                    {
                        return true;
                    }
                }
                return false;
            }

            int currentIndex=getIndex(current);
            if(currentIndex != -1) {
                visited[currentIndex] = true;
                for (int j = 0; j < roads.length; j++) {
                    Location next = null;

                    if (roads[j].getLocationEnd().equals(current)) {
                        next = roads[j].getLocationStart();
                    } else if (roads[j].getLocationStart().equals(current)) {
                        next = roads[j].getLocationEnd();
                    }

                    if (next != null) {
                        int nextIndex = getIndex(next);

                        if (nextIndex != -1 && visited[nextIndex] == false) {
                            visitIndex++;
                            toVisit[visitIndex] = next;
                            previousVisited[nextIndex]=current;
                        }
                    }
                }
            }
        }

        return false;
    }

    /**
     * Used in travel method, returns the index of a given object of type Location
     * @param location The object of type location from which we want to know the index
     * @return The index or -1 if the object doesn't exist
     */
    private int getIndex(Location location)
    {
        for(int i=0;i< locations.length;i++)
        {
            if(locations[i].equals(location))
            {
                return i;
            }
        }
        return -1;
    }
}