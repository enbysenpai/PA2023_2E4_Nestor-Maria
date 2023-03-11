package Homework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Person implements Node, Comparable<Person>
{
    private String name;
    private int id;
    private int dayOfBirth,monthOfBirth,yearOfBirth;
    private Map<Node, String> relationships=new HashMap<>();

    public Person(){}
    public Person(String name,int id,int dayOfBirth,int monthOfBirth,int yearOfBirth,Map<Node,String> relationship)
    {
        this.id=id;
        this.name=name;
        this.dayOfBirth=dayOfBirth;
        this.monthOfBirth=monthOfBirth;
        this.yearOfBirth=yearOfBirth;
        this.relationships=relationship;
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
    public int getDayOfBirth()
    {
        return dayOfBirth;
    }
    public int getMonthOfBirth()
    {
        return monthOfBirth;
    }
    public int getYearOfBirth()
    {
        return yearOfBirth;
    }
    public Map<Node, String> getRelationships()
    {
        return relationships;
    }

    public void setName(String name)
    {
        this.name=name;
    }
    public void setId(int id)
    {
        this.id=id;
    }

    public void setDayOfBirth(int dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }
    public void setMonthOfBirth(int monthOfBirth) {
        this.monthOfBirth = monthOfBirth;
    }
    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getBirthday()
    {
        return getDayOfBirth()+" "+getMonthOfBirth()+" "+getYearOfBirth();
    }

    public void addRelationship(Node node, String value)
    {
        relationships.put(node,value);
    }

    @Override
    public int compareTo(Person other)
    {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString()
    {
        return name;
    }

    @Override
    public int getNodeImportance()
    {
        return relationships.size();
    }

   @Override
   public List<Node> getNeighbors()
   {
      List<Node> neighbors=new ArrayList<>();
      for(Node node: relationships.keySet())
      {
          neighbors.add(node);
      }
      return neighbors;
   }


}