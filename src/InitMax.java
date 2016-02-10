/*
*Author: Aadil Ahamed
*Description: Port of InitMax.m
*/

public class InitMax
{
	private InitMax(){}
	
	/*
	*reorient: Reorient Coordinates with respect to (w.r.t) Center of Mass (COM)
	*@param target target coordinate
	*@param com COM coordinate
	*@return Coordinate<Double> target coordinate w.r.t COM
	*/
	private static Coordinate<Double> reorient(Coordinate<Double> target, Coordinate<Double> com)
	{
		return new Coordinate.minus(target, com);
	}
	
	/*
	* convertToHCS: Convert all coordinates to HCS w.r.t COM
	* @param listOfCoords List of cartesian coordinates
	* @param com COM coordinate
	* @return List of HCS coordinates
	*/
	private static List<Coordinate<Integer>> convertToHCS(List<Coordinate<Double>> listOfCoords, Coordinate<Double> com)
	{
		List<Coordinate<Integer> HCSCoords = new ArrayList<Coordinate<Integer>>();
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
	private static int[][] computeDistMatrix(List<Coordinate<Integer> HCSCoords)
	{
		int DIM = HCSCoords.size();
		int[][] distMat = new int[DIM][DIM];
		for(int i = 0; i < DIM; i++)
		{
			for(int j = i+1; j < DIM; j++)
			{
				int distance = HCS.distance(HCSCoord.get(i), HCSCoord.get(j));
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
	private static void mergeDistMat(int[][] oldMat, int[][] newMat)
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
	*
	*
	*/
	public static void initMax(List<Coordinate<Double>> listOfCoords, Coordinate<Double> com, List<List<Integer>> distMat)
	{
		List<Coordinate<Integer> HCSCoords = convertToHCS(listOfCoords, com);
		
		
	}
	
}