package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExplorationMap {
    private final Cell[][] matrix;

    public ExplorationMap(int size)
    {
        matrix=new Cell[size][size];
        for(int i=0;i<size;i++)
        {
            for(int j=0;j<size;j++)
            {
                matrix[i][j]=new Cell();
            }
        }
    }

    public int getSize() {
        return matrix.length;
    }

    public void setTokens(int row,int col,List<Token> tokens)
    {
        matrix[row][col].setTokens(tokens);
    }

    public boolean visit(int row, int col, Robot robot)
    {
        synchronized (matrix[row][col])
        {
            Cell cell=matrix[row][col];
            if(!cell.isVisited())
            {
                //Extract tokens
                List<Token> tokens=extractTokens();

                //Store tokens in the cell
                cell.setTokens(tokens);
                cell.setVisited(true);

                //Display success message
                System.out.println(robot.getName()+" visited cell ["+row+","+col+"] and extracted tokens: "+tokens);
                return true;
            }
        }
        return false;
    }

    public List<Token> extractTokens()
    {
        List<Token> tokens=new ArrayList<>();
        for(int i=1;i<= matrix.length* matrix.length;i++)
            tokens.add(new Token(i));
        Collections.shuffle(tokens);
        return tokens.subList(0, matrix.length);
    }



    @Override
    public String toString()
    {
        StringBuilder sb=new StringBuilder();
        for(int i=0;i< matrix.length;i++)
        {
            for(int j=0;j< matrix.length;j++)
            {
                sb.append(matrix[i][j].isVisited()?"Y":"O");
                sb.append("\t");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}

