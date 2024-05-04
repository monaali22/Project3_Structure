package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;


public class Doubly_Circular {

	private Double_Node head ; 

	
	
	 public Doubly_Circular() {
		super();
	}


	public Doubly_Circular(Double_Node head) {
		super();
		this.head = head;
	}


	
	public Double_Node getHead() {
		return head;
	}


	public void setHead(Double_Node head) {
		this.head = head;
	}


	public void insertSort(Location  data) {
		
		 Double_Node newNode = new Double_Node(data);

	        if (head == null) {  // case 1 : add to the empty list
	            newNode.setNext(newNode);
	            newNode.setPrivous(newNode);
	            head = newNode;
	          
	        } 
	        
	        
	        else if (data.compareTo( head.getData()) < 0) {  // case 2: add to Head of the list
	            
	        	Double_Node last = head.getPrivous();
	            newNode.setNext(head);
	            newNode.setPrivous(last);
	            head.setPrivous(newNode);
	            last.setNext(newNode);
	            head = newNode;
	            
	            
	             } 
	        
	        else {
	        	Double_Node current = head.getNext();
	            while (current != head && data.compareTo(current.getData()) > 0) {
	                current = current.getNext();
	            }

	            
	            
	            Double_Node prevNode = current.getPrivous();
	            prevNode.setNext(newNode);
	            newNode.setPrivous(prevNode);
	            newNode.setNext(current);
	            current.setPrivous(newNode);
	        }
	    }
	 
	

	 
	// Remove a node from the doubly circular linked list
	    public void delete (Location data) {
	        // If the list is empty
	        if (head == null) {
	            return;
	        }

	        Double_Node current = head;
	        Double_Node prevNode = null;

	        //  find the node to remove
	        while (current.getData().getLocationName().compareTo(data.getLocationName()) != 0 ) {
	            if (current.getNext() == head) {
	                // If the node to remove is not found
	                return;
	            }
	            prevNode = current;
	            current = current.getNext();
	        }

	        // Remove the node
	        if (current == head) {
	            // If the node to remove is the head
	        	Double_Node last = head.getPrivous();
	            head = head.getNext();
	            last.setNext(head);
	            head.setPrivous(last);
	        } else {
	            // If the node to remove is not the head
	            prevNode.setNext(current.getNext());
	            current.getNext().setPrivous(prevNode);
	            
	        }

	    }
	    
	    
	    
		 // Update the value of a node in the doubly circular linked list
	    public void update(String oldData, String newData) {
	        if (head == null) {
	            System.out.println("The list is empty.");
	            return;
	        }

	        Double_Node current = head;

	        while (true) {
	            if (current.getData().getLocationName().equals(oldData)) {
	                current.getData().setLocationName(newData);
	                return;
	            }
	            current = current.getNext();
	            if (current == head) {
	                break;
	            }
	        }

	        System.out.println("Node not found in the list.");
	    }

		    
		    
		    // Sort the doubly circular linked list in ascending order
		    public void sortList() {
		        // If the list is empty or contains a single element, it is already sorted
		        if (head == null || head.getNext() == head) {
		            return;
		        }

		        Double_Node current = head;
		        Double_Node index = null;
		        String temp;

		        // Perform a bubble sort on the linked list
		        do {
		            index = current.getNext();
		            while (index != head) {
		                if (current.getData().getLocationName().compareTo(index.getData().getLocationName()) > 0 ) {
		                    // Swap the data of the two nodes
		                    temp = current.getData().getLocationName();
		                    current.setData(index.getData()); 
		                    index.setData(new Location (temp));
		                }
		                index = index.getNext();
		            }
		            current = current.getNext();
		        } while (current.getNext()  != head);

		    }
		    public Double_Node search(Location searchData) {
		        if (head == null) {
		            return null; // The list is empty, so the node cannot be found
		        }

		        Double_Node current = head;

		        while (true) {
		            String currentLocationName = current.getData().getLocationName();
		            String searchLocationName = searchData.getLocationName();

		            if (currentLocationName != null && searchLocationName != null &&
		                    currentLocationName.trim().equalsIgnoreCase(searchLocationName.trim())) {
		                return current; // Node found, return it
		            }

		            current = current.getNext();
		            if (current == head) {
		                break;
		            }
		        }

		        return null; // Node not found in the list
		    }

				
	
	 
		    
	// Print the elements of the doubly circular linked list
		    public void printList() {
		        if (head == null) {
		            System.out.println("The list is empty.");
		            return;
		        }

		        Double_Node current = head;
		        System.out.print("List: ");

		        while (true) {
		            System.out.print(current.getData().getLocationName() + " ");
		            current = current.getNext();
		            if (current == head) {
		                break;
		            }
		        }

		        System.out.println();
		    }

	    
		    public void searchByLocation(Location location)
		    {
		    	 if (location == null) 
			            return ; // The list is empty, so the node cannot be found
			        

		    	 Stage stage = new Stage(); // create a new stage 
		    	 TextArea textArea = new TextArea(); // create a new TextArea
		    	 GridPane pane = new GridPane (); 
				 Button back = new Button ("Back");  // this button to back to a before screen 
				 StringBuilder stringBuilder = new StringBuilder(); // StringBuilder to save all information 
				 
				 textArea.setPrefWidth(600);;
				 textArea.setPrefHeight(500);


		    	 pane.setAlignment(Pos.CENTER);
		    	 pane.add(textArea, 0, 0);
		    	 pane.add(back, 3, 7);
		 		 pane.setStyle("-fx-background-color:Linear-gradient(#61D8DE , #E839F6) ");

		         Font newFont = ((Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15)));
					if (search(location) != null) { // if the location is exist

		         Double_Node curr1 = search(location);
		         
		           stringBuilder.append(curr1.getData().getTree().getPostOrder()); // and the information to stringBuilder
		     		textArea.setText(stringBuilder.toString());	// Add all information to a text Area as string 
		     		textArea.setFont(newFont);

		    }
					
					 Scene scene = new Scene(pane , 700 , 600); // create a new scene 
					 stage.setScene(scene); // add the scene to the stage 
					 stage.setTitle("Report for Location "); // set a title to  the stage 
					 stage.show();
					
					 back.setOnAction(e->{
						// Main m = new Main();
						// m.nextStage();
					 });
		    }
}