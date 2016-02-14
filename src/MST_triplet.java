/*
 * Author: Josue Galeas
 * Last Edit: Feb 13, 2016
 * Description: TODO
 */

import java.util.ArrayList;
import java.util.List;

public class MST_triplet
{
	private int w_st;
	private List<Coordinate<Integer>> ST = new ArrayList<Coordinate<Integer>>();
	private List<List<Integer>> X_st = new ArrayList<List<Integer>>();

	public MST_triplet(int a, List<Coordinate<Integer>> b, List<List<Integer>> c)
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
}
