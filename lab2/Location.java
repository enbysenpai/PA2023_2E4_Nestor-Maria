public class Location
{
    private String name;
    private double X;
    private double Y;
    public Location() {}
    public Location(String name, double X, double Y)
    {
        this.name=name;
        this.X=X;
        this.Y=Y;
    }

    //getters and setters
   public void setName(String name)
   {
       this.name=name;
   }
   public void setX(double X)
   {
       this.X=X;
   }
   public void setY(double Y)
   {
       this.Y=Y;
   }

   public String getName()
   {
       return this.name;
   }
   public double getX()
   {
       return this.X;
   }
   public double getY()
   {
       return this.Y;
   }

   public String toString()
   {
       return name;
   }
}
