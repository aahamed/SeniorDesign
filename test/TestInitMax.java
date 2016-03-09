/*
* Author: Aadil Ahamed
* Description: Unit Test Suite for InitMax.java
*/

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class TestInitMax {
   
   private boolean mstEquals(int[] res, int[] exp)
   {
       if(res == null)
       {
           return false;
       }
       if(res.length != exp.length)
       {
           return false;
       }
       else
       {
           for(int i = 1; i < res.length; i++)
           {
               if(res[i] != exp[i])
               {
                   return false;
               }
           }
           return true;
       }
       
   }
   
   @Test
   public void testInitMax()
   {
        List<Coordinate<Double>> listOfCoords = new ArrayList<Coordinate<Double>>();
		listOfCoords.add(new Coordinate<Double>(1.0, 3.0));
		listOfCoords.add(new Coordinate<Double>(20.0, 20.0));
		listOfCoords.add(new Coordinate<Double>(70.0, 5.0));
		Coordinate<Double> com = new Coordinate<Double>(0.0, 0.0);
		List<Integer> row1 = Arrays.asList(new Integer[]{2, 2});
        List<Integer> row2 = Arrays.asList(new Integer[]{2, 2});
        List<List<Integer>> oldDistMat = new ArrayList<List<Integer>>();
        oldDistMat.add(row1);
        oldDistMat.add(row2);
		InitMaxOut res = InitMax.initMax(listOfCoords, com, oldDistMat);
        int[] exp = new int[]{0, 0, 1};
        System.out.println("MST: \n" + PrimMST.MSTtoString(res.getParent()));
        assertTrue(mstEquals(res.getParent(), exp));
   }
}