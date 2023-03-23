package org.example;

import java.util.List;

public class ListCommand implements Command
{
    private Catalog catalog;
    private String path;

    public ListCommand(Catalog catalog,String path)
    {
        this.catalog=catalog;
        this.path=path;
    }

    public void run()
    {
        List<Document> documents=catalog.getDocuments();

        StringBuilder sb=new StringBuilder();
        for(Document doc:documents)
        {
            sb.append(doc.getTitle()).append("\n");
        }
        System.out.println(sb.toString());
    }
}

