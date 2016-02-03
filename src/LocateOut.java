/*
* Author: Ezana Woldegabriel
* Description: Class to package the output of locate
*/
public class LocateOut {
	Coordinate<Integer> AN;
	int exflag;
	
	public LocateOut(){}
	
	public Coordinate<Integer> getAN(){
		return this.AN;
	}
	
	public void setAN(Coordinate<Integer> a){
		this.AN = a;
	}
	
	public int getexFlag(){
		return this.exflag;
	}
	
	public void setexFlag(int b){
		this.exflag = b;
	}
}
