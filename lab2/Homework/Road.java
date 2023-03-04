package Homework;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

/**
 * This class is used for creating roads
 */

public class Road
{
    private String name;
    private RoadType type;
    private double length;
    private int speedLimit;

    private Location locationStart;
    private Location locationEnd;

    /**
     * The default constructor
     */
    public Road(){}

    /**
     * This constructor takes five parameters:
     * @param name The name of the roads
     * @param type The type of the road (it takes a value from RoadType.java
     * @param a It is one location from which the street starts/ends
     * @param b It is the other location from which the street starts/ends
     * @param speedLimit It is the speed limit of the street
     */
    public Road(String name,RoadType type,Location a,Location b,int speedLimit)
    {
        this.name=name;
        this.type=type;
        //using the Euclidean distance:
        this.length=sqrt(abs(abs(a.getX()-b.getX())*abs(a.getX()-b.getX())-abs(a.getY()-b.getY())*abs(a.getY()-b.getY())));
        this.speedLimit=speedLimit;
        this.locationStart=a;
        this.locationEnd=b;
    }

    //getters and setters

    /**
     * Sets the name of the street
     * @param name The name of the street
     */
    void setName(String name)
    {
        this.name=name;
    }
    /**
     * Sets the type of the street
     * @param type The type of the street; it needs to be of type RoadType
     */
    void setType(RoadType type)
    {
        this.type=type;
    }

    /**
     * Sets the speed limit of the street
     * @param speedLimit The speed limit of the street
     */
    void setSpeedLimit(int speedLimit)
    {
        this.speedLimit=speedLimit;
    }

    /**
     * Sets the start/end location of the street
     * @param a The start/end location of the street
     */
    void setLocationStart(Location a)
    {
        this.locationStart=a;
    }
    /**
     * Sets the start/end location of the street
     * @param b The start/end location of the street
     */
    void setLocationEnd(Location b)
    {
        this.locationEnd=b;
    }

    /**
     * Returns the name of the street
     * @return The name of the street
     */
    public String getName()
    {
        return this.name;
    }
    /**
     * Returns the type of the street
     * @return The type of the street
     */
    public RoadType getType()
    {
        return this.type;
    }
    /**
     * Returns the speed limit of the street
     * @return The name of the street
     */
    public int getSpeedLimit() {
        return speedLimit;
    }
    /**
     * Returns the start of the street
     * @return The start location of the street
     */
    public Location getLocationStart()
    {
        return locationStart;
    }
    /**
     * Returns the end location of the street
     * @return The end location of the street
     */
    public Location getLocationEnd()
    {
        return locationEnd;
    }

    /**
     * Verifies if the object given as parameter is a street and has the same name as the current street
     * @param obj The object to be compared
     * @return True if it does, False if it doesn't
     */
    @Override
    public boolean equals(Object obj)
    {
        if(obj==null || !(obj instanceof Road))
        {
            return false;
        }
        Road other=(Road)obj;
        return name.equals(other.name); //true -> same name; false -> otherwise
    }

    /**
     * Returns information about the street in a String format
     * @return A string containing information about the street
     */
    @Override
    public String toString()
    {
        return "("+type+")"+name+" has length "+length+" and the speed limit is "+speedLimit;
    }

}