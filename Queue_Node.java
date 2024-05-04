package application;

public class Queue_Node {
	private AVL_Node  data;
	private Queue_Node Next;
	
	
	public Queue_Node() {
		super();
	}


	public Queue_Node(AVL_Node data) {
		super();
		this.data = data;
	}


	public AVL_Node getData() {
		return data;
	}


	public void setData(AVL_Node data) {
		this.data = data;
	}


	public Queue_Node getNext() {
		return Next;
	}


	public void setNext(Queue_Node next) {
		Next = next;
	}
	
	



}
