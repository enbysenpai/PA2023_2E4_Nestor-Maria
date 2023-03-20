import javax.xml.catalog.Catalog;

public class Main
{
    public static void Main(String[] args)
    {
        Main app=new Main();
        app.testCreateSave();
        app.testLoadView();
    }
    private void testCreateSave()
    {
        Catalog catalog=new Catalog("MyDocuments");
        var book=new doc("article1",...);
        var article=new doc("book1",...);
        catalog.add(book);
        catalog.add(article);

        CatalogUtil.save(catalog,"d:/research/catalog.json")";"
    }
    private void testLoadView()
    {
        Catalog catalog=CatalogUtil.load("d://research/catalog.json");
        CatalogUtil.view(catalog.findById("article1"));
    }
}
