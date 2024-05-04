package application;

public class Stack_Node {
	
	private Martyrs data;
	private Stack_Node Next;
	
	
	public Stack_Node() {
		super();
	}
	
	public Stack_Node(Martyrs data) {
		super();
		this.data = data;
	}

	public Martyrs getData() {
		return data;
	}

	public void setData(Martyrs data) {
		this.data = data;
	}

	public Stack_Node getNext() {
		return Next;
	}

	public void setNext(Stack_Node next) {
		Next = next;
	}


	
	
	

}
