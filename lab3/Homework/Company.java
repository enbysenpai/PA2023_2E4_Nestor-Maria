package Homework;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Company implements Node, Comparable<Company>
{
    private String name;
    private int id;
    private String city;
    private List<Node> neighbors;
    public Company(){}
    public Company(int id,String name,String city)
    {
        this.id=id;
        this.name=name;
        this.city=city;
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
    public String getCity()
    {
        return city;
    }
    public void setName(String name)
    {
        this.name=name;
    }
    public void setId(int id)
    {
        this.id=id;
    }
    public void setCity(String city)
    {
        this.city=city;
    }

    @Override
    public int compareTo(Company other)
    {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString()
    {
        return name;
    }

    public List<Node> getNeighbors()
    {
        return this.neighbors;
    }
    public int getNodeImportance()
    {
        return this.neighbors.size();
    }
}
