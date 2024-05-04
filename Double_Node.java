package application;

public class Double_Node {

	private Location data;
	private Double_Node Next;
	private Double_Node privous;
	
	
	public Double_Node() {
		super();
	}


	public Double_Node(Location data) {
		super();
		this.data = data;
	}


	public Location getData() {
		return data;
	}


	public void setData(Location data) {
		this.data = data;
	}


	public Double_Node getNext() {
		return Next;
	}


	public void setNext(Double_Node next) {
		Next = next;
	}


	public Double_Node getPrivous() {
		return privous;
	}


	public void setPrivous(Double_Node privous) {
		this.privous = privous;
	}
	
	
	
}
