package teleStar;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

class Star implements Comparator<Star>{
	int start;
	int end;
	int desirability;
	public String toString() {
		return "s:"+start+" e:"+end+" d:"+desirability;
	}
	@Override
	public int compare(Star a, Star b) {
		return a.start-b.start;
	}
}

public class Main {
	public static boolean check(Star a,Star b) {
		if(a.end<b.start)
			return false;
		if(b.end<a.start)
			return false;
		return true;
	}
	
	public static void main(String[] args) {
		LinkedList<Star> list=new LinkedList<Star>();
		Scanner in=new Scanner(System.in);
		int testCases=in.nextInt();
		int max=0;
		for(int currentC=0;currentC<testCases;currentC++) {
			Star s=new Star();
			s.start=in.nextInt();
			s.end=in.nextInt();
			if(s.end>max)
				max=s.end;
			s.desirability=in.nextInt();
			list.add(s);
		}
		list.sort(new Star());
		
		int[] dp=new int[max+1];		
		
		while(!list.isEmpty()) {
			Star s=list.remove();
			int result=s.desirability+dp[s.start];
			for(int i=s.end+1;i<dp.length;i++) {
				if(result>dp[i])
					dp[i]=result;
			}
		}
		
		System.out.println(dp[dp.length-1]);
		in.close();
	}
}
