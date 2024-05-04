package application;

public class BST2 {

	
	AVL2_Node root;

	public BST2() {
		super();
	}
	
	
	/*
	public void insert(DateStack data)
	{
		root = insert (root , data);
	}
	
	
	
	
    private AVL2_Node insert(AVL2_Node T , DateStack Key) {
		
		if (T == null) {
			T = new AVL2_Node(Key);
			return T;	
		}
		
		else {
			
			if (Key.compareTo(T.getData())  < 0)
				T.left =  insert (T.left , Key);
			else if (Key.compareTo(T.getData()) > 0 || Key.compareTo(T.getData()) ==  0  )
				T.right = insert (T.right , Key);
			
			return T;
		}
	}
    
    
    public AVL2_Node findMin() {
		return findMin(root);
	}
    
    */
	
	private AVL2_Node findMin(AVL2_Node T) {
		if (T == null )
			return null ;
		else {
			if (T.left == null )
				return T;
			else 
				return (findMin (T.left));
		}
	}
	
	
	public AVL2_Node findMax() {
		return findMax(root);
	}
	
	
	private AVL2_Node findMax(AVL2_Node T) {
		if (T == null )
			return null ;
		else {
			if (T.right == null )
				return T;
			else 
				return (findMin (T.right));
		}
	}
	
	

	public AVL2_Node delete(DateStack Key)
	{
		return delete (root , Key );
	}
	
	
	
	private AVL2_Node delete(AVL2_Node T , DateStack Key) {
		
		AVL2_Node temp ,child = null;
		
		if (T == null )
			return null ;
		
		else 
		{
			if (Key.compareTo(T.getData()) < 0)
				T.left = delete (T.left ,  Key);
			
			else if (T.hasLeft() && T.hasRight()) {
				temp = findMin(T.right);
				T.setData(temp.getData());
				T.right = delete (T.right , T.getData());
			}
			
			else {
				
				if (T.left == null )
					child = T.right;
				if (T.right == null )
					child = T.left;
				return child;
			}
			
		}
		return T;
	}

	
	
	
	
	
	
}
