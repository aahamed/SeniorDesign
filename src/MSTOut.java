/*
 * Author: Josue Galeas
 * Last Edit: Mar 1, 2016
 * Description: Object that will contain the three data structures that the MST algorithm produces.
 */

import java.util.ArrayList;
import java.util.List;

public class MSTOut
{
	private int w_st;
	private List<Coordinate<Integer>> ST;
	private List<List<Integer>> X_st;

	public MSTOut(int a, List<Coordinate<Integer>> b, List<List<Integer>> c)
	{
		w_st = a;
		ST = b;
		X_st = c;
	}

	public int getwst()
	{
		return w_st;
	}

	public List<Coordinate<Integer>> getST()
	{
		return ST;
	}

	public int[][] getSTArray()
	{
		return List_ops.lc2array(ST);
	}

	public List<List<Integer>> getXst()
	{
		return X_st;
	}

	public int[][] getXstArray()
	{
		return List_ops.ll2array(X_st);
	}

	public void setwst(int a)
	{
		w_st = a;
	}

	public void setST(List<Coordinate<Integer>> a)
	{
		ST = a;
	}

	public void setSTArray(int[][] a)
	{
		ST = List_ops.array2lc(a);
	}

	public void setXst(List<List<Integer>> a)
	{
		X_st = a;
	}

	public void setXstArray(int[][] a)
	{
		X_st = List_ops.array2ll(a);
	}

	public void printwst()
	{
		System.out.println(">> w_st is:");
		System.out.println(w_st);
	}

	public void printST()
	{
		System.out.println(">> ST is:");
		List_ops.print_coordlist_int(ST);
	}

	public void printXst()
	{
		System.out.println(">> X_st is:");
		List_ops.print_matrix(X_st);
	}

	public void printAll()
	{
		printwst();
		printST();
		printXst();
	}
}
