package org.example;



import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Problem {
    public List<Student> students;
    public List<Project> projects;

    public Problem(List<Student> students, List<Project> projects) {
        this.students = students;
        this.projects = projects;
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void preferences() {
        double avgOfPreferences = students.stream()
                .mapToInt(student -> student.getPreferredProjects().size())
                .average()
                .orElse(0.0); //this is the case when our list is empty

        List<Student> theWantedStudents = students.stream()
                .filter(student -> student.getPreferredProjects().size() < avgOfPreferences)
                .collect(Collectors.toList());

        System.out.println(theWantedStudents);
    }

    public void assignProjects() {
        List<Pair<Student, Project>> assignments = new ArrayList<>();

        //We now iterate through each project and assign a student to it
        for (Student student : students) {
            if (student.getAssignedProject() == null)
                for (Project project : projects) {

                    if (project.getAssignedStudent() == null) {
                        project.setAssignedStudent(student);
                        student.setAssignedProject(project);
                        assignments.add(new Pair<>(student, project));
                        break;
                    }
                }

        }

        for (Pair<Student, Project> assignment : assignments) {
            System.out.println(assignment.getL() + "->" + assignment.getR());
        }
    }


    @Override
    public String toString() {
        return getStudents().toString() + getProjects().toString();
    }
}