/**
 * Circle.java prints the area of a circle with two different radii
 * 
 * Modify this Java application to utilize the Math class
 * 
 * @author (Riya Shenvi) 
 * @date (10.31.2022)
 * @period (1)
 * 22HPFY001
 */
public class Circle
{
    public static void main(String[] args)
    {
        final double PI = Math.PI;
        
        int radius = 5;
        double area1 = PI * radius * radius;
        double circumference1 = 2 * PI * radius;
        System.out.println("The area of a circle with radius " + radius + " is " + area1);
        System.out.println("The circumference of a circle with radius " + radius + " is " + circumference1);
        
        radius = 10;
        double area2 = PI * radius * radius;
        double circumference2 = 2 * PI * radius;
        System.out.println("The area of a circle with radius " + radius + " is " + area2);
        System.out.println("The circumference of a circle with radius " + radius + " is " + circumference2);

        double areaFactor = area2 / area1; 
        System.out.println("The factor by which the area changed after doubling the radius of the circle is " + areaFactor);
        double circumFactor = circumference2 / circumference1;
        System.out.println("The factor by which the circumference changed after doubling the radius of the circle is " + circumFactor);


        System.out.println("----------------------------------------------------------------------------------------------"); //Separation Line

        //Utilizing Math.random() to test doubling

        int randRadius1 = 15 + (int)(Math.random() *65);
        double area3 = PI * randRadius1 * randRadius1;
        double circumference3 = 2 * PI * randRadius1;
        System.out.println("The area of a circle with radius " + randRadius1 + " is " + area3);
        System.out.println("The circumference of a circle with radius " + randRadius1 + " is " + circumference3);

        int randRadius2 = 2*randRadius1;
        double area4 = PI * randRadius2 * randRadius2;
        double circumference4 = 2 * PI * randRadius2;
        System.out.println("The area of a circle with radius " + randRadius2 + " is " + area4);
        System.out.println("The circumference of a circle with radius " + randRadius2 + " is " + circumference4);

        double areaFactor2 = area4 / area3; 
        System.out.println("The factor by which the area changed after doubling the radius of the circle is " + areaFactor2);
        double circumFactor2 = circumference4 / circumference3;
        System.out.println("The factor by which the circumference changed after doubling the radius of the circle is " + circumFactor2);
        





        

    }//end of main method
}//end of class
