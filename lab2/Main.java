public class Main
{
    public static void main(String[] args)
    {
        Location c1 = new Location();
        c1.setName("Iasi");
        c1.setX(2.8);
        c1.setY(3.4);
        double X=c1.getX();
        double Y=c1.getY();
        System.out.println(c1+" "+X+" "+Y);

        Location c2 = new Location("Vaslui", 10.0, 20.0);
        X=c2.getX(); Y=c2.getY();
        System.out.println(c2+" "+X+" "+Y);

        Road c3 = new Road("B15",RoadType.HIGHWAY,c1,c2);
        RoadType type;
        String name;
        name= c3.getName();
        type=c3.getType();
        System.out.println(name+" "+type+" "+c1+" "+c2);
    }
}
