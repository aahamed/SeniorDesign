/*
* Author: Ezana Woldegabriel
* Description: Port of locate.m
*/

import java.util.Arrays;

import Jama.Matrix;

public class Locate {
	double[][] i;
	double[][] j,k,d;
	Matrix i1 = new Matrix(i);
	Matrix j1 = new Matrix(j);
	Matrix k1 = new Matrix(k);
	Matrix d1 = new Matrix(d);
	LocateOut out = new LocateOut();
	
	public Locate(Matrix p, Matrix q, int H){
		i = new double[][]{{1,0},{0,0}};
		j = new double[][]{{0,1},{0,0}};
		k = new double[][]{{-1,1},{0,0}};
		d = new double[][]{{(double) (q.get(0, 0)-p.get(0, 0)),(int) (q.get(1, 0)-p.get(1, 0))},{0,0}};
		i1 = new Matrix(i);
		j1 = new Matrix(j);
		k1 = new Matrix(k);
		d1 = new Matrix(d);
		case1(p,q,H);
	}
	private void case1(Matrix p, Matrix q, int H){
		if(this.d[0][0] > 0 && this.d[0][1]>=0){
			Coordinate<Integer> I = new Coordinate<Integer>(0, 0);
			Matrix J,K;
			J = p.plus(j1.times((double)H));
			K = q.plus(k1.times((double)H));
			
			int jtemp1 = (int)J.get(0, 0);
			int jtemp2 = (int)J.get(0, 1);
			Coordinate<Integer> J1 = new Coordinate<Integer>(jtemp1,jtemp2);
			
			int ktemp1 = (int)K.get(0, 0);
			int ktemp2 = (int)K.get(0, 1);
			Coordinate<Integer> K1 = new Coordinate<Integer>(ktemp1,ktemp2);
			
			int ptemp1 = (int)p.get(0, 0);
			int ptemp2 = (int)p.get(0, 1);
			Coordinate<Integer> p1 = new Coordinate<Integer>(ptemp1,ptemp2);
			
			int qtemp1 = (int)q.get(0, 0);
			int qtemp2 = (int)q.get(0, 1);
			Coordinate<Integer> q1 = new Coordinate<Integer>(qtemp1,qtemp2);
			
			
			double Ind[][] = {{0,1,1},{0,0,0}};
			int[] Ind1 = new int[Ind[0].length];
			for(int n=0;n<Ind[0].length;n++)
				Ind1[n] = (int)Ind[0][n];

			Matrix Ind2 = new Matrix(Ind);
			FindANOut f = FindAN.findANm(I, J1, K1, Ind1 );
			
			if(f.getFlag()==1){
				ConnectOut cout1 = Connect.connect(p1,f.getResultC(), 'c');
				ConnectOut cout2 = Connect.connect(f.getResultC(), q1, 'c');
				
				if((cout1.getPointer()!=0)&&(cout2.getPointer()!=0)){
					out.setAN(f.getResultC());
					out.setexFlag(1);
				}
			}
		}
	}
}
