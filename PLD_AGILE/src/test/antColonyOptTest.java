package test;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import model.Stop;
import model.Tour;

public class antColonyOptTest {


	@Test
	public void test1() {
		double[][] graphe = {{0.0,2.0,5.0,7.0,5.0},
							{3.0,0.0,5.0,10.0,15.0},
							{10.0,7.0,0.0,8.0,10.0},
							{5.0,10.0,7.0,0.0,4.0},
							{3.0,14.0,8.0,4.0,0.0}};
		Tour tour=new Tour();
		Stop[] real_Id_Of = new Stop[5];
		int[] resultOfTSP = tour.antColonyOpt(0.8, 1, 5, 2000, (int)(1.6*2+1), graphe,real_Id_Of);
		for(int i=0;i<resultOfTSP.length;i++)
			System.out.print(resultOfTSP[i]+" ");
		int[] expectedpath = {0,1,2,3,4,0};
		assertTrue(Arrays.equals(expectedpath, resultOfTSP));
	}
	@Test
	public void test2() {
		double[][] graphe = {{0.0,5.0,8.0,7.0,20.0},
							{7.0,0.0,4.0,5.0,8.0},
							{4.0,7.0,0.0,6.0,10.0},
							{7.0,10.0,4.0,0.0,4.0},
							{20.0,6.0,10.0,15.0,0.0}};
		Tour tour=new Tour();
		Stop[] real_Id_Of = new Stop[5];
		int[] resultOfTSP = tour.antColonyOpt(0.8, 1, 5, 2000, (int)(1.6*2+1), graphe,real_Id_Of);
		for(int i=0;i<resultOfTSP.length;i++)
			System.out.print(resultOfTSP[i]+" ");
		int[] expectedpath = {0,3,4,1,2,0};
		assertTrue(Arrays.equals(expectedpath, resultOfTSP));
	}
	@Test
	public void test3() {
		double[][] graphe = {{0.0,46.0,88.0,35.0,74.0,89.0,78.0},
							{49.0,0.0,67.0,83.0,41.0,20.0,55.0},
							{22.0, 36.0, 0.0, 69.0, 61.0, 34.0, 40.0},
							{61.0, 98.0, 86.0, 0.0, 74.0, 20.0, 85.0},
							{44.0, 81.0, 22.0, 29.0, 0.0, 72.0, 29.0},
							{48.0, 51.0, 51.0, 20.0, 23.0, 0.0, 45.0},
							{88.0, 25.0, 69.0, 60.0, 28.0, 94.0, 0.0}};
		Tour tour=new Tour();
		Stop[] real_Id_Of = new Stop[7];
		int[] resultOfTSP =  tour.antColonyOpt(0.8, 1, 5, 2000, (int)(1.6*3+1), graphe,real_Id_Of);
		for(int i=0;i<resultOfTSP.length;i++)
			System.out.print(resultOfTSP[i]+" ");
		int[] expectedpath = {0,3,5,6,1,4,2,0}; // best distance : 210
		assertTrue(Arrays.equals(expectedpath, resultOfTSP));
	}
	
