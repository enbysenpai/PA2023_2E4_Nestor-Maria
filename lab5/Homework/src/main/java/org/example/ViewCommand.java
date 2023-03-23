package org.example;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class ViewCommand implements Command
{
    private Document document;
    private String location;
    public ViewCommand(Document document)
    {
        this.document=document;
        this.location=document.getLocation();
    }

    public void run()
    {
        if(location.startsWith("http"))
            browse(location);
        else if(location.endsWith(".txt"))
            open(location);
        else
            System.out.println("Can't open location: "+location);
    }

    private void open(String location)
    {
        try
        {
            Desktop desktop=Desktop.getDesktop();
            desktop.open(new File(location));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void browse(String location)
    {
        try
        {
            Desktop desktop=Desktop.getDesktop();
            desktop.browse(new URI(location));
        }
        catch (URISyntaxException | IOException e)
        {
            e.printStackTrace();
        }
    }
}
