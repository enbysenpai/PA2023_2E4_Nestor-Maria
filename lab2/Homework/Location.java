package Homework;

/**
 * This class creates locations
 */
public class Location
{
    protected String name;
    protected double X;
    protected double Y;

    /**
     * The default constructor
     */
    public Location(){}

    /**
     * This constructor takes three parameters:
     * @param name The name of the location
     * @param X The x coordinate
     * @param Y The y coordinate
     */
    public Location(String name,double X,double Y)
    {
        this.name=name;
        this.X=X;
        this.Y=Y;
    }

    //getters and setters

    /**
     * Sets the name of the location
     * @param name The name of the location
     */
    void setName(String name)
    {
        this.name=name;
    }

    /**
     * Sets the x coordinate of the location
     * @param X The x coordinate of the location
     */
    void setX(double X)
    {
        this.X=X;
    }

    /**
     * Sets the y coordinate of the location
     * @param Y The y coordinate of the location
     */
    void setY(double Y)
    {
        this.Y=Y;
    }

    /**
     * Returns the name of the location
     * @return The name of the location
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Returns the x coordinate of the location
     * @return The x coordinate of the location
     */
    public double getX()
    {
        return X;
    }
    /**
     * Returns the y coordinate of the location
     * @return The y coordinate of the location
     */
    public double getY()
    {
        return Y;
    }
    /**
     * Returns information about the location in a String format
     * @return A string containing information about the location
     */
    @Override
    public String toString()
    {
        return name+" has coordinates "+X+" "+Y;
    }
    /**
     * Verifies if the object given as parameter is a location and has the same name as the current location
     * @param obj The object to be compared
     * @return True if it does, False if it doesn't
     */
    @Override
    public boolean equals(Object obj)
    {
        if(obj==null || !(obj instanceof Location))
        {
            return false;
        }
        Location other=(Location)obj;
        return name.equals(other.name);
    }
}