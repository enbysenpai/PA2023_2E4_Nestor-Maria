package org.example;

public class AddCommand implements Command
{
    private Document entry;
    private Catalog catalog;

    public AddCommand(Document entry,Catalog catalog)
    {
        this.entry=entry;
        this.catalog=catalog;
    }

    @Override
    public void run()
    {
        try
        {
            if(catalog.getDocuments().stream()
                    .filter(d->d.getId().equals(entry.getId()))
                    .findFirst()
                    .orElse(null) == null )
                catalog.getDocuments().add(entry);
            else
                System.out.println("Item already exists");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
