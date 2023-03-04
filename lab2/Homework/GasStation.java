package Homework;

/**
 * A type of a location
 */
public class GasStation extends Location
{
    private double gasPrice;

    /**
     * The only constructor; it takes four parameters:
     * @param name The name of the gas station
     * @param X The x coordinate of the gas station
     * @param Y The y coordinate of the gas station
     * @param gasPrice The gas price of the gas station
     */
    public GasStation(String name,double X,double Y,int gasPrice)
    {
        super(name,X,Y);
        this.gasPrice=gasPrice;
    }

    /**
     * Sets the gas price of the gas station
     * @param gasPrice The gas price of the gas station
     */
    void setGasPrice(double gasPrice)
    {
        this.gasPrice = gasPrice;
    }

    /**
     * Returns the gas price of the gas station
     * @return The gas price of the gas station
     */
    public double getGasPrice()
    {
        return gasPrice;
    }
    /**
     * Returns information about the gas station in a String format
     * @return A string containing information about the gas station
     */
    @Override
    public String toString()
    {
        return "Gas station "+name+" with coordinates "+X+" "+Y+" has the gas price "+gasPrice;
    }
}
