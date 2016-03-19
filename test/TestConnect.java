/*
* Author: Aadil Ahamed
* Description: Unit Test Suite for Connect.java
*/

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;

public class TestConnect {
   
   @Test
   public void testConnect()
   {
        ConnectOut res;
        ConnectOut exp;
        
        Coordinate<Integer> p1 = new Coordinate<Integer>(0, 0);
		Coordinate<Integer> q1 = new Coordinate<Integer>(1, 1);
        exp = new ConnectOut(0.17, 0.52, 1, new int[]{1, 0});
        res = Connect.connect(p1, q1, Connect.CONNECT_FLAG);
        //System.out.println("Test case1 failed!\nres: " + res + "\nexp: " + exp); // case1 1st quadrant theta<30
        assertTrue(test_equals(res, exp));

		Coordinate<Integer> p2 = new Coordinate<Integer>(0, 0);
		Coordinate<Integer> q2 = new Coordinate<Integer>(-10, 1);
        exp = new ConnectOut(0.10, 0.09, 1, new int[]{-1, 0});
        res = Connect.connect(p2, q2, Connect.CONNECT_FLAG);
		//System.out.println(Connect.connect(p2, q2, Connect.CONNECT_FLAG)); // case1 3rd quadrant theta<30
        assertTrue(test_equals(res, exp));

		Coordinate<Integer> p3 = new Coordinate<Integer>(0, 0);
		Coordinate<Integer> q3 = new Coordinate<Integer>(-1, 10);
        exp = new ConnectOut(0.10, 0.09, 1, new int[]{0, 1});
        res = Connect.connect(p3, q3, Connect.CONNECT_FLAG);
		//System.out.println(Connect.connect(p3, q3, Connect.CONNECT_FLAG)); // case1 3rd quadrant theta>30
        assertTrue(test_equals(res, exp));

		Coordinate<Integer> p4 = new Coordinate<Integer>(0, 0);
		Coordinate<Integer> q4 = new Coordinate<Integer>(10, 0);
        exp = new ConnectOut(0.11, 0.00, 1, new int[]{1, 0});
        res = Connect.connect(p4, q4, Connect.CONNECT_FLAG);
		//System.out.println(Connect.connect(p4, q4, Connect.CONNECT_FLAG)); // x*y=0    x!=0
        assertTrue(test_equals(res, exp));
        
        Coordinate<Integer> p5 = new Coordinate<Integer>(0, 0);
		Coordinate<Integer> q5 = new Coordinate<Integer>(10, 1);
        exp = new ConnectOut(0.12, 0.08, 1, new int[]{1, 0});
        res = Connect.connect(p5, q5, Connect.CONNECT_FLAG);
		//System.out.println(Connect.connect(p5, q5, Connect.CONNECT_FLAG)); // case1 1rd quadrant theta>30
        assertTrue(test_equals(res, exp));
        
		Coordinate<Integer> p6 = new Coordinate<Integer>(0, 0);
		Coordinate<Integer> q6 = new Coordinate<Integer>(0, 10);
        exp = new ConnectOut(0.11, 0.00, 1, new int[]{0, 1});
        res = Connect.connect(p6, q6, Connect.CONNECT_FLAG);
		//System.out.println(Connect.connect(p6, q6, Connect.CONNECT_FLAG)); // x*y=0   x=0
        assertTrue(test_equals(res, exp));
		
		Coordinate<Integer> p7 = new Coordinate<Integer>(-524, 382);
		Coordinate<Integer> q7 = new Coordinate<Integer>(-585, 502);
        exp = new ConnectOut(1.14, 0.53, 0, new int[]{0, 1});
        res = Connect.connect(p7, q7, Connect.CONNECT_FLAG);
		//System.out.println(res); // x*y=0   x=0
        assertTrue(test_equals(res, exp));
   }
   
