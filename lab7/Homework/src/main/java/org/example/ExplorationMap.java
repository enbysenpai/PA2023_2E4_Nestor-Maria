package org.example;

import java.util.List;

public class ExplorationMap {
    private final Cell[][] matrix;
    private final SharedMemory mem;

    public ExplorationMap(int n) {
        mem = new SharedMemory(n * n * n);
        matrix = new Cell[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = new Cell();
            }
        }
    }

    public int getSize() {
        return matrix.length;
    }

    public synchronized boolean visit(int row, int col, Robot robot) {
        if (matrix[row][col].isVisited()) {
            System.out.println(robot.getName() + " failed to access the cell [" + row + "," + col + "]");
            return false;
        } else {
            Cell cell = matrix[row][col];
            if (!cell.isVisited()) {
                List<Token> tokens = mem.extractTokens(matrix.length);

                cell.setTokens(tokens);
                cell.setVisited();

                System.out.println(robot.getName() + " visited cell [" + row + "," + col + "] and extracted: " + tokens);
                return true;
            }
        }
        return false;
    }

    public void setTokens(int row, int col, List<Token> tokens) {
        matrix[row][col].setTokens(tokens);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                sb.append(matrix[i][j].isVisited());
                sb.append("\t");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
