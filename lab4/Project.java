public class Project implements Comparable<Project>
{
    String name;

    public Project(){}
    public Project(String name)
    {
        this.name=name;
    }
    public void setName(String name)
    {
        this.name=name;
    }

    public String getName()
    {
        return name;
    }

    @Override
    public String toString()
    {
        return name;
    }

    @Override
    public int compareTo(Project other)
    {
        return this.name.compareTo(other.name);
    }
}
