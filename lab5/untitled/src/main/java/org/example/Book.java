package org.example;

public class Book extends Document
{
    private String author;

    Book(){}
    public Book(String id,String title,String location,String author)
    {
        super(id,title,location);
        this.author=author;
    }

    public String getAuthor()
    {
        return author;
    }
    public void setAuthor(String author)
    {
        this.author=author;
    }

    @Override
    public String toString()
    {
        return getTitle()+" "+getAuthor()+" "+getLocation()+" "+ getTags();
    }
}
