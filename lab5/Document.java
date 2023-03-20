import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public abstract class Document implements Serializable
{
    private String id;
    private String title;
    private String location;

    private Map<String,Object> tags=new HashMap<>();

    //...

    public void addTag(String key,Object obj)
    {
        tags.put(key,obj);
    }

    //...
}
