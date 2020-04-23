package BinaryTree;

import java.util.Scanner;
class Node {
    int value;
    Node left;
    Node right;
 
    Node(int value) {
        this.value = value;
        right = null;
        left = null;
    }
}

class BinaryTree {
	 
    Node root;
    int height=0;
    private Node addRecursive(Node current, int value) {
        if (current == null) {
            return new Node(value);
        }
        height++;
     
        if (value < current.value) {
            current.left = addRecursive(current.left, value);
        } else if (value > current.value) {
            current.right = addRecursive(current.right, value);
        } else {
            // value already exists
            return current;
        }
     
        return current;
    }
    
    public int add(int value) {
    	height=0;
        root = addRecursive(root, value);
        return height;
    }
    private boolean containsNodeRecursive(Node current, int value) {
        if (current == null) {
            return false;
        } 
        if (value == current.value) {
            return true;
        } 
        return value < current.value
          ? containsNodeRecursive(current.left, value)
          : containsNodeRecursive(current.right, value);
    }
    public boolean containsNode(int value) {
        return containsNodeRecursive(root, value);
    }
}

class BiNode{
	BiNode left;
	BiNode right;
	int value;
}

public class Main1 {
	static BiNode result;

	static int CreateBiTree(BiNode t,String presequence,String insequence)//tΪҪ�����Ķ�����,presequence��insequence�ֱ�Ϊǰ�����������
	{
	   if(presequence.length()==0)
	   {
	        // t=null;
			 return -1;
	   }
	   char rootNode=presequence.charAt(0);//��
	   int index=insequence.indexOf(rootNode);//�������������е�λ��
	   String lchild_insequence=insequence.substring(0,index);//���ӵ���������
	   String rchild_insequence=insequence.substring(index+1);//�Һ��ӵ���������
	   int lchild_length=lchild_insequence.length();//���ӵĳ���
	   int rchild_length=rchild_insequence.length();//�Һ��ӵĳ���
	   String lchild_presequence=presequence.substring(1,lchild_length);//���ӵ�ǰ������
	   String rchild_presequence=presequence.substring(1+lchild_length);//�Һ��ӵ�ǰ������
	 
	  // t=new BiNode();
	   if(t==null)
	   result=t=new BiNode();
		   t.value=rootNode;
		   t.left=new BiNode();
		   t.right=new BiNode();
	       if(CreateBiTree(t.left,lchild_presequence,lchild_insequence)==-1)
	    	   t.left=null;//�ݹ鴴������
		   if(CreateBiTree(t.right,rchild_presequence,rchild_insequence)==-1)
			   t.right=null;//�ݹ鴴���Һ���
	   
	   return 0;
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
	
	public static void main(String[] args) {
		Scanner in=new Scanner(System.in);
		String pret=in.nextLine();
		int[] pos=new int[256];
		for(int i=0;i<pret.length();i++) {
			pos[pret.charAt(i)]=i;
		}
		int[] height=new int[pret.length()];
		BinaryTree bt=new BinaryTree();
		for(int i=0;i<height.length;i++) {
			height[i]=bt.add(pret.charAt(i));
		}
		//int[] height= {0,1,2};
		String intr=in.nextLine();
		
		//CreateBiTree(null,intr,pret);
		
	}

}
