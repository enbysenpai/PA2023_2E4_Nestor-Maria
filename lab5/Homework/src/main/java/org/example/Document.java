package org.example;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Book.class, name = "book"),
        @JsonSubTypes.Type(value = Article.class, name = "article")
})


public abstract class Document implements Serializable
{
    private String id;
    private String title;
    private String location;

    private Map<String,Object> tags=new HashMap<>();


    public Document(){}
    public Document(String id,String title,String location)
    {
        this.id=id;
        this.title=title;
        this.location=location;
    }
    public String getId()
    {
        return id;
    }
    public String getLocation()
    {
        return location;
    }
    public String getTitle()
    {
        return title;
    }

    public void addTag(String key,Object obj)
    {
        tags.put(key,obj);
    }

    public Map<String, Object> getTags() {
        return tags;
    }

    @Override
    public String toString()
    {
        StringBuilder sb=new StringBuilder();
        sb.append("ID: ").append(id);
        sb.append(" Title: ").append(title);
        sb.append(" Location: ").append(location).append(" ");
        for(String tagName:tags.keySet())
        {
            sb.append(tagName).append(": ").append(tags.get(tagName)).append(" ");
        }
        return sb.toString();
    }
}
