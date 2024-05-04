package application;

public class Stack_list {

    private Stack_Node StackrNode;
    private Queue_Node queueNode;
    


	public Stack_list() {
		super();
	}

	public Stack_list(Stack_Node stackrNode) {
		super();
		StackrNode = stackrNode;
	}
	

	public Stack_list(Queue_Node queueNode) {
		super();
		this.queueNode = queueNode;
	}
	
	

	public Stack_list(Stack_Node stackrNode, Queue_Node queueNode) {
		super();
		StackrNode = stackrNode;
		this.queueNode = queueNode;
	}
	

	public Queue_Node getQueueNode() {
		return queueNode;
	}

	public void setQueueNode(Queue_Node queueNode) {
		this.queueNode = queueNode;
	}

	public Stack_Node getStackrNode() {
		return StackrNode;
	}

	public void setStackrNode(Stack_Node stackrNode) {
		StackrNode = stackrNode;
	}
    
    
	// for Stack 

	public void insertFirstS(Martyrs data) { // insert for Stack 
		Stack_Node newNode = new Stack_Node(data);
        if (StackrNode == null) {
        	StackrNode = newNode;
        } else {
        	newNode.setNext(StackrNode);
        	StackrNode = newNode;
        }
    }
	
	public Martyrs deleteFirstS() { // delete from stack 
		if(StackrNode != null) {
			Stack_Node temp = StackrNode;
			StackrNode = StackrNode.getNext();
			return temp.getData();
			
		}
		else 
			return null;
	
	}

	public boolean isEmptyS() {  // is empty for Stack
		return (StackrNode == null);
	}

	// for Queue 
	
	
	public boolean isEmptyQ() {
		return (queueNode == null);
	}
	
	public void insertFirstQ(AVL_Node data) {
		Queue_Node newNode = new Queue_Node(data);
        if (queueNode == null) {
        	queueNode = newNode;
        } else {
        	newNode.setNext(queueNode);
            queueNode = newNode;
        }
    }
	
	public AVL_Node deleteLastQ() // delete last item
	{
		AVL_Node temp;
		Queue_Node current = queueNode;
	 if (queueNode == null)
	      return null;
	 
	 if (queueNode.getNext() == null)
	 {
	      temp = queueNode.getData();
	      queueNode = null;
	      return (temp);
	}
	 
	while (current.getNext().getNext() != null)
	     current = current.getNext();
	   temp = current.getNext().getData();
	   current.setNext(null);;
	   return temp;
	}
	
	
	
	
	
}
