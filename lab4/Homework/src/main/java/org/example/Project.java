package org.example;

public class Project implements Comparable<Project> {
    String name;
    Student assignedStudent;

    public Project() {
    }

    public Project(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAssignedStudent(Student student) {
        this.assignedStudent = student;
    }

    public Student getAssignedStudent() {
        return assignedStudent;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Project other) {
        return this.name.compareTo(other.name);
    }
}

