package application;

public class Location {

	AVL_Tree tree = new AVL_Tree();
	AVL2_Tree tree2 = new AVL2_Tree();
	
	private String locationName;

	public Location() {
		super();
	}

	public Location(String locationName) {
		super();
		this.locationName = locationName;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	@Override
	public String toString() {
		return " "+locationName + "";
	}
	
	public int compareTo(Location o) {
		return (this.getLocationName()).compareTo(o.getLocationName());
	}

	public AVL_Tree getTree() {
		return tree;
	}

	public void setTree(AVL_Tree tree) {
		this.tree = tree;
	}

	public AVL2_Tree getTree2() {
		return tree2;
	}

	public void setTree2(AVL2_Tree tree2) {
		this.tree2 = tree2;
	}
	
	
	
	
	
}
