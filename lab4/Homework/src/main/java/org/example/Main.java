package org.example;


import com.github.javafaker.Faker;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        var students = IntStream.rangeClosed(0, 2)
                .mapToObj(i ->
                        {
                            List<Project> availableProjects = new ArrayList<>();
                            if (i == 0) {
                                availableProjects.addAll(Arrays.asList(new Project("P0")));
                            } else if (i == 1) {
                                availableProjects.addAll(Arrays.asList(new Project("P0"), new Project("P1")));
                            } else {
                                availableProjects.addAll(Arrays.asList(new Project("P0"), new Project("P1"), new Project("P2")));
                            }
                            return new Student("S" + i, availableProjects);
                        }
                )
                .toArray(Student[]::new);

        var projects = IntStream.rangeClosed(0, 2)
                .mapToObj(i -> new Project("P" + i))
                .toArray(Project[]::new);
//
//        Student[] students=new Student[3];
//
//        for(int i=0;i<3;i++)
//        {
//            Faker faker = new Faker();
//
//            String name = faker.name().fullName(); // Miss Samanta Schmidt
//
//            students[i].setName(name);
//            if(i==0)
//                students[i].setPreferredProjects(Arrays.asList(new Project("P0")));
//            else if (i==1)
//                students[i].setPreferredProjects(Arrays.asList(new Project("P0"),new Project("P1")));
//            else
//                students[i].setPreferredProjects(Arrays.asList(new Project("P0"),new Project("P1"),new Project("P2")));
//
//        }
//        System.out.println();
//        System.out.println(students);


        System.out.println("The list of students: ");
        List<Student> listOfStudents = new ArrayList<>();
        listOfStudents.addAll(Arrays.asList(students));
        listOfStudents.stream().sorted().forEach(s -> System.out.println(s.getName()));

        System.out.println();

        System.out.println("The list of projects: ");
        List<Project> listOfProjects = new ArrayList<>();
        listOfProjects.addAll(Arrays.asList(projects));
        listOfProjects.forEach(p -> System.out.println(p.getName()));


        System.out.println();
        Problem problem = new Problem(listOfStudents, listOfProjects);

        System.out.println("The problems with the preference lower than the average: ");
        problem.preferences();

        System.out.println();
        System.out.println("The assignments made: ");
        problem.assignProjects();

        System.out.println();
        System.out.println("What problem have: ");
        System.out.println(problem);





    }
}