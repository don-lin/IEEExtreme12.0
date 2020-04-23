package TreeFun;

import java.util.HashMap;
import java.util.Scanner;

class Node{
	Node parent;
	int number;
	int id;
}

class Tree{
	Node[] nodes;
	public Tree(int nodeNum) {
		nodes=new Node[nodeNum];
		for(int i=0;i<nodes.length;i++) {
			nodes[i]=new Node();
			nodes[i].number=0;
			nodes[i].id=i;
		}
	}
	public void insert(int a,int b) {
		if(nodes[b].parent==null)
			nodes[b].parent=nodes[a];
		else
			nodes[a].parent=nodes[b];
	}
	public void add(int from,int to,int K) {
		HashMap<Integer,Boolean> hm=new HashMap<Integer,Boolean>();
		Node current=nodes[from];
		Node commonPar=null;
		while(current!=null) {
			hm.put(current.id, true);
			current=current.parent;
		}
		
		current=nodes[to];
		
		while(!hm.containsKey(current.id)) {
			current.number+=K;
			current=current.parent;
			if(current==null)
				break;
		}
		commonPar=current;
		current.number+=K;
		
		try {
		current=nodes[from];
		while(current!=commonPar) {
			current.number+=K;
			current=current.parent;
		}
		}catch(Exception e) {
			
		}
	}
	public int getMax() {
		int max=0;
		for(int i=0;i<nodes.length;i++) {
			if(nodes[i].number>max)
				max=nodes[i].number;
		}
		return max;		
	}
}

public class Main {
	public static void main(String[] args) {
		Scanner in=new Scanner(System.in);
		int N=in.nextInt();
		int M=in.nextInt();
		Tree tree=new Tree(N);
		for(int i=0;i<N-1;i++) {
			int par=in.nextInt();
			int son=in.nextInt();
			tree.insert(par,son);
		}
		
		for(int i=0;i<M;i++) {
			int from=in.nextInt();
			int to=in.nextInt();
			int K=in.nextInt();
			tree.add(from, to, K);
		}
		
		System.out.println(tree.getMax());
		in.close();
		
	}
}
