public class Homework
{
    public static void main(String[] args) {

        int i, j, k = 0;
        long start, stop, runningTime;
        boolean tooBig = false;

        
        if (args.length < 1) {
            System.out.println("Not enough arguments!");
            System.exit(-1);
        }

        
        start = System.nanoTime();
        String str = args[0];
        int n = Integer.parseInt(str);
        System.out.println(n);

        if (n > 30_000) {
            tooBig = true;
        }

        int[][] latin = new int[n][n];

        for (i = 0; i < n; i++) {
            k++;
            for (j = 0; j < n; j++) {
                if (k + j <= n)
                    latin[i][j] = k + j;
                else
                    latin[i][j] = k + j - n;
            }
        }

        if(tooBig==false)
        {
            for (i = 0; i < n; i++)
                for (j = 0; j < n; j++)
                    System.out.println(latin[i][j] + " ");
        }
        start = System.nanoTime();

        if(tooBig==false)System.out.println("Rows:");
        for (i = 0; i < n; i++)
        {
            String row = "";
            for (j = 0; j < n; j++)
            {
                row = row + latin[i][j];
            }
            if (tooBig==false) System.out.println(row);
        }

        if(tooBig==false)System.out.println("Columns:");
        for (j = 0; j < n; j++)
        {
            String column = "";
            for (i = 0; i < n; i++)
            {
                column = column + latin[i][j];
            }
            if (tooBig==false) System.out.println(column);
        }
        stop = System.nanoTime();

        runningTime = stop - start;

        if (tooBig==true)
        {
            System.out.println(runningTime);
        }


    }
}
