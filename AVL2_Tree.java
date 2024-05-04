package application;

import java.util.Date;

public class AVL2_Tree {
	
	
	Stack tempStack = new Stack();
	Martyrs temp = new Martyrs();
	AVL2_Node root;
	
	AVL2_Tree(){
		root = null;
	}
	

	public void insert(DateStack  key) {
		root = insert(root, key);
	}
	
	private AVL2_Node insert(AVL2_Node root, DateStack key) {
		if (root == null) {
			return new AVL2_Node(key);
		}
		if (key.getDateOfDeth().compareTo(root.getData().getDateOfDeth()) < 0) {
			root.left  = insert(root.left, key) ;
		}
		
		else {
			root.right  =  insert(root.right , key);
		}
		return balance(root);
	}
	
	
	
	private AVL2_Node balance(AVL2_Node  root) {
		if (root == null) {
			return root;
		}
		int balance = getBalance(root);
		if (balance > 1) {
			if (getBalance(root.left) > 0) {
				root = rotateRight(root);
			} else {
				root = rotateLeftRight(root);
			}
		} else if (balance < -1) {
			if (getBalance(root.right) < 0) {
				root = rotateLeft(root);
			} else {
				root = rotateRightLeft(root);
			}
		}
		return root;
	}
	
	
	
	private AVL2_Node rotateRightLeft(AVL2_Node root) {
		AVL2_Node temp = root.right;
		root.right = rotateRight(temp);
		return rotateLeft(root);
	}
	
	
	private AVL2_Node rotateLeft(AVL2_Node root) {
		AVL2_Node temp = root.right;
		root.right = temp.left;
		temp.left = root;
		return temp;
	}
	
	
	private AVL2_Node rotateLeftRight(AVL2_Node root) {
		AVL2_Node temp = root.left ;
		root.left = rotateLeft(temp);
		return rotateRight(root);
	}
	
	
	private AVL2_Node  rotateRight(AVL2_Node  root) {
		AVL2_Node temp = root.left;
		root.left = temp.right;
		temp.right = root;
		return temp;
	}
	
	
	private int getBalance(AVL2_Node root) {
		if (root == null) {
			return 0;
		}
		return getHeight(root.left) - getHeight(root.right);
	}
	

	
	
	private int getHeight(AVL2_Node curr) {
		if (curr == null)
			return 0;
		if (curr.isLeaf())
			return 1;
		else
			return Math.max(1 + getHeight(curr.left ), 1 + getHeight(curr.right));
	}
	
	
	
	

	    // Method to print the AVL tree
	    public void printTree() {
	        printTree(root);
	    }

	    private void printTree(AVL2_Node node) {
	        if (node == null) {
	            return;
	        }

	        printTree(node.left);
	        System.out.println(node.getData());
	        printTree(node.right);
	    }
	

	
	public int height() {
		return height(root);
	}

	private int height(AVL2_Node curr) {
		if (curr == null)
			return 0;
		
		else
			return Math.max(getHeight(curr.left ), getHeight(curr.right));
	}
	
	
	
	
	// this method to check if the Key is found or not in tree (return null if not found and return node if found )
	public AVL2_Node find(DateStack key) {
	    return findNode(root, key);
	}

	private AVL2_Node findNode(AVL2_Node node, DateStack key) {
	    if (node == null || node.getData().getDateOfDeth().compareTo(key.getDateOfDeth()) == 0) {
	        return node;
	    }

	    if (key.compareTo(node.getData()) < 0) {
	        return findNode(node.left, key);
	    } else {
	        return findNode(node.right, key);
	    }
	}
	
	
	
	
	public void  getPostOrder() {
	     getPostOrder(root);
	}

	public void  getPostOrder(AVL2_Node T) {

	    if (T != null) {
	        getPostOrder(T.left);
	        getPostOrder(T.right);
	         System.out.println(T.getData().toString()); 

	}
	    
	}
	
	// this method to print backWord 
	
	public String getInOrder() {
	    return getInOrder(root);
	}

