package application;

public class AVL2_Node {

	private DateStack data;
    private int height;
     AVL2_Node left, right;
     
     
     public AVL2_Node() {
 		super();
 		
 	}
       
     
	public AVL2_Node(DateStack data) {
		super();
		this.data = data;
		
		
	
	}


	public DateStack getData() {
		return data;
	}


	public void setData(DateStack data) {
		this.data = data;
	}


	public int getHeight() {
		return height;
	}


	public void setHeight(int height) {
		this.height = height;
	}

	
	 public boolean hasLeft() {
			 return left != null;
		}

      public boolean hasRight() {
			return right != null;
		}
		
	   public boolean isLeaf() {
			    return !hasLeft() && !hasRight();
		}

     
}
