/*
* Author: Aadil Ahamed
* Description: Unit Test Suite Runner for TestFindAN.java
*/

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestFindANRunner {
   public static void main(String[] args) {
      Result result = JUnitCore.runClasses(TestFindAN.class);
      for (Failure failure : result.getFailures()) {
         System.out.println(failure.toString());
      }
      System.out.println(result.wasSuccessful());
   }
}  	