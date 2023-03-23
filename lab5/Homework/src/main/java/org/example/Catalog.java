package org.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Catalog implements Serializable
{
    private String name;
    private List<Document> documents=new ArrayList<>();
    public Catalog(){}
    public Catalog(String name)
    {
        this.name=name;
    }


    public String getName() {
        return name;
    }

    public Document findById(String id)
    {
        return documents.stream()
                .filter(d->d.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    public List<Document> getDocuments()
    {
        return documents;
    }

}
