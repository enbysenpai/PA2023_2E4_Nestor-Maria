package org.example;

import java.io.IOException;

public class Main
{
    Catalog catalog=new Catalog("MyDocuments");

    public static void main(String[] args)
    {
        Main app=new Main();
        app.testCreateSave();
        app.testLoadView();
    }

    private void testCreateSave()
    {
        var book=new Book("book1","My Book Title","D:/PA/laboratory5/research/books/book1.txt","A book author");
        var article=new Article("article1","My Article Title","https://matias.ma/nsfw/","An article author");
        AddCommand add=new AddCommand(book,catalog);
        AddCommand add2=new AddCommand(article,catalog);
        add.run();
        add2.run();

        SaveCommand save=new SaveCommand(catalog,"D:/PA/laboratory5/research/catalog.json");
        save.run();
    }
    private void testLoadView()
    {

        LoadCommand load=new LoadCommand("D:/PA/laboratory5/research/catalog.json",catalog);

        try
        {
            load.run();
        }
        catch(InvalidCatalogException e)
        {
            e.printStackTrace();
        }

        Document document= catalog.findById("book1");
        ViewCommand view=new ViewCommand(document);
        view.run();

        Document document1= catalog.findById("article1");
        ViewCommand view1=new ViewCommand(document1);
        view1.run();

        ToStringCommand str=new ToStringCommand(catalog,"D:/PA/laboratory5/research/catalog.json");
        System.out.println("Catalog info:");
        str.run();

        ListCommand list=new ListCommand(catalog,"D:/PA/laboratory5/research/catalog.json");
        System.out.println("The catalog contains the documents: ");
        list.run();

        ReportCommand report=new ReportCommand(catalog);
        System.out.println("Creating the report...");
        report.run();
    }
}