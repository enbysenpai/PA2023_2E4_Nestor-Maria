package org.example;

public class Edge {
    private final Vertex v1;
    private final Vertex v2;
    private boolean isColored;

    public Edge(Vertex v1, Vertex v2) {
        this.v1 = v1;
        this.v2 = v2;
        this.isColored = false;
    }

    public Vertex getV1() {
        return v1;
    }

    public Vertex getV2() {
        return v2;
    }

    public boolean isColored() {
        return isColored;
    }

    public void setColor(boolean colored) {
        isColored = colored;
    }
}