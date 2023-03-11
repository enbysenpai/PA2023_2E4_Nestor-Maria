package Homework;

import java.util.List;
import java.util.Map;

public interface Node
{
    String getName();

    int getId();
    List<Node> getNeighbors();
    int getNodeImportance();
    default double getWeight()
    {
        return 0.0;
    }
}
