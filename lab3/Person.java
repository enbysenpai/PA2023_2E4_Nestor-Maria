import java.util.HashMap;
import java.util.Map;

public class Person implements Node, Comparable<Person>
{
    private String name;
    private int id;
    private Map<Node, String> relationships=new HashMap<>();

    public Person(){}
    public Person(int id,String name)
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

    public void addRelationship(Node node,String value)
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
        return name+" has id "+id;
    }
}