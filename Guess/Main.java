package Guess;

import java.util.Scanner;

public class Main {
	public static void outputTwo(int numbers,int j) {
		System.out.print("Q");
		for(int i=0;i<numbers;i++) {
			if(i==j||i==j+1)
				System.out.print(" 1");
			else
				System.out.print(" 0");
		}
	}
	public static void outputOne(int numbers,int j) {
		System.out.print("Q");
		for(int i=0;i<numbers;i++) {
			if(i==j)
				System.out.print(" 1");
			else
				System.out.print(" 0");
		}
	}
	public static void output(boolean[] arr) {
		System.out.print("A");
		for(int i=0;i<arr.length;i++) {
			System.out.print(arr[i]?" 1":" 0");
		}
	}
	public static void main(String[] args) {
		Scanner in=new Scanner(System.in);
		int testCases=in.nextInt();
		for(int currentC=0;currentC<testCases;currentC++) {
			int numbers=in.nextInt();
			boolean[] arr=new boolean[numbers];
			outputOne(numbers,-1);
			int base=in.nextInt();
			int count=0;
			if(numbers%2==1) {
				outputOne(numbers,numbers-1);
				if(in.nextInt()>base) {
					count++;
					arr[numbers-1]=true;
				}
			}
			
			for(int i=0;i<numbers-1;i+=2) {
				outputTwo(numbers,i);
				int get=in.nextInt();
				if(get==base) {
					outputOne(numbers,i);
					int getOne=in.nextInt();
					count++;
					if(getOne>base) {
						arr[i]=true;
						arr[i+1]=false;
					}
					else {
						arr[i]=false;
						arr[i+1]=true;
					}
				}
				else if(get==base+2) {
					arr[i]=true;
					arr[i+1]=true;	
					count+=2;
				}else if(get==base-2) {
					arr[i]=false;
					arr[i+1]=false;
				}
				if(count+base==numbers)
					break;
			}
			output(arr);			
		}
		in.close();
	}
}
