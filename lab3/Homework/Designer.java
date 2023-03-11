package Homework;

import java.util.Map;

public class Designer extends Person
{
    private String designSoftware;
    private String designStyle;

    public Designer(){}
    public Designer(String name, int id, int dayOfBirth, int monthOfBirth, int yearOfBirth, Map<Node,String> relationships, String designSoftware, String designStyle)
    {
        super(name,id,dayOfBirth,monthOfBirth,yearOfBirth,relationships);
        this.designSoftware=designSoftware;
        this.designStyle=designStyle;
    }
    public void setDesignSoftware(String software)
    {
        this.designSoftware=software;
    }
    public void setDesignStyle(String style)
    {
        this.designStyle=style;
    }

    public String getDesignSoftware()
    {
        return designSoftware;
    }
    public String getDesignStyle()
    {
        return designStyle;
    }
}
