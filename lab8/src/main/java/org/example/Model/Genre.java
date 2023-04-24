package org.example;

public class Genre
{
    private int id;
    private String name;

    public Genre(int id, String name)
    {
        this.id=id;
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString()
    {
        StringBuilder sb=new StringBuilder();
        sb.append("GENRE: ");
        sb.append("id=").append(id);
        sb.append(" name=").append(name);
        return sb.toString();
    }
}
