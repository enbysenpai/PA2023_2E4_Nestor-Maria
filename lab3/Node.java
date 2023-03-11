public interface Node
{
    String getName();

    int getId();

    default double getWeight()
    {
        return 0.0;
    }
}
