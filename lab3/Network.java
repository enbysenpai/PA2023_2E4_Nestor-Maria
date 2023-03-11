import java.util.ArrayList;
import java.util.List;

public class Network
{
    private List<Node> nodes=new ArrayList<>();

    public Network(){}
    public void addNode(Node node)
    {
        nodes.add(node);
    }

    private String createString()
    {
        String string="";
        for(int i=0;i< nodes.size();i++)
            string+=nodes.get(i);
        return string;
    }

    @Override
    public String toString()
    {
        return createString();
    }

}
