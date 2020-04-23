package BarterSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
// Don't place your source in a package
import java.util.Scanner;

class TreeNode{
	public ArrayList<TreeNode> child;
	public ArrayList<Long> rate;
	public int flag=0;
	public TreeNode() {
		child=new ArrayList<TreeNode>();
		rate=new ArrayList<Long>();
	}
}

class Graph{

    public static final long MOD=998244353;
	ArrayList<TreeNode> nodes;
	HashMap<String,Integer> ids;
	public Graph(){
		nodes=new ArrayList<TreeNode>();
		ids=new HashMap<String,Integer>();
	}
	public void insert(String namea,String nameb,long value) {
		if(!ids.containsKey(namea)) {ids.put(namea,ids.size());nodes.add(new TreeNode());}
		if(!ids.containsKey(nameb)) {ids.put(nameb,ids.size());nodes.add(new TreeNode());}
		int ida=ids.get(namea);
		int idb=ids.get(nameb);
		
		nodes.get(ida).child.add(nodes.get(idb));
		nodes.get(ida).rate.add(value);

		nodes.get(idb).child.add(nodes.get(ida));
		nodes.get(idb).rate.add(reverse(value));
	}
	
	long power(long a, long b, long c)
	{
	    long res = 1;
	    a %= c;
	    while (b!=0)
	    {
	        if ((b & 1)!=0)
	            res = (res * a) % c;
	        a = (a * a) % c;
	        b >>= 1;
	    }
	    return res;
	}
	
	public long reverse(long n) {
        return power(n, MOD-2, MOD);
	}
	
	public long multiply(long a,long b) {
		return a*b%MOD;
	}
	
	int solveCount=0;
	
	public long solve(int ida,int idb) {
		solveCount++;
		return get(nodes.get(ida),nodes.get(idb));
	}
	
	public long get(TreeNode start,TreeNode end) {
		LinkedList<TreeNode> list=null;
		LinkedList<TreeNode> next=new LinkedList<TreeNode>();
		
		LinkedList<Long> nums=new LinkedList<Long>();
		nums.add(1l);
		
		next.add(start);
		
		while(next.size()>0) {
			list=next;
			next=new LinkedList<TreeNode>();
			while(!list.isEmpty()) {
				TreeNode t=list.remove();
				long n=nums.remove();
				if(t.flag==solveCount)
					continue;
				t.flag=solveCount;
				ArrayList<TreeNode> c=t.child;
				for(int i=0;i<c.size();i++) {
					next.add(c.get(i));
					nums.add(multiply(n,t.rate.get(i)));
					if(next.getLast()==end)
						return nums.getLast();
				}
			}
		}
		return 0;
	}
}

public class Main {
	public static void main (String[] args) throws Exception {
	    
	    Scanner in = new Scanner(System.in);
	    int inputCases=in.nextInt();
	    Graph g=new Graph();
		UnionFind union = new UnionFind(inputCases*2);
	    for(int i=0;i<inputCases;i++) {
	    	String namea=in.next();
	    	String nameb=in.next();
	    	long rate=in.nextLong();
	    	g.insert(namea,nameb,rate);
			union.unionElements(g.ids.get(namea), g.ids.get(nameb));
	    }
	    int testCases=in.nextInt();
	    for(int i=0;i<testCases;i++) {
	    	String n1=in.next();
	    	String n2=in.next();
	    	if(n1.equals(n2)) {
				System.out.println(1);
				continue;
			}
	    	int ida=g.ids.get(n1);
	    	int idb=g.ids.get(n2);
			if(!union.isConnected(ida,idb)) {
				System.out.println(-1);
				continue;
			}
	    	System.out.println(g.solve(ida,idb));
	    }
	    in.close();
	}
}


class UnionFind {

	private int[] parent;
	private int[] weight;

	public UnionFind(int size) {
		this.parent = new int[size];
		this.weight = new int[size];
		for (int i = 0; i < size; i++) {
			this.parent[i] = i;
			this.weight[i] = 1;
		}
	}

	public int find(int element) {
		while (element != parent[element]) {
			parent[element] = parent[parent[element]];
			element = parent[element];
		}
		return element;
	}
	public boolean isConnected(int firstElement, int secondElement) {
		return find(firstElement) == find(secondElement);
	}

	public void unionElements(int firstElement, int secondElement) {
		int firstRoot = find(firstElement);
		int secondRoot = find(secondElement);

		//if (firstRoot == secondRoot) {
		//	return;
		//}

		if (weight[firstRoot] > weight[secondRoot]) {
			parent[secondRoot] = firstRoot;
			weight[firstRoot] += weight[secondRoot];
		} else {// weight[firstRoot] &lt;= weight[secondRoot]
			parent[firstRoot] = secondRoot;
			weight[secondRoot] += weight[firstRoot];
		}
	}
}

