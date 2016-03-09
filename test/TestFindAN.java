/*
* Author: Aadil Ahamed
* Description: Unit Test Suite for FindAN.java
*/

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;

public class TestFindAN {
   
   @Test
   public void testFindAN1()
   {
       assertTrue(true);
   }
   
   @Test
   public void testFindAN2()
   {
       //System.out.println("Testing FindAN2");
       Coordinate<Integer> I, J, K;
       int flag = 1;
       int[] Ind;
       double[][] expRes;
       FindANOut exp, res;
       
       I = new Coordinate<Integer>(143, -795);
       J = new Coordinate<Integer>(0, 0);
       K = new Coordinate<Integer>(147, -754);
       Ind = new int[]{-1,0,-1};
       expRes = new double[][]{{130.0}, {-769.0}};
       flag = 1;
       exp = new FindANOut(expRes, flag);
       res = FindAN.findANm(I, J, K, Ind);
       System.out.println("res: " + res);
       System.out.println("exp: " + exp);
       assertTrue(testEquals(res, exp));
       
       I = new Coordinate<Integer>(143, 100);
       J = new Coordinate<Integer>(0, 0);
       K = new Coordinate<Integer>(147, 10);
       Ind = new int[]{1,0,1};
       expRes = new double[][]{{174.0}, {37.0}};
       flag = 1;
       exp = new FindANOut(expRes, flag);
       res = FindAN.findANm(I, J, K, Ind);
       System.out.println("res: " + res);
       System.out.println("exp: " + exp);
       assertTrue(testEquals(res, exp));
       
        
       I = new Coordinate<Integer>(143, 100);
       J = new Coordinate<Integer>(1, 1);
       K = new Coordinate<Integer>(700, 100);
       Ind = new int[]{0,1,1};
       expRes = new double[][]{{401.0}, {-199.0}};
       flag = 1;
       exp = new FindANOut(expRes, flag);
       res = FindAN.findANm(I, J, K, Ind);
       System.out.println("res: " + res);
       System.out.println("exp: " + exp);
       assertTrue(testEquals(res, exp));
   }
   
   public static boolean arrayEquals(double[][] a1, double a2[][])
   {
       if(a1 == null || a2 == null)
       {
           System.out.println("Null Pointer");
           return false;
       }
       else if(a1.length != a2.length || a1[0].length != a2[0].length)
       {
           System.out.println("Length mismatch");
           return false;
       }
       else
       {
           for(int i = 0; i < a1.length; i++)
           {
               for(int j = 0; j < a1[0].length; j++)
               {
                   if(!doubleEquals(a1[i][j], a2[i][j]))
                   {
                        System.out.printf("a1[%d][%d] = %.2f  but a2[%d][%d] = %.2f\n", i, j, a1[i][j], i, j, a2[i][j]);
                        return false;
                   }
               }
               
           }
           return true;
       }
   }
   
   public static boolean doubleEquals(double d1, double d2)
   {
        double DELTA = 1e-2;
        if(Math.abs(d1 - d2) <= DELTA)
        {
            return true;
        }
        else
        {
            return false;
        }
   }
    
   public boolean testEquals(FindANOut res, FindANOut exp)
   {
        if(res == null || exp == null)
            return false;
        if(arrayEquals(res.getResult(), exp.getResult()) && res.getFlag() == exp.getFlag())
        {
            return true;
        }
        else
        {
            return false;
        }
   }
}