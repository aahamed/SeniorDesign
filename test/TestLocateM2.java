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
       Coordinate<Integer> p;
       Coordinate<Integer> q;
       double theta;
       int H = GlobalConstants.H;
       LocateOut res;
       Coordinate<Double> expAN1;
       Coordinate<Double> expAN2
        
	   p = new Coordinate<Integer>(0, 0);
	   q = new Coordinate<Integer>(10, 10);
	   theta = Math.PI/12;
	   res = (new LocateM2(p, q, theta)).out;
	   expAN1 = new Coordinate<Integer>(91, 0);
	   expAN2 = new Coordinate<Integer>(-76, 0);
	   assertEquals(res.getAN().getX(), expAN1.getX(), DELTA);
	   assertEquals(res.getAN().getY(), expAN1.getY(), DELTA);
	   assertEquals(res.getAN2().getX(), expAN2.getX(), DELTA);
	   assertEquals(res.getAN2().getY(), expAN2.getY(), DELTA);
       
       p = new Coordinate<Integer>(0, 0);
	   q = new Coordinate<Integer>(100, 100);
	   theta = Math.PI/12;
	   res = (new LocateM2(p, q, theta)).out;
       expAN1 = new Coordinate<Integer>(78, 26);
	   expAN2 = new Coordinate<Integer>(32, 54);
	   assertEquals(res.getAN1().getX(), expAN1.getX(), DELTA);
	   assertEquals(res.getAN1().getY(), expAN1.getY(), DELTA);
	   assertEquals(res.getAN2().getX(), expAN2.getX(), DELTA);
	   assertEquals(res.getAN2().getY(), expAN2.getY(), DELTA);
   }
}