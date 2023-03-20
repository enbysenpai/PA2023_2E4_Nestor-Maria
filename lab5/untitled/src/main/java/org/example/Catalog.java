package org.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Catalog implements Serializable
{
    private String name;
    private List<Document> documents=new ArrayList<>();

    Catalog(){}
    public Catalog(String name)
    {
        this.name=name;
    }

    public void add(Document doc)
    {
        documents.add(doc);
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

    public String toString()
    {
        StringBuilder sb=new StringBuilder();
        sb.append("Catalog: ").append(name).append(" ");
        for(Document doc:documents)
        {
            sb.append(doc.toString()).append(" ");
        }
        return sb.toString();
    }
}
