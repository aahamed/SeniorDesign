/*
*Author: Aadil Ahamed
*Date: 1/25/16
*Description: Port of FindAN1.m and FindAN2.m
*/

import java.util.Arrays;
import Jama.Matrix;

public class FindAN
{
	private static final int[] CASE1 = {-1, 1, 0};
	private static final int[] CASE2 = {1, -1, 0};
	private static final int[] CASE3 = {0, 1, 1};
	private static final int[] CASE4 = {0, -1, -1};
	private static final double THRESHOLD = 1E-6;
	
	private FindAN(){}
	
	private static boolean perturb(double[][] result)
	{
		int flag = 0;
		if( (Math.abs( Math.round(result[0][0]) - result[0][0] ) < FindAN.THRESHOLD) && 
					(Math.abs( Math.round(result[1][0]) - result[2][0] ) < FindAN.THRESHOLD))
		{
			return false;
		}
		return true;
	}
	
	private static int computeFlag(Matrix A, Matrix B, Matrix C, Matrix D)
	{
		int flag = 0;
		double[][] result = (A.solve(B)).getArray();
		if(FindAN.perturb(result))
		{
			flag = 1;
			return flag;
		}
		else
		{
			Matrix BplusC = B.plus(C);
			result = (A.solve(BplusC)).getArray();
			if(FindAN.perturb(result))
			{
				flag = 1;
				return flag;
			}
			else
			{
				Matrix BplusD = B.plus(D);
				result = (A.solve(BplusD)).getArray();
				if(FindAN.perturb(result))
				{
					flag = 1;
					return flag;
				}
				else
				{
					flag = 0;
					return flag;
				}
			}
		}
	}
	
	
	private static int case1(Coordinate<Integer> I, Coordinate<Integer> J)
	{
		double[][] A_ji = {{0.5, 1}, {1, 0,5}};
		double[][] b_ji = {{0.5*J.getX() +J.getY()},{I.getX() + (0.5*I.getY())}};
		Matrix mat_A_ji = new Matrix(A_ji);
		Matrix mat_b_ji = new Matrix(b_ji);
		Matrix mat_b_yk = new Matrix(new double[][]{{-0.5},{0}}); 
		Matrix mat_b_xK	 = new Matrix(new double[][]{{0},{0.5}});
		return FindAN.computeFlag(mat_A_ji, mat_b_ji, mat_b_yk, mat_b_xK); 
	}
	
	private static int case2(Coordinate<Integer> I, Coordinate<Integer> J)
	{
		double[][] A_ji = {{0.5, 1}, {1, 0,5}};
		double[][] b_ji = {{0.5*J.getX() +J.getY()},{I.getX() + (0.5*I.getY())}};
		Matrix mat_A_ji = new Matrix(A_ji);
		Matrix mat_b_ji = new Matrix(b_ji);
		Matrix mat_b_yk = new Matrix(new double[][]{{0.5},{0}}); 
		Matrix mat_b_xK	 = new Matrix(new double[][]{{0},{-0.5}});
		return FindAN.computeFlag(mat_A_ji, mat_b_ji, mat_b_yk, mat_b_xK); 
	}
	
	private static int case3(Coordinate<Integer> J, Coordinate<Integer> K)
	{
		double[][] A_jk = {{0.5, 1}, {-0.5, 0,5}};
		double[][] b_jk = {{0.5*J.getX() +J.getY()},{(-0.5*K.getX()) + (0.5*K.getY())}};
		Matrix mat_A_jk = new Matrix(A_jk);
		Matrix mat_b_jk = new Matrix(b_jk);
		Matrix mat_b_xI = new Matrix(new double[][]{{-0.5},{0}}); 
		Matrix mat_b_kI	 = new Matrix(new double[][]{{0},{-1}});
		return FindAN.computeFlag(mat_A_jk, mat_b_jk, mat_b_xI, mat_b_kI); 
	}
	
	private static int case4(Coordinate<Integer> J, Coordinate<Integer> K)
	{
		double[][] A_kj = {{0.5, 1}, {-0.5, 0,5}};
		double[][] b_kj = {{0.5*J.getX() +J.getY()},{(-0.5*K.getX()) + (0.5*K.getY())}};
		Matrix mat_A_kj = new Matrix(A_kj);
		Matrix mat_b_kj = new Matrix(b_kj);
		Matrix mat_b_xI = new Matrix(new double[][]{{0},{0.5}}); 
		Matrix mat_b_kI	 = new Matrix(new double[][]{{0.5},{0.5}});
		return FindAN.computeFlag(mat_A_kj, mat_b_kj, mat_b_xI, mat_b_kI); 
	}
	
	/*
	* Port of FindAN1.m
	*/
	public static void findAN1(Coordinate<Integer> I, Coordinate<Integer> J, Coordinate<Integer> K, int[] indicator)
	{
		int flag = 0;
		if(Arrays.equals(indicator, FindAN.CASE1))
		{
			flag = FindAN.case1(I, J);
		}
		else if(Arrays.equals(indicator, FindAN.CASE2))
		{
			flag = FindAN.case2(I, J);
		}
		else if(Arrays.equals(indicator, FindAN.CASE3))
		{
			flag = FindAN.case3(J, K);
		}
		else if(Arrays.equals(indicator, FindAN.CASE4))
		{
			flag = FindAN.case4(J, K);
		}
	}
}