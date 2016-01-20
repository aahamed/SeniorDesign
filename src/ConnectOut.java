public class ConnectOut
{
	private double num;
	private double theta;
	private int pointer;
	private int[] direction;
	
	public ConnectOut(double num, double theta, int pointer, int[] direction)
	{
		this.num = num;
		this.theta = theta;
		this.pointer = pointer;
		this.direction = direction;
	}
	
	public double getNum()
	{
		return num;
	}
	
	public double getTheta()
	{
		return theta;
	}
	
	public int getPointer()
	{
		return pointer;
	}
	
	public int[] getDirection()
	{
		return direction;
	}
	
	public String toString()
	{
		return String.format("num: %.2f theta: %.2f pointer: %d  direction: [%d, %d]", num, theta, pointer, direction[0], direction[1]);
	}
}