   @Test
   public void testConnectC()
   {
        int res;
        int exp;
        
        Coordinate<Integer> p1 = new Coordinate<Integer>(0, 0);
		Coordinate<Integer> q1 = new Coordinate<Integer>(1, 1);
        exp = 1;
        res = Connect.connect(p1, q1, Connect.CONNECT_C_FLAG).getPointer();
        //System.out.println(Connect.connect(p1, q1, Connect.CONNECT_C_FLAG)); // case1 1st quadrant theta<30
        assertTrue(res == exp);
		

		Coordinate<Integer> p5 = new Coordinate<Integer>(0, 0);
		Coordinate<Integer> q5 = new Coordinate<Integer>(10, 1);
        exp = 1;
        res = Connect.connect(p5, q5, Connect.CONNECT_C_FLAG).getPointer();
		//System.out.println(Connect.connect(p5, q5, Connect.CONNECT_C_FLAG)); // case1 1rd quadrant theta>30
        assertTrue(res == exp);



		Coordinate<Integer> p2 = new Coordinate<Integer>(0, 0);
		Coordinate<Integer> q2 = new Coordinate<Integer>(-10, 1);
        exp = 1;
        res = Connect.connect(p2, q2, Connect.CONNECT_C_FLAG).getPointer();
		//System.out.println(Connect.connect(p2, q2, Connect.CONNECT_C_FLAG)); // case1 3rd quadrant theta<30
        assertTrue(res == exp);



		Coordinate<Integer> p3 = new Coordinate<Integer>(0, 0);
		Coordinate<Integer> q3 = new Coordinate<Integer>(-1, 10);
        exp = 1;
        res = Connect.connect(p3, q3, Connect.CONNECT_C_FLAG).getPointer();
		//System.out.println(Connect.connect(p3, q3, Connect.CONNECT_C_FLAG)); // case1 3rd quadrant theta>30
        assertTrue(res == exp);



		Coordinate<Integer> p4 = new Coordinate<Integer>(0, 0);
		Coordinate<Integer> q4 = new Coordinate<Integer>(10, 0);
        exp = 1;
        res = Connect.connect(p4, q4, Connect.CONNECT_C_FLAG).getPointer();
		//System.out.println(Connect.connect(p4, q4, Connect.CONNECT_C_FLAG)); // x*y=0    x!=0
        assertTrue(res == exp);

		Coordinate<Integer> p6 = new Coordinate<Integer>(0, 0);
		Coordinate<Integer> q6 = new Coordinate<Integer>(0, 10);
        exp = 1;
        res = Connect.connect(p6, q6, Connect.CONNECT_C_FLAG).getPointer();
		//System.out.println(Connect.connect(p6, q6, Connect.CONNECT_C_FLAG)); // x*y=0   x=0
        assertTrue(res == exp);
		
		Coordinate<Integer> p7 = new Coordinate<Integer>(-524, 382);
		Coordinate<Integer> q7 = new Coordinate<Integer>(-585, 502);
        exp = 1;
        res = Connect.connect(p7, q7, Connect.CONNECT_C_FLAG).getPointer();
		//System.out.println(res); // x*y=0   x=0
        assertTrue(res == exp);
        
        Coordinate<Integer> p8 = new Coordinate<Integer>(-10, -10);
		Coordinate<Integer> q8 = new Coordinate<Integer>(100, 10);
        exp = 0;
        res = Connect.connect(p8, q8, Connect.CONNECT_C_FLAG).getPointer();
		//System.out.println("res: " + res + " exp:" + exp); // x*y=0   x=0
        assertTrue(res == exp);
   }
   
   public static boolean double_equals(double d1, double d2)
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
    
   public boolean test_equals(ConnectOut res, ConnectOut exp)
   {
        if(res == null || exp == null)
            return false;
        if(double_equals(res.getNum(), exp.getNum()) && double_equals(res.getTheta(), exp.getTheta()) && 
            res.getPointer() == exp.getPointer() && Arrays.equals(res.getDirection(), exp.getDirection()))
        {
            return true;
        }
        else
        {
            return false;
        }
   }
}