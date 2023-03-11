package Homework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Network
{
    private List<Node> nodes;

    public Network()
    {
        this.nodes=new ArrayList<>();
    }
    public Network(List<Node> nodes) {
        this.nodes = nodes;
    }
    public void addNode(Node node)
    {
        this.nodes.add(node);
    }
    public List<Node> getNodes()
    {
        return nodes;
    }
    public void setNodes(List<Node> nodes)
    {
        this.nodes=nodes;
    }


    @Override
    public String toString()
    {
        return printNetwork();
    }

    public String printNetwork()
    {
        String string="";
        List<Node> sortedNodes = new ArrayList<>(nodes);
        Collections.sort(sortedNodes, Comparator.comparingInt(Node::getNodeImportance).reversed());

        string+="Network: \n";
        for (int i=0;i<sortedNodes.size();i++)
        {
            string+=sortedNodes.get(i).toString();

            List<Node> neighbors = sortedNodes.get(i).getNeighbors();
            if (!neighbors.isEmpty())
            {
                string+="  Neighbors: ";
                for(int j=0;j< neighbors.size();j++)                {
                    string+=neighbors.get(j).toString()+", ";
                }
            }
            else
            {
                string+="  No neighbors ";
            }
            string+="\n";
        }
        return string;
    }
}
