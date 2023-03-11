package Homework;

import java.util.Map;

public class Programmer extends Person
{
    private String programmingLanguage;
    private int yearsOfExperience;

    public Programmer(){}
    public Programmer(String name, int id, int dayOfBirth, int monthOfBirth, int yearOfBirth, Map<Node,String> relationships, String programmingLanguage, int yearsOfExperience)
    {
        super(name,id,dayOfBirth,monthOfBirth,yearOfBirth,relationships);
        this.yearsOfExperience=yearsOfExperience;
        this.yearsOfExperience=yearsOfExperience;
    }
    public void setProgrammingLanguage(String language)
    {
        this.programmingLanguage=language;
    }
    public void setYearsOfExperience(int years)
    {
        this.yearsOfExperience=years;
    }

    public String getProgrammingLanguage()
    {
        return programmingLanguage;
    }
    public int getYearsOfExperience()
    {
        return yearsOfExperience;
    }
}
