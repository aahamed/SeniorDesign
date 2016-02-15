/*
 * Author: Josue Galeas
 * Last Edit: Feb 14, 2016
 * Description: Object that will contain the three data structures that the MST algorithm produces.
 */

import java.util.ArrayList;
import java.util.List;

public class MST
{
	private int w_st;
	private List<Coordinate<Integer>> ST = new ArrayList<Coordinate<Integer>>();
	private List<List<Integer>> X_st = new ArrayList<List<Integer>>();

	public MST(int a, List<Coordinate<Integer>> b, List<List<Integer>> c)
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

	public List<List<Integer>> getXst()
	{
		return X_st;
	}

	public int[][] getSTarray()
	{
		return List_ops.lc2array(ST);
	}

	public int[][] getXstarray()
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

	public void setXst(List<List<Integer>> a)
	{
		X_st = a;
	}

	public void setSTarray(int[][] a)
	{
		ST = List_ops.array2lc(a);
	}

	public void setXstarray(int[][] a)
	{
		X_st = List_ops.array2ll(a);
	}

	public void printAll()
	{
		System.out.println(">> w_st is:");
		System.out.println(w_st);
		System.out.println(">> ST is:");
		List_ops.print_coordlist_int(ST);
		System.out.println(">> X_st is:");
		List_ops.print_matrix(X_st);
	}
}
