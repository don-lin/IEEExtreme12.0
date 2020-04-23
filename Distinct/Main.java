package Distinct;

import java.util.LinkedList;
import java.util.Scanner;

public class Main {
	public static int[] bucket=new int[300000];
	
	public static int order() {
		int count=0;
		int sum=0;
		for(int i=0;i<bucket.length;i++) {
			while(bucket[i]>1) {
				int n=nearest(i);
				bucket[n]++;
				bucket[i]--;
				sum+=Math.abs(n-i);
			}
		}		
		return sum;
	}
	
	public static int nearest(int pos){
		for(int gap=1;true;gap++) {
			if(bucket[pos-gap]==0)
				return pos-gap;
			if(bucket[pos+gap]==0)
				return pos+gap;
		}
	}
	
	public static void out() {
		for(int i=0;i<bucket.length;i++) {
			if(bucket[i]!=0)
				System.out.print(i-100000+":"+bucket[i]+" ");
		}
	}
	
	public static void main(String[] args) {
		Scanner in=new Scanner(System.in);
		int inputCases=in.nextInt();
		for(int i=0;i<inputCases;i++) {
			bucket[in.nextInt()+100000]++;
		}
		System.out.println(order());
		//out();
		in.close();
	}
}
