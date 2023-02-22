public class Lab1
{
    public static void main(String args[])
    {
        Lab1 lab1=new Lab1();
        lab1.compulsory();
    }

    void compulsory()
    {
        System.out.println("Hello world!");

        String languages[] = {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};

        int n=(int)(Math.random()*1_000_000);
        System.out.println(n);

        n=(n*3+10101+0xFF)*6;
        System.out.println(n);

        while(n>9)
        {
            int s=0;

            while(n!=0)
            {
                s=s+n%10;
                n/=10;
            }
            n=s;
        }
        System.out.println(n);

        System.out.println("Willy-nilly, this semester I wil learn "+languages[n]);
    }
}