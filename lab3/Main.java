import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        List<Person> persons=new ArrayList<>();
        persons.add(new Person(1,"Mark"));
        persons.add(new Person(2,"Jane"));
        persons.add(new Person(3,"Alina"));
        persons.add(new Person(4,"DudaMagica"));

        Collections.sort(persons);
        for(int i=0;i<persons.size();i++)
        {
            System.out.println(persons.get(i));
        }

        Company company=new Company(14,"Amazing Name for a Company");

        persons.get(0).addRelationship(persons.get(1),"best-friend");
        persons.get(0).addRelationship(company,"boss");

        List<Node> nodes=new ArrayList<>();
        nodes.add(persons.get(0));
        nodes.add(persons.get(1));
        nodes.add(persons.get(2));
        nodes.add(persons.get(3));
        nodes.add(company);

        System.out.println(nodes);

    }
}
