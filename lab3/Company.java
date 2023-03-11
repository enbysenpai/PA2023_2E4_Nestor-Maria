public class Company implements Node, Comparable<Company>
{
    private String name;
    private int id;
    public Company(){}
    public Company(int id,String name)
    {
        this.id=id;
        this.name=name;
    }
    public String getName()
    {
        return name;
    }
    @Override
    public int getId()
    {
        return id;
    }
    public void setName(String name)
    {
        this.name=name;
    }
    public void setId(int id)
    {
        this.id=id;
    }

    @Override
    public int compareTo(Company other)
    {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString()
    {
        return name+" has id "+id;
    }
}
