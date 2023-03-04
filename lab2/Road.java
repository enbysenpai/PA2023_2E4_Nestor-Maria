public class Road
{
    private String name;
    private RoadType type;

    public Road(String name, RoadType type, Location a, Location b)
    {
        this.name=name;
        this.type=type;


    }

    //getters and setters
    public void setName(String name)
    {
        this.name=name;
    }
    public void setType(RoadType type)
    {
        this.type=type;
    }
    public String getName()
    {
        return this.name;
    }
    public RoadType getType()
    {
        return this.type;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(obj==null || !(obj instanceof RoadType))
        {
            return false;
        }
        Road other = (Road)obj;
        return name.equals(other.name);
    }

    public String toString()
    {
        return name;
    }

}


