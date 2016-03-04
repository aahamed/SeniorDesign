/*
* Author: Ezana Woldegebriel
* Description: Port of locate1.m
*/

import java.util.Arrays;

import Jama.Matrix;

public class Locate1 {
	double[][] i;
	double[][] j,k,d;
	Matrix i1;
	Matrix j1;
	Matrix k1;
	Matrix d1;
	LocateOut out = new LocateOut();
	boolean c1return=false,c2return=false,c3return=false,c4return=false;
	
	public static void main(String[] args) {
        double[][] p1 = {{-181,-94},{0,0}};
        double[][] q1 = {{-299,66},{0,0}};
        Matrix p = new Matrix(p1);
        Matrix q = new Matrix(q1);
        int H = 91;
        Locate1 l = new Locate1(p,q,H);
        System.out.println("Coordinate: "+l.out.getAN().getX()+" , "+l.out.getAN().getY());
    }
	
	public Locate1(Matrix p, Matrix q, int H){
		i = new double[][]{{1,0},{0,0}};
		j = new double[][]{{0,1},{0,0}};
		k = new double[][]{{-1,1},{0,0}};
		d = new double[][]{{(q.get(0, 0)-p.get(0, 0)),(q.get(0, 1)-p.get(0, 1))},{0,0}};
		System.out.println(d[0][0]+" " +d[0][1]);
		i1 = new Matrix(i);
		j1 = new Matrix(j);
		k1 = new Matrix(k);
		d1 = new Matrix(d);
		case1(p,q,H);
		case2(p,q,H);
		case3(p,q,H);
		case4(p,q,H);
	}
	private void case1(Matrix p, Matrix q, int H){
		if(this.d[0][0] > 0 && this.d[0][1]>=0){
			//C2: 
			case1C2(p,q,H);
			if(c1return)
				return;
			//C3: Try {Z+X-} This is special in Quandrant 1, we have to employ <FindAN2>
			case1C3(p,q,H);
			if(c1return)
				return;
			//C1: Try p{X+} and q{Y-}
			case1C1(p,q,H);
			if(c1return)
				return;
		}
	}
	
	private void case2(Matrix p, Matrix q, int H){
		//WHEN In Quadrant 3; The same as in Quadrant 1, just switch p and q
		//on X axis, treat as In Quadrant 1
		if(this.d[0][0]<0 && this.d[0][1]<=0){
			Matrix c = p;
			p = q;
			q = c;
			case1(p,q,H);
		}
	}
	
	private void case3(Matrix p, Matrix q, int H){
		if(this.d[0][0] <= 0 && this.d[0][1]>0){
			//%% C1: Try p{Z+}, q{X-}
			case3C1(p,q,H);
			if(c3return)
				return;
			//%% C3 : {X-Y-}
			case3C3(p,q,H);
			if(c3return)
				return;
			//%% C5:  Try {Y-Z-}
			case3C5(p,q,H);
			if(c3return)
				return;
			//%% C2: {Z+Y-}
	        //   % This one we can't leave margin
			case3C2(p,q,H);
			if(c3return)
				return;
			//%% C4: {X-Z-};
	        //% This one we are unable to leave margin
			case3C4(p,q,H);
			if(c3return)
				return;
		}
	}
	
	private void case4(Matrix p, Matrix q, int H){
		if(this.d[0][0] >= 0 && this.d[0][1]<0){
			Matrix c = p;
			p=q;
			q=c;
			d = new double[][]{{(q.get(0, 0)-p.get(0, 0)),(q.get(0, 1)-p.get(0, 1))},{0,0}};
			System.out.println(d[0][0]+" " +d[0][1]);
			case1(p,q,H);
			case2(p,q,H);
			case3(p,q,H);
			//case4(p,q,H);
		}
	}
	
