/**
 * QuadraticFormula.java
 * 
 * Utilizes the Math class to compute and solve 
 * Quadratic Equations in the form ax^2+bx+c=0
 * Outputs two solutions for each/every equation.
 * 
 * You may NOT create any additional variables
 * 
 * @author (Riya Shenvi) 
 * @date (10.31.2022)
 * @period (1)
 * 22HPFY001
 */
public class QuadraticFormula
{
   public static void main(String[] args)
   {
	   /* Declaration statements, these are the ONLY variables to be used */
	   int a, b, c;
	   double discriminant, answer1, answer2;
       
       /* Create 3 assignment statements using Math.random() 
        * to assign random integers in the range [-30,30]
        * to each of a, b, and c below this comment.
        */
        a = ((int)(Math.random()*61))-30;
        b = ((int)(Math.random()*61))-30;
        c = ((int)(Math.random()*61))-30;
       
       /* Calculate and assign the discriminant below here (discriminant only!) */
        discriminant = (b*b)-(4*a*c);

       /* Compute and assign values to answer1 and answer2 below here */
        answer1 = (-b - Math.sqrt(discriminant)) / (2*a);
        answer2 = (-b + Math.sqrt(discriminant)) / (2*a);

       
       /* Use print or println method(s) to output the results
        * as shown in the examples. 
        */

        System.out.println("The solutions to " + a + "x^2 + " + b + "x " + "+ " + c + " are " + answer1 + " and " + answer2);
       
   }//end of class
}//end of QuadraticFormula
