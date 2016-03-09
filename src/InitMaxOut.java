/*
* Author: Aadil Ahamed
* InitMaxOut.java
* Description: Packages the output of InitMax.java
*/
import java.util.List;

public class InitMaxOut
{
    private List<Coordinate<Integer>> HCSList;
    private List<List<Integer>> DMatrix;
    private int[] parent;  // parent pointer representation of mst
    
    public InitMaxOut(List<Coordinate<Integer>> HCSList, List<List<Integer>> DMatrix, int[] parent)
    {
        this.HCSList = HCSList;
        this.DMatrix = DMatrix;
        this.parent = parent;
    }
    
    public List<Coordinate<Integer>> getHCSList()
    {
        return HCSList;
    }
    
    public void setHCSList(List<Coordinate<Integer>> l)
    {
        HCSList = l;
    }
    public List<List<Integer>> getDMatrix()
    {
        return DMatrix;
    }
    
    public void setDMatrix(List<List<Integer>> l)
    {
        DMatrix = l;
    }
    
    public int[] getParent()
    {
        return parent;
    }
    
    public void setParent(int[] p)
    {
        parent = p;
    }
    
}