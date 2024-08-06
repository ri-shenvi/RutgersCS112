/**
 * @(#)PhoneNumber.java
 *
 * Phone Number randomly a phone number using Math.random method and outputs
 * in the form (XXX) XXX-XXXX where the area code and exchange code 
 * must NOT begin with a 0 or 1
 * 
 * Utilize Math.random() to create a random area code, exchange code, 
 * and subscriber line number (the last four digits). 
 * 
 * You may NOT create any additional variables
 *
 * @author (Riya Shenvi) 
 * @date (10.31.2022)
 * @period (1) 
 * 22HPFY001
 */
public class PhoneNumber
{
    public static void main(String[] args)
    {
    	/* Declaration statements */
        int areaCode, exchangeCode, lastFourDigits; 
        /* Create an assignment statement for each variable below here */
        areaCode = (int)((Math.random()*800) + 200);
        exchangeCode = (int)((Math.random()*800)+200);
        lastFourDigits = (int)(Math.random()*10000); 

        
        /* Create a single output statement using print 
         * or println method in the form (XXX) XXX - XXXX
         * below this comment.
         * POSTCONDITION: A logic error may appear
         */

        System.out.println("(" + areaCode + ")" + exchangeCode + "-" + lastFourDigits);
        
    }//end of main
}//end of class
