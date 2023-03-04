public class Bonus2
{
    public static void main(String[] args)
    {
        //Create the adjacency matrix of a regular graph. The number of vertices and the vertex degree are given as arguments.
        if (args.length < 2)
        {
            System.out.println("Not enough arguments!");
            System.exit(-1);
        }

        int noOfVertices=Integer.parseInt(args[0]);
        int degree=Integer.parseInt(args[1]);

        System.out.println("Number of vertices: "+noOfVertices);
        System.out.println("The degree: "+degree);

        //the necessary and sufficient conditions: n>=k+1, n*k is even
        if(!(noOfVertices>=degree && noOfVertices*degree%2==0))
        {
            System.out.println("This graph does not exist!");
            System.exit(-1);
        }

        int[][] adjacencyMatrix=new int[noOfVertices][noOfVertices];

        int noOfEdges=(noOfVertices*degree)/2;

        System.out.println("Number of edges: "+noOfEdges);

        int i,j;

        for(i=0;i<noOfVertices;i++)
            for(j=0;j<noOfVertices;j++)
                adjacencyMatrix[i][j]=0;

        //construction of the matrix:
        //we add 1 on the first line
        int k=1;
        for(j=1;j<noOfVertices;j++)
        {
            if(k<=degree)
            {
                adjacencyMatrix[0][j]=1;
                k++;
            }

        }

        k=0;
        for (i = 1; i < noOfVertices; i++)
        {
            for (j =1; j < noOfVertices; j++)
            {
                adjacencyMatrix[i][j]=adjacencyMatrix[i-1][j-1];
            }
            adjacencyMatrix[i][0]=adjacencyMatrix[i-1][noOfVertices-1];
        }

        for (i = 0; i < noOfVertices; i++)
        {
            for (j = 0; j < noOfVertices; j++)
                System.out.print(adjacencyMatrix[i][j]+" ");
            System.out.println("");
        }

    }
}
