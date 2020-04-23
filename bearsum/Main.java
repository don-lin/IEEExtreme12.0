package bearsum;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
	public static int[] solve(int expect,int[] arr) {
		HashMap<Integer,Integer> hm=new HashMap<Integer,Integer>();
		for(int i=0;i<arr.length;i++) {
			if(hm.containsKey(expect-arr[i])) {
				int[] result=new int[2];
				result[0]=expect-arr[i];
				result[1]=arr[i];
				if(result[0]>result[1]) {
					int temp=result[0];
					result[0]=result[1];
					result[1]=temp;
				}
				return result;
			}
			if(!hm.containsKey(arr[i])) {
				hm.put(arr[i], i);
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		Scanner in=new Scanner(System.in);
		int cases=in.nextInt();
		for(int i=0;i<cases;i++) {
			int expect=in.nextInt();
			int input=in.nextInt();
			int[] arr=new int[input];
			for(int j=0;j<input;j++)
				arr[j]=in.nextInt();
			int[] result=solve(expect,arr);
			if(result==null)
				System.out.println("!OK");
			else
				System.out.println(result[0]+" "+result[1]);
			
		}
	}
}
