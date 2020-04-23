package magic;

// Don't place your source in a package
import java.util.*;

public class Main {

	public static void main (String[] args) throws java.lang.Exception {
		    Scanner in = new Scanner(System.in);
			int testcases = in.nextInt();
			in.nextLine();
			for(int i=0;i<testcases;i++){
			    int sum=0;
			    String s=in.nextLine();
			    for(int j=0;j<s.length();j++){
			        sum+=s.charAt(j);
			    }
			    System.out.println(sum);
			}
		}
}
