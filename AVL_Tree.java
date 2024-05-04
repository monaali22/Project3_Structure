package application;
import java.util.Date;

import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;



public class AVL_Tree {
	private AVL_Node root;
	public AVL_Tree() {
		root = null;
	}

	
	

	
	
	public void insert(Martyrs  key) {
		root = insert(root, key);
	}
	
	
	private AVL_Node insert(AVL_Node root, Martyrs key) {
		if (root == null) {
			return new AVL_Node(key);
		}
		if (key.getName().trim().compareToIgnoreCase(root.getData().getName()) < 0) {
			root.left  = insert(root.left, key) ;
		}
		
		else {
			root.right  =  insert(root.right , key);
		}
		return balance(root);
	}
	
	
	private AVL_Node balance(AVL_Node  root) {
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

	
	private AVL_Node rotateRightLeft(AVL_Node root) {
		AVL_Node temp = root.right;
		root.right = rotateRight(temp);
		return rotateLeft(root);
	}
	
	
	private AVL_Node rotateLeft(AVL_Node root) {
		AVL_Node temp = root.right;
		root.right = temp.left;
		temp.left = root;
		return temp;
	}
	
	
	private AVL_Node rotateLeftRight(AVL_Node root) {
		AVL_Node temp = root.left ;
		root.left = rotateLeft(temp);
		return rotateRight(root);
	}
	
	
	private AVL_Node  rotateRight(AVL_Node  root) {
		AVL_Node temp = root.left;
		root.left = temp.right;
		temp.right = root;
		return temp;
	}
	
	
	

	private int getBalance(AVL_Node root) {
		if (root == null) {
			return 0;
		}
		return getHeight(root.left) - getHeight(root.right);
	}
	
	private int getHeight(AVL_Node curr) {
		if (curr == null) // it's an empty node
			return 0;
		if (curr.isLeaf())  // 
			return 1;
		else
			return Math.max(1 + getHeight(curr.left ), 1 + getHeight(curr.right));
	}
	
	
	private AVL_Node findMin(AVL_Node node) {
	    AVL_Node current = node;
	    while (current.left != null) {
	        current = current.left;
	    }
	    return current;
	}
	
	public void delete(Martyrs martyr) {
	    root = delete(root, martyr);
	}

	private AVL_Node delete(AVL_Node root, Martyrs martyr) {
	    if (root == null) {
	        return root;
	    }
	    
	    String name = martyr.getName().trim();
	    int age = martyr.getAge();
	    char gender = martyr.getGender();
	    Date date = martyr.getDateOfDeth();
	    
	    int compare = name.trim().compareToIgnoreCase(root.getData().getName().trim());
	    
	    if (compare < 0) {
	        root.left = delete(root.left, martyr);
	    } else if (compare > 0) {
	        root.right = delete(root.right, martyr);
	    } else {
	        // Found the matching node, now check if all information matches
	        if (root.getData().getAge() == age &&
	                root.getData().getGender() == gender &&
	                root.getData().getDateOfDeth().compareTo(date) == 0) {
	            // Delete the node
	            if (root.isLeaf()) {
	                return null;
	            } else if (root.left == null) {
	                return root.right;
	            } else if (root.right == null) {
	                return root.left;
	            } else {
	                AVL_Node successor = findMin(root.right);
	                root.setData(successor.getData());
	                root.right = delete(root.right, successor.getData());
	            }
	        } else {
	            // Information does not match, continue searching in the appropriate subtree
	            if (compare < 0) {
	                root.left = delete(root.left, martyr);
	            } else {
	                root.right = delete(root.right, martyr);
	            }
	        }
	    }

	    return balance(root);
	}
	
	
	
	
	
	public int height() {
		return hieght(root);
	}

	private int hieght( AVL_Node curr) {
		if (curr == null)
			return -1;
		
		else
			return Math.max(hieght(curr.left), hieght(curr.right))+1;
	}
	
	
	// this method to find all node that matching with the name i entered 

	public String findNodesByName(String name) {
	    StringBuilder matchingNames = new StringBuilder();
	    findNodesByName(root, name.trim().toLowerCase(), matchingNames);
	    return matchingNames.toString();
	}

	private void findNodesByName(AVL_Node node, String name, StringBuilder matchingNames) {
	    if (node != null) {
	        String nodeName = node.getData().getName().toLowerCase();
	        if (nodeName.contains(name)) {
	            matchingNames.append(node.getData().toString()).append("\n\n");
	        }
	        findNodesByName(node.left, name, matchingNames);
	        findNodesByName(node.right, name, matchingNames);
	    }
	}

	
  

	// this method to calculate the number of node (number of martyrs)
	public int countNodes() {
	    return countNodes(root);
	}

	private int countNodes(AVL_Node node) {
	    int count = 1;
	    
	    if (node == null)
	    	return 0;
	    else {
	    	count+= countNodes(node.left);
	    	count+= countNodes(node.right);
	    	
	    	return count;

	    }
	}

	
	
	
	public String printLevelByLevel() {
		 StringBuilder stringBuilder = new StringBuilder(); // StringBuilder to save all information 

	    if (root == null) {
	        return null;
	    }
	    
	    int CurrentLevel = 0;
	    int NextLevel = 0;
	    int Level = 1;  // this variable to now the number of current level

	    Queue queue = new Queue();
	    queue.enqueue(root);
	    CurrentLevel++;
	    
	    while (!queue.isEmpty()) {
	        AVL_Node node = queue.dequeue();
	        stringBuilder.append(node.getData().toString() + " \n ");
	         CurrentLevel--;


	         if (node.left != null) {
		            queue.enqueue(node.left);
		            NextLevel++;
		        }
	      

	        if (node.right != null) {
	            queue.enqueue(node.right);
	            NextLevel++;
	        }
	        
	       
	        
	        if (CurrentLevel == 0) {
	        	Level++;
	            stringBuilder.append("\n\n Level "+Level+" : ").append("\n\n");
	            CurrentLevel = NextLevel;
	            NextLevel = 0;
	        }
	        
	        
	        
	        }
	    return stringBuilder.toString();
	    }
	

	
	public void  PostOrder() {
	     PostOrder(root);
	}

	public void  PostOrder(AVL_Node T) {

	    if (T != null) {
	        PostOrder(T.left);
	        PostOrder(T.right);
	         System.out.println(T.getData().toString()); 

	}
	    
	}
	
	public String getPostOrder() {
	    return getPostOrder(root);
	}

	public String getPostOrder(AVL_Node T) {
	    StringBuilder result = new StringBuilder();

	    if (T != null) {
	        result.append(getPostOrder(T.left));
	        result.append(T.getData().toString()).append("\n");
	        result.append(getPostOrder(T.right));
	    }

	    return result.toString();
	}
}

