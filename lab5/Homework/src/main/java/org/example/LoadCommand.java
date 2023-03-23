package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.io.File;
import java.io.IOException;

public class LoadCommand implements Command
{
    private String path;
    private Catalog catalog;

    public LoadCommand(String path,Catalog catalog)
    {
        this.path=path;
        this.catalog=catalog;
    }

    public void run() throws InvalidCatalogException
    {
        try
        {
            ObjectMapper objectMapper=new ObjectMapper();
            Catalog catalog=objectMapper.readValue(
                    new File(path),
                    Catalog.class
            );
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
