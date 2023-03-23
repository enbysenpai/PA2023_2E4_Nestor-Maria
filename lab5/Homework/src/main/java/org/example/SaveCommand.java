package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class SaveCommand implements Command
{
    private String path;
    private Catalog catalog;

    public SaveCommand(Catalog catalog,String path)
    {
        this.catalog=catalog;
        this.path=path;
    }

    public void run()
    {
        try
        {
            ObjectMapper objectMapper=new ObjectMapper();
            objectMapper.writeValue(new File(path),catalog);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