	public String getInOrder(AVL2_Node T) {
	    StringBuilder result = new StringBuilder();

	    if (T != null) {
	        result.append(getInOrder(T.left));
	     
	        while (!T.getData().getStack().isEmpty()) {
	        	temp = T.getData().getStack().pop();

	        	result.append(temp).append("\n\n");
	        	//System.out.println(temp);
	        	tempStack.push(temp);
	        }
	        
	        while (!tempStack.isEmpty()) {
	        	T.getData().getStack().push(tempStack.pop());
	        }
	        result.append(getInOrder(T.right));
	    }
	    
	   

	    return result.toString();
	}
	

	
		// this method to return the date with max number of martyrs
	    public Date findMaxMartyrsDate() {
	        if (root == null) {
	            return null; // Return null if the tree is empty
	        }

	        AVL2_Node maxNode = findMaxMartyrsNode(root);
	        
	        return maxNode.getData().getDateOfDeth();  // return the date of node with max number 
	    }

	    
	    private AVL2_Node findMaxMartyrsNode(AVL2_Node node) {
	    	
	        AVL2_Node  maxNode = node;   // the node with max number
	        int maxCount = node.getData().getStack().size();  // number of martyrs in left node stack
	        
	        
	        
	        
	        if (node.left != null) {    // left count
	            AVL2_Node  leftMaxNode = findMaxMartyrsNode(node.left);
	            int leftCount = leftMaxNode.getData().getStack().size();  //  number of martyrs in right node stack
	            
	            if (leftCount > maxCount) {
	            	maxNode = leftMaxNode;
	                maxCount = leftCount;
	            }
	        }

	        
	        
	        if (node.right != null) {  // right count 
	            AVL2_Node rightMaxNode = findMaxMartyrsNode(node.right);
	            int rightCount = rightMaxNode.getData().getStack().size();
	            
	            if (rightCount > maxCount) {
	            	maxNode = rightMaxNode;
	                maxCount = rightCount;
	            }
	        }

	        return maxNode;
	    }
	   
	    
	    
	    
	    public int countNodes() {
		    return countNodes(root);
		}

		private int countNodes(AVL2_Node node) {
		    int count = 1;
		    
		    if (node == null)
		    	return 0;
		    else {
		    	count+= countNodes(node.left);
		    	count+= countNodes(node.right);
		    	
		    	return count;

		    }
		}
		
		
		public void delete(DateStack key) {
		    root = deleteNode(root, key);
		}

		private AVL2_Node deleteNode(AVL2_Node root, DateStack key) {
		    if (root == null) {
		        return root; // Tree is empty or key not found
		    }
		    
		    

		    if (key.getDateOfDeth().compareTo(root.getData().getDateOfDeth()) < 0) {
		        root.left = deleteNode(root.left, key); // Key is smaller, go to the left subtree
		    }
		    
		    else if (key.getDateOfDeth().compareTo(root.getData().getDateOfDeth()) > 0) {
		        root.right = deleteNode(root.right, key); // Key is greater, go to the right subtree
		    } 
		    
		    
		    else {
		        // Found the node to be deleted
		        if (root.left == null || root.right == null) {
		            // Node has no children or only one child
		            AVL2_Node temp = null;
		            if (root.left != null) {
		                temp = root.left;
		            } 
		            else {
		                temp = root.right;
		            }

		            if (temp == null) {
		                // Node has no children
		                temp = root;
		                root = null;
		            } else {
		                // Node has one child
		                root = temp; // Replace the node with its child
		            }
		            temp = null; // Delete the temporary node
		        } 
		        
		        else {
		            // Node has two children
		            AVL2_Node min = findMinNode(root.right); // Find the inorder successor
		            root.setData(min.getData()); // Copy the data of the successor
		            root.right = deleteNode(root.right, min.getData()); // Delete the successor
		        }
		    }

		    if (root != null) {
		        root = balance(root); // Rebalance the tree after deletion
		    }
		    return root;
		}

		private AVL2_Node findMinNode(AVL2_Node node) {
		    AVL2_Node current = node;
		    while (current.left != null) {
		        current = current.left;
		    }
		    return current;
		}

		
		
		
}
