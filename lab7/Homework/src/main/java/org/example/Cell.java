package org.example;

import java.util.ArrayList;
import java.util.List;

public class Cell
{
    private boolean visited;
    private List<Token> tokens;

    public Cell()
    {
        visited=false;
        tokens=new ArrayList<>();
    }

    public synchronized boolean isVisited() {
        return visited;
    }

    public synchronized void setVisited() {
        this.visited = true;
    }

    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }
}
