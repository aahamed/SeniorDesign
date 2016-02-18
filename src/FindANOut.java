import java.util.Arrays;

/*
* Author: Aadil Ahamed
* Date: 1/27/16
* Description: Class to package the output of FindAN.findANm()
*/
public class FindANOut
{
	private double[][] result;
	private int flag;
	
	public FindANOut(double[][] result, int flag)
	{
		this.result = result;
		this.flag = flag;
	}
	
	public double[][] getResult()
	{
		return result;
	}

	public Coordinate<Integer> getResultC(){
		int[] temp = new int[this.result.length];
		temp[0] = (int)this.result[0][0];
		temp[1] = (int)this.result[1][0];
		//System.out.print(Arrays.toString(temp));
		return new Coordinate<Integer>(temp[0],temp[1]);
		
	}
	
	public int getFlag()
	{
		return flag;
	}
	
	public void setResult(double[][] result)
	{
		this.result = result;
	}
	
	public void setFlag(int flag)
	{
		this.flag = flag;
	}
}
