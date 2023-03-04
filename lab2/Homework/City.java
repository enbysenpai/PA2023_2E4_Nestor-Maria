package Homework;

/**
 * A type of the location
 */
public class City extends Location
{
    private int population;

    /**
     * The only constructor; it takes four parameters:
     * @param name The name of the city
     * @param X The x coordinate of the city
     * @param Y The y coordinate of the city
     * @param population The population of the city
     */
    public City(String name,double X,double Y,int population)
    {
        super(name,X,Y);
        this.population=population;
    }

    /**
     * Sets the population of the city
     * @param population The population of the city
     */
    void setPopulation(int population)
    {
        this.population = population;
    }

    /**
     * Returns the population of the city
     * @return The population of the city
     */
    public int getPopulation()
    {
        return population;
    }
    /**
     * Returns information about the city in a String format
     * @return A string containing information about the city
     */
    @Override
    public String toString()
    {
        return "City "+name+" with coordinates "+X+" "+Y+" has the population "+population;
    }
}