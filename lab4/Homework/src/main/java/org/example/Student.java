package org.example;


import java.util.List;

public class Student implements Comparable<Student> {
    String name;
    private List<Project> preferredProjects;
    private Project assignedProject;

    public Student() {
    }

    public Student(String name, List<Project> preferredProjects) {
        this.name = name;
        this.preferredProjects = preferredProjects;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPreferredProjects(List<Project> preferredProjects) {
        this.preferredProjects = preferredProjects;
    }

    public List<Project> getPreferredProjects() {
        return preferredProjects;
    }

    public void setAssignedProject(Project project) {
        this.assignedProject = project;
    }

    public Project getAssignedProject() {
        return assignedProject;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Student other) {
        int indexThis = this.preferredProjects.indexOf(this);
        int indexOther = other.preferredProjects.indexOf(other);
        return Integer.compare(indexThis, indexOther);
    }
}

