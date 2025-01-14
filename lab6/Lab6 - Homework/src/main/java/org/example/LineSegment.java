package org.example;

import java.awt.*;
import java.io.Serializable;

public class LineSegment implements Serializable
{
    int x1,x2,y1,y2;
    Color color;

    public LineSegment(int x1,int y1,int x2,int y2,Color color)
    {
        this.x1=x1;
        this.y1=y1;
        this.x2=x2;
        this.y2=y2;
        this.color=color;
    }

    public int getX1() {
        return x1;
    }

    public int getX2() {
        return x2;
    }

    public int getY1() {
        return y1;
    }

    public int getY2() {
        return y2;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color)
    {
        this.color=color;
    }

    public int getLength()
    {
        return (int)Math.sqrt((this.x1-this.x2)*(this.x1-this.x2) *  + (this.y1-this.y2) * (this.y1-this.y2));
    }

    @Override
    public String toString()
    {
        return getColor().toString();
    }
}
