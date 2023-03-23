package org.example;

import java.util.List;

public class ToStringCommand implements Command
{
    private Catalog catalog;
    private String path;

    public ToStringCommand(Catalog catalog,String path)
    {
        this.catalog=catalog;
        this.path=path;
    }

    @Override
    public void run()
    {
        String name=catalog.getName();
        List<Document> documents=catalog.getDocuments();

        StringBuilder sb=new StringBuilder();
        sb.append("NAME: ").append(name).append(" CONTAINING:\n");
        for(Document doc:documents)
        {
            sb.append(doc.toString()).append("\n");
        }
        System.out.println(sb.toString());
    }
}
