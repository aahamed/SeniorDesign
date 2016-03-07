/*
*Author: Aadil Ahamed
*Description: Port of InitMax.m
*/

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class InitMax
{
	private static boolean D = false; //Debug flag
	private InitMax(){}
	
	/*
	*reorient: Reorient Coordinates with respect to (w.r.t) Center of Mass (COM)
	*@param target target coordinate
	*@param com COM coordinate
	*@return Coordinate<Double> target coordinate w.r.t COM
	*/
	private static Coordinate<Double> reorient(Coordinate<Double> target, Coordinate<Double> com)
	{
		return (Coordinate.minus(target, com));
	}
	
	/*
	* convertToHCS: Convert all coordinates to HCS w.r.t COM
	* @param listOfCoords List of cartesian coordinates
	* @param com COM coordinate
	* @return List of HCS coordinates
	*/
	public static List<Coordinate<Integer>> convertToHCS(List<Coordinate<Double>> listOfCoords, Coordinate<Double> com)
	{
		List<Coordinate<Integer>> HCSCoords = new ArrayList<Coordinate<Integer>>();
		for(Coordinate<Double> c: listOfCoords)
		{
			HCSCoords.add(HCS.cartToHex(reorient(c, com)));
		}
		return HCSCoords;
	}
	
	/*
	* computeDistMatrix: Computes the distance between every pair of coordinates
	* @param HCSCoords List of all coordinates in HCS
	* @return Distance Matrix
	*/
	public static int[][] computeDistMatrix(List<Coordinate<Integer>> HCSCoords)
	{
		int DIM = HCSCoords.size();
		int[][] distMat = new int[DIM][DIM];
		for(int i = 0; i < DIM; i++)
		{
			distMat[i][i] = 0;
			for(int j = i+1; j < DIM; j++)
			{
				int distance = HCS.distance(HCSCoords.get(i), HCSCoords.get(j));
				if(D)
				{
					System.out.println("coord i: " + HCSCoords.get(i) + " coord j: " + HCSCoords.get(j));
					System.out.println("i = " + i + "  j = " + j + "  distance = " + distance);
				}
				distMat[i][j] = distance;
				distMat[j][i] = distance;
			}
		}
		return distMat;
	}
	
	/*
	*mergeDistMat: Merge new distance matrix with old distance matrix in order to prevent picking nodes that are already connected
	*@param oldMat old distance matrix
	*@param newMat new distance matrix
	*@return new distance matrix
	*/
	public static int[][] mergeDistMat(int[][] oldMat, int[][] newMat)
	{
		for(int i = 0; i < oldMat.length; i++)
		{
			for(int j = 0; j < oldMat[0].length; j++)
			{
				if(oldMat[i][j] == GlobalConstants.H)
				{
					newMat[i][j] = GlobalConstants.H;
				}
			}
		}
		return newMat;
	}
	
	/*
	* initMax: Port of Initate_MAX.m
	* @param listOfCoords list of coordinates
	* @param com center of mass
	* @param oldDistMat old distance Matrix
	* @return minimum spanning tree
	*/
	public static int[] initMax(List<Coordinate<Double>> listOfCoords, Coordinate<Double> com, int[][] oldDistMat)
	{
		List<Coordinate<Integer>> HCSCoords = convertToHCS(listOfCoords, com);
		int[][] newDistMat = InitMax.computeDistMatrix(HCSCoords);
		InitMax.mergeDistMat(oldDistMat, newDistMat);
		if(D)
		{
			for(int i = 0; i < newDistMat.length; i++)
			{
				System.out.println(Arrays.toString(newDistMat[i]));
			}
		}
		int[] mst = PrimMST.primMST(newDistMat);
		PrimMST.printMST(mst, newDistMat[0].length, newDistMat);   //debug code - comment out
		return mst;
	}
	
	public static void test1()
	{
		List<Coordinate<Double>> listOfCoords = new ArrayList<Coordinate<Double>>();
		listOfCoords.add(new Coordinate<Double>(1.0, 3.0));
		listOfCoords.add(new Coordinate<Double>(20.0, 20.0));
		listOfCoords.add(new Coordinate<Double>(70.0, 5.0));
		Coordinate<Double> com = new Coordinate<Double>(0.0, 0.0);
		int[][] oldDistMat = new int[][]{{2, 2}, {2, 2}};
		int[] mst = InitMax.initMax(listOfCoords, com, oldDistMat);
	}
	
	public static void main(String[] args)
	{
		InitMax.test1();
	}
	
}
