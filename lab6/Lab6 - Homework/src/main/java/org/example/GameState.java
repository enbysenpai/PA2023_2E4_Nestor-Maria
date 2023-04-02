package org.example;

import java.io.Serializable;
import java.util.List;

public class GameState implements Serializable {
    private int numDots;
    private List<LineSegment> lines;
    private int nextIndexColor;
    private MainFrame gameState;

    public GameState(int numDots, List<LineSegment> lines, int nextIndexColor)
    {
        this.numDots = numDots;
        this.lines = lines;
        this.nextIndexColor = nextIndexColor+1;
    }

    public int getNumDots() {
        return numDots;
    }

    public List<LineSegment> getLines() {
        return lines;
    }

    public int getNextIndexColor() {
        return nextIndexColor;
    }

}
