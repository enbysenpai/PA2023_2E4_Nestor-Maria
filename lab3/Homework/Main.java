package Homework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main
{
    public static void main(String[] args) {

        List<Person> persons = new ArrayList<>();

        Programmer programmer1 = new Programmer();
        programmer1.setName("Mark");
        programmer1.setId(1);
        programmer1.setDayOfBirth(1);
        programmer1.setMonthOfBirth(2);
        programmer1.setYearOfBirth(1987);
        programmer1.setProgrammingLanguage("C++");
        programmer1.setYearsOfExperience(13);

        Programmer programmer2 = new Programmer();
        programmer2.setName("Jane");
        programmer2.setId(2);
        programmer2.setDayOfBirth(2);
        programmer2.setMonthOfBirth(2);
        programmer2.setYearOfBirth(2000);
        programmer2.setProgrammingLanguage("Java");
        programmer2.setYearsOfExperience(2);

        Designer designer1 = new Designer();
        designer1.setName("Alina");
        designer1.setId(3);
        designer1.setDayOfBirth(2);
        designer1.setMonthOfBirth(7);
        designer1.setYearOfBirth(2002);
        designer1.setDesignSoftware("Adobe Photoshop");
        designer1.setDesignStyle("Minimalist");

        Designer designer2 = new Designer();
        designer2.setName("Daniel");
        designer2.setId(4);
        designer2.setDayOfBirth(6);
        designer2.setMonthOfBirth(12);
        designer2.setYearOfBirth(1999);
        designer2.setDesignSoftware("Canva");
        designer2.setDesignStyle("Retro");

        Company company = new Company(14, "Amazing Name for a Company", "Iasi");

        persons.add(programmer1);
        persons.add(programmer2);
        persons.add(designer1);
        persons.add(designer2);

        persons.get(0).addRelationship(persons.get(1), "best-friend");
        persons.get(0).addRelationship(persons.get(2), "friend");
        persons.get(0).addRelationship(persons.get(3), "siblings");
        persons.get(2).addRelationship(persons.get(3), "enemies");

        persons.get(0).addRelationship(company, "boss");

        Collections.sort(persons);

        List<Node> nodes = new ArrayList<>();
        nodes.add(persons.get(0));
        nodes.add(persons.get(1));
        nodes.add(persons.get(2));
        nodes.add(persons.get(3));

        System.out.println("The nodes are: "+nodes);

//        Network network = new Network(nodes);
//        System.out.println(network);


        Network network=new Network();
        network.addNode(persons.get(0));
        network.addNode(persons.get(1));
        network.addNode(persons.get(2));
        network.addNode(persons.get(3));
        System.out.println(network);
    }
}
