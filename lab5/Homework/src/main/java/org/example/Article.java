package org.example;

public class Article extends Document
{
    private String author;
    public Article(){}
    public Article(String id,String title,String location,String author)
    {
        super(id,title,location);
        this.author=author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString()
    {
        return "ARTICLE: "+getTitle()+" WRITTEN BY "+getAuthor()+" PATH: "+getLocation()+" TAGS: "+ getTags();
    }
}
