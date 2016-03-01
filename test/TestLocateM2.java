import org.junit.Test;
import static org.junit.Assert.assertEquals;
public class TestJunit 
{
   private static final double DELTA = 1e-15;
   
   @Test
   public void testAdd() {
      String str= "Junit is working fine";
      assertEquals("Junit is working fine",str);
   }
   
   @Test
   public void testLocateM2()
   {
	   Coordinate<Integer> p = new Coordinate<Integer>();
	   Coordinate<Integer> q = new Coordinate<Integer>();
	   double theta = Math.PI/12;
	   LocateM2Out res = LocateM2.locateM2(p, q, theta);
	   Coordinate<Double> expAN1 = new Coordinate<Double>(3.5, 3.0);
	   Coordinate<Double> expAN2 = new Coordinate<Double>(6.5, 7.0);
	   assertTrue(res.getAN1().getX(), expAN1.getX(), DELTA);
	   assertTrue(res.getAN1().getY(), expAN1.getY(), DELTA);
	   assertTrue(res.getAN2().getX(), expAN2.getX(), DELTA);
	   assertTrue(res.getAN2().getY(), expAN2.getY(), DELTA);
   }
}