package BinaryTree;

import java.util.LinkedList;
import java.util.Scanner;

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	int space;

	TreeNode(int x) {
		val = x;
	}
}

class Solution {

	public TreeNode buildTree(int[] preorder, int[] inorder) {
		if (preorder == null || inorder == null || preorder.length == 0) {
			return null;
		}
		return buildCore(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
	}

	private TreeNode buildCore(int[] preorder, int preSt, int preEnd, int[] inorder, int inSt, int inEnd) {
		// 前序遍历第一个节点是根节点
		int rootValue = preorder[preSt];
		TreeNode root = new TreeNode(rootValue);

		// 前序序列只有根节点
		if (preSt == preEnd) {
			return root;
		}
		// 遍历中序序列，找到根节点的位置
		int rootInorder = inSt;
		while (inorder[rootInorder] != rootValue && rootInorder <= inEnd) {
			rootInorder++;
		}

		// 左子树的长度
		int leftLength = rootInorder - inSt;
		// 前序序列中左子树的最后一个节点
		int leftPreEnd = preSt + leftLength;

		// 左子树非空
		if (leftLength > 0) {
			// 重建左子树
			root.left = buildCore(preorder, preSt + 1, leftPreEnd, inorder, inSt, inEnd);
		}
		// 右子树非空
		if (leftLength < preEnd - preSt) {
			// 重建右子树
			root.right = buildCore(preorder, leftPreEnd + 1, preEnd, inorder, rootInorder + 1, inEnd);
		}
		return root;
	}
	public int height=-1;
	public int count=0;
	LinkedList<Integer> result=new LinkedList<Integer>();
	void InOrderTraverse(TreeNode  t)
	 {
	 	//height++;
		 if(t!=null)
		 {
			// height[count]=heightValue;
	        // System.out.println((char)t.val+" : "+height);
	         //result.add(count);
	         //result.add(height);
	         InOrderTraverse(t.left);
			 t.space=count;
			// System.out.println((char)t.val+":"+count);
	         count++;
	    
	        // System.out.println((char)t.val);
	         InOrderTraverse(t.right);
		 }
		 //height--;
	 }
	
	public void print(char value,int space) {
		String result=new String();
		for(int i=0;i<space;i++)
			result+=" ";
		System.out.print(result+""+value);
	}
	
	void print(TreeNode t) {
		LinkedList<TreeNode> current=new LinkedList<TreeNode>();
		LinkedList<TreeNode> next=null;
		current.add(t);
		while(next==null||!next.isEmpty()) {
			next=new LinkedList<TreeNode>();
			int position=0;
			int count=0;
			while(!current.isEmpty()) {
				TreeNode n=current.remove();
				if(n.left!=null)next.add(n.left);
				if(n.right!=null)next.add(n.right);
				print((char)n.val,n.space-position-count);
				count=1;
				position=n.space;
			}
			System.out.println();
			current=next;
		}
	}
}

public class Main{
	public static void main(String[] args) {
		Scanner in=new Scanner(System.in);
		in.hasNext();
		String ino=in.nextLine();
		String preo=in.nextLine();
		
		Solution s=new Solution();
		s.print( initTree(ino,preo) );
	}
	
	public static TreeNode initTree(String ino,String preo) {
		int[]  inorder=new int[ino.length()];
		for(int i=0;i<inorder.length;i++)
			inorder[i]=ino.charAt(i);
		int[] preorder=new int[preo.length()];
		for(int i=0;i<preorder.length;i++)
			preorder[i]=preo.charAt(i);
		Solution s=new Solution();
		TreeNode n=s.buildTree( preorder,inorder);
		s.InOrderTraverse(n);
		return n;
	}
	
	public static int[] getHeight(String ino,String preo) {
		
		int[]  inorder=new int[ino.length()];
		for(int i=0;i<inorder.length;i++)
			inorder[i]=ino.charAt(i);
		int[] preorder=new int[preo.length()];
		for(int i=0;i<preorder.length;i++)
			preorder[i]=preo.charAt(i);
		Solution s=new Solution();
		TreeNode n=s.buildTree( preorder,inorder);
		s.InOrderTraverse(n);
		LinkedList<Integer> result=s.result;
		int resultLength=preo.length();
		int[] height=new int[resultLength];
		while(!result.isEmpty()) {
			int count=result.remove();
			int h=result.remove();
			
			height[count]=h;
			//System.out.println(count+","+h);
			
		}
		return height;
	}

	public static void print(char value,int space) {
		String result=new String();
		for(int i=0;i<space;i++)
			result+=" ";
		System.out.print(result+""+value);
	}
	
	public static void drawTree(String intr,int[] pos,int[] height) {

		int lastSpace=0;
		int lastHeight=0;
		for(int i=0;i<intr.length();i++) {
			char value=intr.charAt(i);
			int space=pos[value];
			if(height[i]>lastHeight) {
				System.out.println();
				lastHeight=height[i];
				lastSpace=0;
			}
			space-=lastSpace;
			lastSpace+=space;
			print(value,space);
		}
	}
}
