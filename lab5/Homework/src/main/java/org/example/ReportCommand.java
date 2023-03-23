package org.example;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;

public class ReportCommand implements Command
{
    private Catalog catalog;

    public ReportCommand(Catalog catalog)
    {
        this.catalog=catalog;
    }

    @Override
    public void run()
    {
        try
        {
            Configuration config=new Configuration(Configuration.VERSION_2_3_32);
            config.setDirectoryForTemplateLoading(new File("D:/PA/laboratory5/src/main/java/org/example/"));

            Template template=config.getTemplate("Template.flt");

            Writer out=new OutputStreamWriter(new FileOutputStream("D:/PA/laboratory5/src/main/java/org/example/"+catalog.getName()+".html"));
            template.process(catalog,out);
        }
        catch(IOException | TemplateException e)
        {
            e.printStackTrace();
        }
    }
}
