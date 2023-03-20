package org.example;

import jakarta.persistence.criteria.CriteriaBuilder;

import java.io.IOException;

public class Main
{
    public static void main(String[] args) throws IOException, InvalidCatalogException
    {
        Main app=new Main();
        app.testCreateSave();
        app.testLoadView();

    }

    private void testCreateSave() throws IOException
    {
        Catalog catalog= new Catalog("MyDocuments");
        var book=new Book("article1","My Book Title","D:/research/books/book1.txt","A book author");
        var book2=new Book("article2","My Book Title 2","D:/research/books/book2.txt","Another book author");
        var article=new Article("book1","My Article Title", "https://profs.info.uaic.ro/~acf/java/","An article author");
        var article2=new Article("book2","My Article Title 2", "https://matias.ma/nsfw/","Another article author");

        catalog.add(book);
        catalog.add(book2);
        catalog.add(article);
        catalog.add(article2);


        CatalogUtil.save(catalog,"d:/research/catalog.txt");
    }

    private void testLoadView() throws InvalidCatalogException
    {
        Catalog catalog=CatalogUtil.load("d:/research/catalog.txt");
        CatalogUtil.view(catalog.findById("article1"));
        CatalogUtil.view(catalog.findById("article2"));

        CatalogUtil.view(catalog.findById("book1"));

        CatalogUtil.view(catalog.findById("book2"));
    }
}