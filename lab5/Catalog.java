import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Catalog implements Serializable
{
    private String name;
    private List<Document> documents=new ArrayList<>();

    //...

    public void add(Document doc)
    {
        documents.add(doc);
    }
    public Document findById(String id)
    {
        return documents.stream()
                .filter(d->d.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