	private void case1C2(Matrix p, Matrix q, int H){
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
				//Leave Margin
				int distance1 = HCS.distance(f.getResultC(), p1);
				int distance2 = HCS.distance(f.getResultC(), q1);
				if((distance1 >= GlobalConstants.H + 4*GlobalConstants.NL)&&(distance2 >= GlobalConstants.H + 4*GlobalConstants.NL)){
					Coordinate<Integer> tempcoord = new Coordinate<Integer>(f.getResultC().getX()+(-2)*(int)j1.get(0,0)+
																			(-2)*(int)k1.get(0,0),f.getResultC().getY()+(-2)*(int)j1.get(0,1)+(-2)*(int)k1.get(0,1));
					out.setAN(tempcoord);
					c1return=true;
					return;
				}
				if(distance1 >= GlobalConstants.H + 4*GlobalConstants.NL){
					Coordinate<Integer> tempcoord = new Coordinate<Integer>(f.getResultC().getX()+(-2)*(int)j1.get(0,0),f.getResultC().getY()
																			+(-2)*(int)j1.get(0,1));
					out.setAN(tempcoord);
					c1return=true;
					return;
				}
				if(distance2 >= GlobalConstants.H + 4*GlobalConstants.NL){
					Coordinate<Integer> tempcoord = new Coordinate<Integer>(f.getResultC().getX()+(-2)*(int)k1.get(0,0),f.getResultC().getY()
																			+(-2)*(int)k1.get(0,1));
					out.setAN(tempcoord);
					c1return=true;
					return;
				}
			}
		}

	}
	
	private void case1C3(Matrix p, Matrix q, int H){
		Coordinate<Integer> J = new Coordinate<Integer>(0, 0);
		Matrix I,K;
		I = p.plus(i1.times((double)H));
		K = q.plus(k1.times((double)-H));
		
		int itemp1 = (int)I.get(0, 0);
		int itemp2 = (int)I.get(0, 1);
		Coordinate<Integer> I1 = new Coordinate<Integer>(itemp1,itemp2);
		
		int ktemp1 = (int)K.get(0, 0);
		int ktemp2 = (int)K.get(0, 1);
		Coordinate<Integer> K1 = new Coordinate<Integer>(ktemp1,ktemp2);
		
		int ptemp1 = (int)p.get(0, 0);
		int ptemp2 = (int)p.get(0, 1);
		Coordinate<Integer> p1 = new Coordinate<Integer>(ptemp1,ptemp2);
		
		int qtemp1 = (int)q.get(0, 0);
		int qtemp2 = (int)q.get(0, 1);
		Coordinate<Integer> q1 = new Coordinate<Integer>(qtemp1,qtemp2);
		
		
		double Ind[][] = {{1,0,-1},{0,0,0}};
		int[] Ind1 = new int[Ind[0].length];
		for(int n=0;n<Ind[0].length;n++)
			Ind1[n] = (int)Ind[0][n];

		Matrix Ind2 = new Matrix(Ind);
		FindANOut f = FindAN.findANm(I1, J, K1, Ind1 );
		
		if(f.getFlag()==1){
			ConnectOut cout1 = Connect.connect(p1,f.getResultC(), 'c');
			ConnectOut cout2 = Connect.connect(f.getResultC(), q1, 'c');
			
			if((cout1.getPointer()!=0)&&(cout2.getPointer()!=0)){
				out.setAN(f.getResultC());
				out.setexFlag(1);
				//Leave Margin
				int distance1 = HCS.distance(f.getResultC(), p1);
				int distance2 = HCS.distance(f.getResultC(), q1);
				
				if((distance1 >= GlobalConstants.H + 4*GlobalConstants.NL)&&(distance2 >= GlobalConstants.H + 4*GlobalConstants.NL)){
					Coordinate<Integer> tempcoord = new Coordinate<Integer>(f.getResultC().getX()+(2)*(int)k1.get(0,0)+(-2)*(int)i1.get(0,0),f.getResultC().getY()+(2)*(int)k1.get(0,1)+(-2)*(int)i1.get(0,1));
					out.setAN(tempcoord);
					c1return=true;
					return;
				}
				if(distance1 >= GlobalConstants.H + 4*GlobalConstants.NL){
					Coordinate<Integer> tempcoord = new Coordinate<Integer>(f.getResultC().getX()+(-2)*(int)i1.get(0,0),f.getResultC().getY()+(-2)*(int)i1.get(0,1));
					out.setAN(tempcoord);
					c1return=true;
					return;
				}
				if(distance2 >= GlobalConstants.H + 4*GlobalConstants.NL){
					Coordinate<Integer> tempcoord = new Coordinate<Integer>(f.getResultC().getX()+2*(int)k1.get(0,0),f.getResultC().getY()+2*(int)k1.get(0,1));
					out.setAN(tempcoord);
					c1return=true;
					return;
				}
			}
		}

	}
	
	private void case1C1(Matrix p, Matrix q, int H){
		Coordinate<Integer> K = new Coordinate<Integer>(0, 0);
		Matrix I,J;
		I = q.plus(i1.times((double)-H));
		J = p.plus(j1.times((double)H));
		
		int itemp1 = (int)I.get(0, 0);
		int itemp2 = (int)I.get(0, 1);
		Coordinate<Integer> I1 = new Coordinate<Integer>(itemp1,itemp2);
		
		int jtemp1 = (int)J.get(0, 0);
		int jtemp2 = (int)J.get(0, 1);
		Coordinate<Integer> J1 = new Coordinate<Integer>(jtemp1,jtemp2);
		
		int ptemp1 = (int)p.get(0, 0);
		int ptemp2 = (int)p.get(0, 1);
		Coordinate<Integer> p1 = new Coordinate<Integer>(ptemp1,ptemp2);
		
		int qtemp1 = (int)q.get(0, 0);
		int qtemp2 = (int)q.get(0, 1);
		Coordinate<Integer> q1 = new Coordinate<Integer>(qtemp1,qtemp2);
		
		
		double Ind[][] = {{-1,1,0},{0,0,0}};
		int[] Ind1 = new int[Ind[0].length];
		for(int n=0;n<Ind[0].length;n++)
			Ind1[n] = (int)Ind[0][n];

		Matrix Ind2 = new Matrix(Ind);
		FindANOut f = FindAN.findANm(I1, J1, K, Ind1 );
		
		//Solution exists and pos connect with p and q 
		if(f.getFlag()==1){
			ConnectOut cout1 = Connect.connect(p1,f.getResultC(), 'c');
			ConnectOut cout2 = Connect.connect(f.getResultC(), q1, 'c');
			
			if((cout1.getPointer()!=0)&&(cout2.getPointer()!=0)){
				out.setAN(f.getResultC());
				out.setexFlag(1);
				c1return=true;
				return;
			}
			else{
				out.setexFlag(0);
				//AN = [];
				c1return=true;
				return;
			}
		}
		else{
			out.setexFlag(0);
			//AN = [];
		}
		if(f.getFlag()+out.exflag==2){
			System.out.println("No Solution");
			out.setexFlag(0);
		}

	}
	
	private void case3C1(Matrix p, Matrix q, int H){
		Coordinate<Integer> J = new Coordinate<Integer>(0, 0);
		Matrix I,K;
		I = q.plus(i1.times((double)H));
		K = p.plus(k1.times((double)-H));
		
		int itemp1 = (int)I.get(0, 0);
		int itemp2 = (int)I.get(0, 1);
		Coordinate<Integer> I1 = new Coordinate<Integer>(itemp1,itemp2);
		
		int ktemp1 = (int)K.get(0, 0);
		int ktemp2 = (int)K.get(0, 1);
		Coordinate<Integer> K1 = new Coordinate<Integer>(ktemp1,ktemp2);
		
		int ptemp1 = (int)p.get(0, 0);
		int ptemp2 = (int)p.get(0, 1);
		Coordinate<Integer> p1 = new Coordinate<Integer>(ptemp1,ptemp2);
		
		int qtemp1 = (int)q.get(0, 0);
		int qtemp2 = (int)q.get(0, 1);
		Coordinate<Integer> q1 = new Coordinate<Integer>(qtemp1,qtemp2);
		
		
		double Ind[][] = {{1,0,-1},{0,0,0}};
		int[] Ind1 = new int[Ind[0].length];
		for(int n=0;n<Ind[0].length;n++)
			Ind1[n] = (int)Ind[0][n];

		Matrix Ind2 = new Matrix(Ind);
		FindANOut f = FindAN.findANm(I1, J, K1, Ind1 );
		
		if(f.getFlag()==1){
			ConnectOut cout1 = Connect.connect(p1,f.getResultC(), 'c');
			ConnectOut cout2 = Connect.connect(f.getResultC(), q1, 'c');
			
			if((cout1.getPointer()!=0)&&(cout2.getPointer()!=0)){
				out.setAN(f.getResultC());
				out.setexFlag(1);
				//Leave Margin
				int distance1 = HCS.distance(f.getResultC(), p1);
				int distance2 = HCS.distance(f.getResultC(), q1);
				
				if((distance1 >= GlobalConstants.H + 4*GlobalConstants.NL)&&(distance2 >= GlobalConstants.H + 4*GlobalConstants.NL)){
					Coordinate<Integer> tempcoord = new Coordinate<Integer>(f.getResultC().getX()+(2)*(int)k1.get(0,0)+(-2)*((int)i1.get(0,0)),f.getResultC().getY()+(2)*(int)k1.get(0,1)+(-2)*(int)i1.get(0,1));
					out.setAN(tempcoord);
					c3return=true;
					return;
				}
				if(distance1 >= GlobalConstants.H + 4*GlobalConstants.NL){
					Coordinate<Integer> tempcoord = new Coordinate<Integer>(f.getResultC().getX()+(-2)*(int)i1.get(0,0),f.getResultC().getY()+(-2)*(int)i1.get(0,1));
					out.setAN(tempcoord);
					c3return=true;
					return;
				}
				if(distance2 >= GlobalConstants.H + 4*GlobalConstants.NL){
					Coordinate<Integer> tempcoord = new Coordinate<Integer>(f.getResultC().getX()+2*((int)k1.get(0,0)),f.getResultC().getY()+2*(int)k1.get(0,1));
					out.setAN(tempcoord);
					c3return=true;
					return;
				}
			}
		}

	}
	
	private void case3C3(Matrix p, Matrix q, int H){
		Coordinate<Integer> K = new Coordinate<Integer>(0, 0);
		Matrix I,J;
		I = q.plus(i1.times((double)H));
		J = p.plus(j1.times((double)H));
		
		int itemp1 = (int)I.get(0, 0);
		int itemp2 = (int)I.get(0, 1);
		Coordinate<Integer> I1 = new Coordinate<Integer>(itemp1,itemp2);
		
		int jtemp1 = (int)J.get(0, 0);
		int jtemp2 = (int)J.get(0, 1);
		Coordinate<Integer> J1 = new Coordinate<Integer>(jtemp1,jtemp2);
		
		int ptemp1 = (int)p.get(0, 0);
		int ptemp2 = (int)p.get(0, 1);
		Coordinate<Integer> p1 = new Coordinate<Integer>(ptemp1,ptemp2);
		
		int qtemp1 = (int)q.get(0, 0);
		int qtemp2 = (int)q.get(0, 1);
		Coordinate<Integer> q1 = new Coordinate<Integer>(qtemp1,qtemp2);
		
		
		double Ind[][] = {{1,1,0},{0,0,0}};
		int[] Ind1 = new int[Ind[0].length];
		for(int n=0;n<Ind[0].length;n++)
			Ind1[n] = (int)Ind[0][n];

		Matrix Ind2 = new Matrix(Ind);
		FindANOut f = FindAN.findANm(I1, J1, K, Ind1 );
		
		//Solution exists and pos connect with p and q 
		if(f.getFlag()==1){
			ConnectOut cout1 = Connect.connect(p1,f.getResultC(), 'c');
			ConnectOut cout2 = Connect.connect(f.getResultC(), q1, 'c');
			
			if((cout1.getPointer()!=0)&&(cout2.getPointer()!=0)){
				out.setAN(f.getResultC());
				out.setexFlag(1);
				//Leave Margin
				int distance1 = HCS.distance(f.getResultC(), p1);
				int distance2 = HCS.distance(f.getResultC(), q1);
				
				if((distance1 >= GlobalConstants.H + 4*GlobalConstants.NL)&&(distance2 >= GlobalConstants.H + 4*GlobalConstants.NL)){
					Coordinate<Integer> tempcoord = new Coordinate<Integer>(f.getResultC().getX()+(-2)*(int)i1.get(0,0)+(-2)*(int)j1.get(0,0),f.getResultC().getY()+(-2)*(int)i1.get(0,1)+(-2)*(int)j1.get(0,1));
					out.setAN(tempcoord);
					c3return=true;
					return;
				}
				if(distance1 >= GlobalConstants.H + 4*GlobalConstants.NL){
					Coordinate<Integer> tempcoord = new Coordinate<Integer>(f.getResultC().getX()+(-2)*(int)j1.get(0,0),f.getResultC().getY()+(-2)*(int)j1.get(0,1));
					out.setAN(tempcoord);
					c3return=true;
					return;
				}
				if(distance2 >= GlobalConstants.H + 4*GlobalConstants.NL){
					Coordinate<Integer> tempcoord = new Coordinate<Integer>(f.getResultC().getX()+(-2)*(int)i1.get(0,0),f.getResultC().getY()+(-2)*(int)i1.get(0,1));
					out.setAN(tempcoord);
					c3return=true;
					return;
				}
			}
		}

	}

	private void case3C5(Matrix p, Matrix q, int H){
		Coordinate<Integer> I = new Coordinate<Integer>(0, 0);
		Matrix J,K;
		J = q.plus(j1.times((double)H));
		K = p.plus(k1.times((double)H));
		
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
				//Leave Margin
				int distance1 = HCS.distance(f.getResultC(), p1);
				int distance2 = HCS.distance(f.getResultC(), q1);
				if((distance1 >= GlobalConstants.H + 4*GlobalConstants.NL)&&(distance2 >= GlobalConstants.H + 4*GlobalConstants.NL)){
					Coordinate<Integer> tempcoord = new Coordinate<Integer>(f.getResultC().getX()+(-2)*(int)j1.get(0,0)+
																			(-2)*(int)k1.get(0,0),f.getResultC().getY()+(-2)*(int)j1.get(0,1)+(-2)*(int)k1.get(0,1));
					out.setAN(tempcoord);
					c3return=true;
					return;
				}
				if(distance1 >= GlobalConstants.H + 4*GlobalConstants.NL){
					Coordinate<Integer> tempcoord = new Coordinate<Integer>(f.getResultC().getX()+(-2)*(int)k1.get(0,0),f.getResultC().getY()
																			+(-2)*(int)k1.get(0,1));
					out.setAN(tempcoord);
					c3return=true;
					return;
				}
				if(distance2 >= GlobalConstants.H + 4*GlobalConstants.NL){
					Coordinate<Integer> tempcoord = new Coordinate<Integer>(f.getResultC().getX()+(-2)*(int)j1.get(0,0),f.getResultC().getY()
																			+(-2)*(int)j1.get(0,1));
					out.setAN(tempcoord);
					c3return=true;
					return;
				}
			}
		}

	}
	
	private void case3C2(Matrix p, Matrix q, int H){
		Coordinate<Integer> I = new Coordinate<Integer>(0, 0);
		Matrix J,K;
		J = p.plus(j1.times((double)H));
		K = q.plus(k1.times((double)-H));
		
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
		
		
		double Ind[][] = {{0,1,-1},{0,0,0}};
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
				c3return=true;
				return;
			}
		}

	}

	private void case3C4(Matrix p, Matrix q, int H){
		Coordinate<Integer> J = new Coordinate<Integer>(0, 0);
		Matrix I,K;
		I = q.plus(i1.times((double)H));
		K = p.plus(k1.times((double)H));
		
		int itemp1 = (int)I.get(0, 0);
		int itemp2 = (int)I.get(0, 1);
		Coordinate<Integer> I1 = new Coordinate<Integer>(itemp1,itemp2);
		
		int ktemp1 = (int)K.get(0, 0);
		int ktemp2 = (int)K.get(0, 1);
		Coordinate<Integer> K1 = new Coordinate<Integer>(ktemp1,ktemp2);
		
		int ptemp1 = (int)p.get(0, 0);
		int ptemp2 = (int)p.get(0, 1);
		Coordinate<Integer> p1 = new Coordinate<Integer>(ptemp1,ptemp2);
		
		int qtemp1 = (int)q.get(0, 0);
		int qtemp2 = (int)q.get(0, 1);
		Coordinate<Integer> q1 = new Coordinate<Integer>(qtemp1,qtemp2);
		
		
		double Ind[][] = {{1,0,1},{0,0,0}};
		int[] Ind1 = new int[Ind[0].length];
		for(int n=0;n<Ind[0].length;n++)
			Ind1[n] = (int)Ind[0][n];

		Matrix Ind2 = new Matrix(Ind);
		FindANOut f = FindAN.findANm(I1, J, K1, Ind1 );
		
		//Solution exists and pos connect with p and q 
		if(f.getFlag()==1){
			ConnectOut cout1 = Connect.connect(p1,f.getResultC(), 'c');
			ConnectOut cout2 = Connect.connect(f.getResultC(), q1, 'c');
			
			if((cout1.getPointer()!=0)&&(cout2.getPointer()!=0)){
				out.setAN(f.getResultC());
				out.setexFlag(1);
				c3return=true;
				return;
			}
			else{
				out.setexFlag(0);
				//AN = [];
				c3return=true;
				return;
			}
		}
		else{
			out.setexFlag(0);
			//AN = [];
		}

	}
	
}
