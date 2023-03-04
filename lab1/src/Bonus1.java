import java.awt.desktop.SystemEventListener;
import java.net.Inet4Address;

public class Bonus1
{
    public static void main(String[] args)
    {
        //Create the adjacency matrix A of a cycle graph Cn and compute the powers A2, A3, .... An. Give an interpretation of the result.
        if(args.length<1)
        {
            System.out.println("Not enough parameters!");
            System.exit(-1);
        }

        int n= Integer.parseInt(args[0]);
        System.out.println("This is n: "+n);
    }
}