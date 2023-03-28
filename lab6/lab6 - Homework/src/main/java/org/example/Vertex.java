package org.example;

public class Vertex
{
    private final int x,y,id;

    public Vertex(int id,int x,int y)
    {
        this.id=id;
        this.x=x;
        this.y=y;
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
