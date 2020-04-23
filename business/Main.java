package business;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

class UnionFind {

	private int[] parent;
	private int[] weight;
	private int size;

	public UnionFind(int size) {
		this.parent = new int[size];
		this.weight = new int[size];
		this.size = size;
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

	public int size() {
		return size;
	}
	public boolean isConnected(int firstElement, int secondElement) {
		return find(firstElement) == find(secondElement);
	}

	public void unionElements(int firstElement, int secondElement) {
		int firstRoot = find(firstElement);
		int secondRoot = find(secondElement);

		// 如果已经属于同一个集合了，就不用再合并了。
		if (firstRoot == secondRoot) {
			return;
		}

		if (weight[firstRoot] > weight[secondRoot]) {
			parent[secondRoot] = firstRoot;
			weight[firstRoot] += weight[secondRoot];
		} else {// weight[firstRoot] &lt;= weight[secondRoot]
			parent[firstRoot] = secondRoot;
			weight[secondRoot] += weight[firstRoot];
		}
	}

	private void printArr(int[] arr) {
		for (int p : arr) {
			System.out.print(p + "\t");
		}
		System.out.println();
	}

}

class Node{
	LinkedList<Node> nodes;
	LinkedList<Long> value;
	String name;
	public Node(String n) {
		nodes=new LinkedList<Node>();
		value=new LinkedList<Long>();
	}
}

class Graph{
	Node[] nodes;
	
	public static long power(long a, long b, long c)
	{
	    long res = 1;
	    for (long i = 1; i <= b; i++)
	        res = (res * a) % c;
	    return res;
	}
	
	public static long getReverse(long n) {
        return power(n, Main.MOD-2, Main.MOD);
	}
	
	public static long multiply(long a,long b) {
		return a*b%Main.MOD;
	}
	
	public Graph(int nodeNumber) {
		nodes=new Node[nodeNumber];
	}
	public void setUpID(int id,String name) {
		nodes[id]=new Node(name);
	}
	public void connect(int ida,int idb,long value) {
		nodes[ida].nodes.add(nodes[idb]);
		nodes[ida].value.add(getReverse(value));
		nodes[idb].nodes.add(nodes[ida]);
		nodes[idb].value.add(value);
	}
	
	public void compute(int ida,int idb) {
		//nodes[ida]
	}
}


public class Main {
	public static final long MOD=998244353;
	
	public static long power(long a, long b, long c)
	{
	    long res = 1;
	    for (long i = 1; i <= b; i++)
	        res = (res * a) % c;
	    return res;
	}
	
	public static long getReverse(long n) {
        return power(n, MOD-2, MOD);
	}
	
	
	public static long multiply(long a,long b) {
		return a*b%MOD;
	}
	
	public static void main(String[] args) {
		
		Scanner in=new Scanner(System.in);
		int cases=in.nextInt();
		UnionFind union = new UnionFind(cases*2);
		LinkedList<String> name1=new LinkedList<String>();
		LinkedList<String> name2=new LinkedList<String>();
		LinkedList<Integer> numbers=new LinkedList<Integer>();
		
		HashMap<String,Integer> names=new HashMap<String,Integer>();
		HashMap<String,LinkedList<String>> connections=new HashMap<String,LinkedList<String>>();
		int count=0;
		for(int i=0;i<cases;i++) {
			String n1=in.next();
			String n2=in.next();
			name1.add(n1);
			name2.add(n2);
			
			int num=in.nextInt();
			numbers.add(num);
			
			if(!names.containsKey(n1))
				names.put(n1, count++);
			if(!names.containsKey(n2))
				names.put(n2, count++);
			union.unionElements(names.get(n1), names.get(n2));
			
		}
		
		int testCases=in.nextInt();
		for(int i=0;i<testCases;i++) {
			String n1=in.next();
			String n2=in.next();
			if(n1.equals(n2)) {
				System.out.println(1);
				continue;
			}
			if(!union.isConnected(names.get(n1), names.get(n2))) {
				System.out.println(-1);
				continue;
			}
			System.out.println("ok");
		}
	}
}
