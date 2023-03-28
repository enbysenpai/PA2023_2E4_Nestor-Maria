package org.example;

import java.awt.*;
import java.util.List;

public class Graph
{
    private List<Vertex> vertices;
    private List<Edge> edges;

    public void addVertex(Vertex v) {
        vertices.add(v);
    }

    public void addEdge(Edge e) {
        edges.add(e);
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    public List<Edge> getEdges() {
        return edges;
    }
}