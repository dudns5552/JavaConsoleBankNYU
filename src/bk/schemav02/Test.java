package bk.schemav02;

public class Test {

	public static void main(String[] args) {

		int gd = 1111;
		int norInt = 3;
		double inter = (double)norInt/100;
		
		int dpM = 5000;
		
		int set = gd + (int)(gd * inter) + dpM;
		System.out.println(set);
		System.out.println(gd + (int)(gd * inter) + dpM);
		System.out.println(inter);
	}

}
