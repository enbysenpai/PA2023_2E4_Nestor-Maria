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
    private List<Integer> positions;

    public ExplorationMap(int n)
    {
        mem=new SharedMemory(n*n*n);
        positions=new ArrayList<>();
        matrix=new Cell[n][n];
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                matrix[i][j]=new Cell();
            }
        }
        for(int i=0;i<n*n;i++)
            positions.add(i);
        Collections.shuffle(positions);
    }

    public int getSize()
    {
        return matrix.length;
    }

    public synchronized int getPosition()
    {
        return positions.get(0);
    }

    public int getPositionLength()
    {
        return positions.size();
    }
    public synchronized void delPosition()
    {
        positions.remove(0);
    }

    public synchronized boolean visit(int row,int col,Robot robot)
    {
        System.out.println("Cell "+row+" "+col+" is visited "+matrix[row][col].isVisited());

        if(matrix[row][col].isVisited())
        {
            System.out.println(robot.getName()+" failed to access the cell ["+row+","+col+"]");
            return false;
        }
        else
        //synchronized (matrix[row][col])
        {
            Cell cell=matrix[row][col];
                if(!cell.isVisited())
                {
//                    System.out.println("Cell "+row+" "+col+" is visited "+cell.isVisited());

                    List<Token> tokens=mem.extractTokens(matrix.length);

                    cell.setTokens(tokens);
                    cell.setVisited();

                    System.out.println("Cell "+row+" "+col+" is visited?: "+cell.isVisited()+" "+robot.getName() + " visited cell [" + row + "," + col + "] and extracted: " + tokens);
                    return true;
                }

                System.out.println(robot.getName()+" failed to access the cell ["+row+","+col+"]");
        }
        return false;
    }

    public synchronized boolean checkMatrixCell(int row,int col)
    {
        return matrix[row][col].isVisited();
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
