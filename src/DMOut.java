/*
 * Author: Josue Galeas
 * Last Edit: Mar 28, 2016
 * Description: TODO
 */

import java.util.ArrayList;
import java.util.List;

public class DMOut
{
	private List<List<Integer>> DM;
	private List<List<Integer>> SP0M;
	private List<List<Integer>> XM;
	private List<List<Integer>> wM;

	public DMOut(List<List<Integer>> DM, List<List<Integer>> SP0M, List<List<Integer>> XM, List<List<Integer>> wM)
	{
		this.DM = DM;
		this.SP0M = SP0M;
		this.XM = XM;
		this.wM = wM;
	}

	public List<List<Integer>> getDM()
	{
		return DM;
	}

	public List<List<Integer>> getSP0M()
	{
		return SP0M;
	}

	public List<List<Integer>> getXM()
	{
		return XM;
	}

	public List<List<Integer>> getwM()
	{
		return wM;
	}
}
