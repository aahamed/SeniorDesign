/*
* Author: Aadil Ahamed
* Description: Unit Test Suite Runner for TestInitMax.java
*/

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;


public class TestInitMaxRunner {
   public static void main(String[] args) {
      Result result = JUnitCore.runClasses(TestInitMax.class);
      for (Failure failure : result.getFailures()) {
         System.out.println(failure.toString());
      }
      System.out.println(result.wasSuccessful());
   }
}  	