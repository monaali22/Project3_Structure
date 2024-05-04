package application;


public class BST {

	AVL_Node root;

	public BST() {
		super();
	}
	
	
	
	
	
	
	
	public void  printPostOrder() {
		printPostOrder(root);
	}

	public void  printPostOrder(AVL_Node T) {
	    if (T != null) {
	    	printPostOrder(T.left);
	    	printPostOrder(T.right);
	         System.out.println(T.getData().toString()); 

	}
	    
	}
}
