import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main
{
    public static void main(String[] args)
    {
       var students=IntStream.rangeClosed(0,2)
                .mapToObj(i->new Student("S"+i))
                        .toArray(Student[]::new);

       var projects=IntStream.rangeClosed(0,2)
               .mapToObj(i->new Project("P"+i))
               .toArray(Project[]::new);


       List<Student>listOfStudents=new LinkedList<>();
       listOfStudents.addAll(Arrays.asList(students));
       listOfStudents.stream().sorted().forEach(s-> System.out.println(s.getName()));

       System.out.println();

       TreeSet<Project>listOfProjects=new TreeSet<>();
       listOfProjects.addAll(Arrays.asList(projects));
       listOfProjects.forEach(p-> System.out.println(p.getName()));

    }
}