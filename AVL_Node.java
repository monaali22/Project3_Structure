package application;


public class AVL_Node {

	private Martyrs data;
    private int height;
     AVL_Node left, right;
   


    public  AVL_Node() {
		super();
	}



	AVL_Node (Martyrs data) {
       this.data = data;
   }



	public Martyrs getData() {
		return data;
	}



	public void setData(Martyrs data) {
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
