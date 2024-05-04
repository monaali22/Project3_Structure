package application;

public class Stack {

	private Stack_list Linked= new Stack_list ();
	int size = 0;
	
	

	public Stack() {
		super();
	}

	
	public boolean isEmpty()
	{
		return (Linked.isEmptyS());
	}

	
	public void push(Martyrs costamers)
	{
		Linked.insertFirstS(costamers);
		size++;
	}
	
	
	public Martyrs pop()
	{
		size--;
		return (Linked.deleteFirstS());
	}


	public int size() {
		return size;
	}
	
	
	

}
