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