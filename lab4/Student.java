import java.util.stream.IntStream;

public class Student implements Comparable<Student>
{
    String name;

    public Student(){}
    public Student(String name)
    {
        this.name=name;
    }

    private void setName(String name)
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
    public int compareTo(Student other)
    {
        return this.name.compareTo(other.name);
    }
}