	@Test
	public void test4() {
		double[][] graphe = {{0.0 ,24.0 ,38.0, 27.0 ,14.0,16.0 ,38.0},
							{14.0 ,0.0 ,16.0, 23.0, 8.0, 35.0 ,23.0},
							{12.0 ,23.0, 0.0, 26.0 ,17.0 ,24.0, 33.0},
							{25.0 ,18.0 ,22.0 ,0.0 ,2.0 ,22.0, 17.0},
							{36.0 ,28.0, 38.0, 27.0, 0.0, 38.0 ,22.0},
							{37.0, 22.0, 36.0, 13.0 ,27.0 ,0.0, 9.0},
							{14.0 ,26.0 ,5.0, 2.0, 22.0, 4.0, 0.0}};
		Tour tour=new Tour();
		Stop[] real_Id_Of = new Stop[7];
		int[] resultOfTSP =  tour.antColonyOpt(0.8, 1, 5, 2000, (int)(1.6*3+1), graphe,real_Id_Of);
		for(int i=0;i<resultOfTSP.length;i++)
			System.out.print(resultOfTSP[i]+" ");
		int[] expectedpath = {0,5,6,3,4,1,2,0}; // best distance 85
		assertTrue(Arrays.equals(expectedpath, resultOfTSP));
	}
	@Test
	public void test5() {
		double[][] graphe = {{0.0, 46.0, 88.0, 35.0, 74.0},
							{49.0, 0.0, 67.0, 83.0, 0.0},
							{22.0, 36.0, 0.0, 69.0, 61.0},
							{60.0, 0.0, 86.0, 0.0, 74.0},
							{44.0, 81.0, 22.0, 29.0, 0.0}};
		Tour tour=new Tour();
		Stop[] real_Id_Of = new Stop[5];
		int[] resultOfTSP =  tour.antColonyOpt(0.8, 1, 5, 2000, (int)(1.6*2+1), graphe,real_Id_Of);
		for(int i=0;i<resultOfTSP.length;i++)
			System.out.print(resultOfTSP[i]+" ");
		int[] expectedpath = {0,3,1,4,2,0}; // best distance 79
		assertTrue(Arrays.equals(expectedpath, resultOfTSP));
	}
	@Test
	public void test6() {
		double[][] graphe = {{0.0, 2.0, 5.0, 7.0, 1.0},
							{6.0, 0.0, 3.0, 8.0, 2.0},
							{8.0, 7.0, 0.0, 4.0, 7.0},
							{12.0, 4.0, 6.0, 0.0, 5.0},
							{0 ,0 ,0, 0, 0}};
		Tour tour=new Tour();
		Stop[] real_Id_Of = new Stop[5];
		int[] resultOfTSP = tour.antColonyOpt(0.8, 1, 5, 2000, (int)(1.6*2+1), graphe,real_Id_Of);
		for(int i=0;i<resultOfTSP.length;i++)
			System.out.print(resultOfTSP[i]+" ");
		int[] expectedpath = {0,1,2,3,4,0}; // best distance 14
		assertTrue(Arrays.equals(expectedpath, resultOfTSP));
	}
	@Test
	public void test7() {
		double[][] graphe = {{0}};
		Tour tour=new Tour();
		Stop[] real_Id_Of = new Stop[1];
		int[] resultOfTSP =  tour.antColonyOpt(0.8, 1, 5, 2000, (int)(1.6*0+1), graphe,real_Id_Of);
		for(int i=0;i<resultOfTSP.length;i++)
			System.out.print(resultOfTSP[i]+" ");
		int[] expectedpath = {0,0};
		assertTrue(Arrays.equals(expectedpath, resultOfTSP));
	}
	@Test
	public void test11() {

		double[][] graphe = {{0.0, 0.0, 0.0, 0.0, 0.0},
							{0.0, 0.0, 0.0, 0, 0.0},
							{0.0, 0.0, 0.0, 0.0, 0.0},
							{0.0, 0.0, 0.0, 0.0, 0.0},
							{0.0, 0.0, 0.0, 0.0, 0.0}};
		Tour tour=new Tour();
		Stop[] real_Id_Of = new Stop[5];
		int[] resultOfTSP =  tour.antColonyOpt(0.8, 1, 5, 2000, (int)(1.6*2+1), graphe,real_Id_Of);
		for(int i=0;i<resultOfTSP.length;i++)
			System.out.print(resultOfTSP[i]+" ");
		int[] expectedpath1 = {0,3,1,2,4,0}; // best distance 108
		int[] expectedpath2 = {0,3,1,4,2,0};
		int[] expectedpath6 = {0,3,4,1,2,0};
		int[] expectedpath3 = {0,1,3,2,4,0};
		int[] expectedpath4 = {0,1,3,4,2,0};
		int[] expectedpath5 = {0,1,2,3,4,0};
		assertTrue(Arrays.equals(expectedpath1, resultOfTSP) || Arrays.equals(expectedpath2, resultOfTSP) ||
				Arrays.equals(expectedpath3, resultOfTSP)  ||Arrays.equals(expectedpath4, resultOfTSP)||Arrays.equals(expectedpath5, resultOfTSP)
				||Arrays.equals(expectedpath6, resultOfTSP));
	}
	@Test
	public void test12() {
		double[][] graphe = {{0.0, 46.0, 0.0, 35.0, 0.0},
							{49.0, 0.0, 49.0, 30.0, 49.0},
							{0.0, 46.0, 0.0, 35.0, 0.0},
							{60.0, 24.0, 60.0, 0.0, 60.0},
							{0.0, 46.0, 0.0, 35.0, 0.0}};
		Tour tour=new Tour();
		Stop[] real_Id_Of = new Stop[5];
		int[] resultOfTSP =  tour.antColonyOpt(0.8, 1, 5, 2000, (int)(1.6*2+1), graphe,real_Id_Of);
		for(int i=0;i<resultOfTSP.length;i++)
			System.out.print(resultOfTSP[i]+" ");
		int[] expectedpath1 = {0,3,1,2,4,0}; // best distance 108
		int[] expectedpath2 = {0,3,1,4,2,0};
		int[] expectedpath3 = {0,1,3,2,4,0};
		int[] expectedpath4 = {0,1,3,4,2,0};
		assertTrue(Arrays.equals(expectedpath1, resultOfTSP) || Arrays.equals(expectedpath2, resultOfTSP) ||
				Arrays.equals(expectedpath3, resultOfTSP)  ||Arrays.equals(expectedpath4, resultOfTSP));
	}
}
