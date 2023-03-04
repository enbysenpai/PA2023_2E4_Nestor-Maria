package Homework;

/**
 * A type of location
 */
public class Airport extends Location
{
    private int numberOfTerminals;

    /**
     * The only constructor, takes four parameters:
     * @param name The name of the airport
     * @param X The x coordinate of the airport
     * @param Y The y coordinate of the airport
     * @param numberOfTerminals The number of terminals of the airport
     */
    public Airport(String name,double X,double Y,int numberOfTerminals)
    {
        super(name,X,Y);
        this.numberOfTerminals=numberOfTerminals;
    }

    /**
     * Sets the number of terminals of the airport
     * @param numberOfTerminals The number of terminals of the airport
     */
    void setNumberOfTerminals(int numberOfTerminals)
    {
        this.numberOfTerminals = numberOfTerminals;
    }

    /**
     * Returns the number of terminals of the airport
     * @return The number of terminals of the airport
     */
    public int getNumberOfTerminals()
    {
        return numberOfTerminals;
    }

    /**
     * Returns information about the airport in a String format
     * @return A string containing information about the airport
     */
    @Override
    public String toString()
    {
        return "Airport "+name+" with coordinates "+X+" "+Y+" has "+numberOfTerminals+" terminals";
    }
}