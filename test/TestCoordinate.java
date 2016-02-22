import org.junit.Test;
import static org.junit.Assert.*;

public class TestCoordinate
{
    private static final double DELTA = 1e-15;
	
	@Test
	public void testEquals()
	{
		//System.out.println("TEST EQUALS");
		Coordinate<Integer> p = new Coordinate<Integer>(2, 3);
		Coordinate<Integer> q = new Coordinate<Integer>(2, 3);
		assertTrue(p.equals(q));
		q = new Coordinate<Integer>(2, 5);
		assertFalse(p.equals(q));
		q = null;
		assertFalse(p.equals(q));
	}
   
	@Test
	public void testIntAdd()
	{
		//System.out.println("ADD TEST");
		Coordinate<Integer> p;
		Coordinate<Integer> q;
		Coordinate<Integer> result;
		Coordinate<Integer> exp;
		p = new Coordinate<Integer>(4, 5);
		q = new Coordinate<Integer>(3, 3);
		result = Coordinate.add(p, q);
		exp = new Coordinate<Integer>(7, 8);
		assertTrue(result.equals(exp));
		p = new Coordinate<Integer>(3, 5);
		q = new Coordinate<Integer>(3, 4);
		result = Coordinate.add(p, q);
		exp = new Coordinate<Integer>(7, 8);
		assertFalse(result.equals(exp));
	}
	
	@Test
	public void testDoubMinus()
	{
		//System.out.println("MINUS TEST");
		Coordinate<Double> p;
		Coordinate<Double> q;
		Coordinate<Double> result;
		Coordinate<Double> exp;
		p = new Coordinate<Double>(4.0, 5.0);
		q = new Coordinate<Double>(3.0, 3.0);
		result = Coordinate.minus(p, q);
		exp = new Coordinate<Double>(1.0, 2.0);
		//System.out.println("res: " + result + " exp: " + exp);
		assertEquals(result.getX(), exp.getX(), DELTA);
		assertEquals(result.getY(), exp.getY(), DELTA);
		p = new Coordinate<Double>(4.0, 5.0);
		q = new Coordinate<Double>(7.0, 3.0);
		result = Coordinate.minus(p, q);
		exp = new Coordinate<Double>(1.0, 3.0);
		assertFalse(result.equals(exp));
	}
}