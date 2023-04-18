package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ExplorationMap
{

    private Cell[][] matrix;
    private SharedMemory mem;
    private Lock lock;

    public ExplorationMap(int n)
    {
        mem=new SharedMemory(n*n*n);
        matrix=new Cell[n][n];
        lock=new ReentrantLock();
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                matrix[i][j]=new Cell();
            }
        }
    }

    public int getSize()
    {
        return matrix.length;
    }

    public boolean visit(int row,int col,Robot robot)
    {
        synchronized (matrix[row][col])
        {
            Cell cell=matrix[row][col];

            if(!cell.isVisited())
            {
                List<Token> tokens=mem.extractTokens(matrix.length);

                cell.setTokens(tokens);
                cell.setVisited(true);

                System.out.println(robot.getName() + " visited cell [" + row + "," + col + "] and extracted: " + tokens);
                return true;
            }
            else
            {
                System.out.println(robot.getName()+" failed to access the cell ["+row+","+col+"]");
                return false;
            }
        }
    }



    public void setTokens(int row, int col, List<Token> tokens)
    {
        matrix[row][col].setTokens(tokens);
    }

    @Override
    public String toString()
    {
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<matrix.length;i++)
        {
            for(int j=0;j<matrix.length;j++)
            {
                sb.append(matrix[i][j].isVisited());
                sb.append("\t");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
