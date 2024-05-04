package application;

public class Queue {


	private Stack_list Linked = new Stack_list ();

	
	public Queue() {
		super();
	}
	

	public boolean isEmpty()
	{
		return (Linked.isEmptyQ());
	}
	
	
	public void enqueue (AVL_Node order) {
		Linked.insertFirstQ(order);
	}
	
	public AVL_Node dequeue() {
		return Linked.deleteLastQ();
	}


	public Stack_list getLinked() {
		return Linked;
	}


	public void setLinked(Stack_list linked) {
		Linked = linked;
	}
	
}
