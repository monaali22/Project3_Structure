package application;
	
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

//import java.text.ParseException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;


public class Main extends Application {
	Location location ;
	Doubly_Circular circularList = new Doubly_Circular();
	Martyrs martyrs ;
	DateStack dateStack;
	ComboBox<String> comboBox = new ComboBox<>();
	ComboBox<String> comboBox2 = new ComboBox<>();

	TextField selectlocation = new TextField();

	Stack tempStack = new Stack();
	AVL2_Node AVL_Node2;

	@Override

	public void start(Stage primaryStage) {
		
      // System.out.println("m");

		firstScrren();


	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	 public void readfile(Stage primaryStage) throws ParseException { // this method read all date from a file you choose and save it
   	  // choose a file to read information from it 
   	 FileChooser fileChooser = new FileChooser();
   	 fileChooser.setTitle("Open Resource File ");
   	 fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("*.txt", "*.*")); // show the file in stage
   	 File file = fileChooser.showOpenDialog(primaryStage);
   	 
   	 if (file != null) { // check if the file has data 
   		 try {
				Scanner scanner = new Scanner (file); // read from file 
				while (scanner.hasNextLine()) {
					String Line = scanner.nextLine(); // take line line from file 
					String [] split = Line.split(","); // split the file by ,

					if (split[1] == "") { // if the line has not age 
						split[1] ="0"; // set age zero
					}
						
					String name = split[0]; // take the first index from line 
					int age = Integer.parseInt(split[1]); // take the  index two from line  and convert it to integer
					Date dateOfDeth = new SimpleDateFormat("MM/dd/yyyy").parse(split[3]); // take the  index three from line  and convert it to date
					char gender = split[4].charAt(0);
					String locationName = split[2];
					
					dateStack = new DateStack (dateOfDeth);
					martyrs = new Martyrs (name , age , dateOfDeth ,gender ); // create Martyr object with the data you read from file 
				    location = new Location (locationName); // create a location object with the data you read from file 


				   if (circularList.search(location) == null) { // if the location he read from file not found 
					   comboBox.getItems().add(locationName);
					   comboBox2.getItems().add(locationName);
					   
					   circularList.insertSort(location);// insert location to double list
					   circularList.search(location).getData().getTree().insert(martyrs);// insert Martyr information to single list	
					   if (circularList.search(location).getData().getTree2().find(dateStack) == null ) {

					   circularList.search(location).getData().getTree2().insert(dateStack);	// insert Martyr information to single list			   
					   circularList.search(location).getData().getTree2().find(dateStack).getData().getStack().push(martyrs);
					   }
					   
					   else {
						   circularList.search(location).getData().getTree2().find(dateStack).getData().getStack().push(martyrs);

					   }
				   }
				   else { // if the location he read from file  found 
					   circularList.search(location).getData().getTree().insert(martyrs);// insert Martyr information to single list
					   if (circularList.search(location).getData().getTree2().find(dateStack) == null ) {
						   circularList.search(location).getData().getTree2().insert(dateStack);
						   circularList.search(location).getData().getTree2().find(dateStack).getData().getStack().push(martyrs);
					   }
					   else {
						   circularList.search(location).getData().getTree2().find(dateStack).getData().getStack().push(martyrs);

					   }
				   }
					
					}
				scanner.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
   		 
   	 }
   	
}
	  
	
	public Pane firstScrren() {
		
		Stage stage = new Stage ();
		 BorderPane borderpane = new BorderPane(); // create a new Pane 
		 Image image1 = new Image("https://etimg.etb2bimg.com/photo/71884644.cms"); // add image to the scene 
		 ImageView imageView = new ImageView(image1);
		 Label label1 = new Label (); // create a label
		 Button button1 = new Button ("Read File"); // button to read from file 
		 Button button2 = new Button ("Next"); // this button show the second  screen 
		 HBox hBox = new HBox(30);

		// button2.setVisible(false);
		 
		 button1.setPrefWidth(100);
		 button2.setPrefWidth(100);
		 
		 hBox.setAlignment(Pos.CENTER);
		 hBox.getChildren().addAll(button1 ,button2 );
		 
		 		 
		 label1.setText(" Palestinian martyrs "); // add text to the label
		 label1.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,30)); // set font to the label
		 
		 
		 imageView.setFitWidth(500);
		 imageView.setFitHeight(400);
		 
		 
		 borderpane.setPadding(new Insets (5 ,5,5,5));
		 borderpane.setCenter(imageView); // add imageView to pane 
		 borderpane.setTop(label1); // add label1 to the pane 
		 borderpane.setBottom(hBox); // add hBox to pane 
		 borderpane.setStyle("-fx-background-color:LIGHTSKYBLUE");
		 //borderpane.setStyle("-fx-background-color:Linear-gradient(#61D8DE , #E839F6) ");

		 
		 
		 button1.setOnAction(e->{ // button 1 action 
			
			 try {
				readfile(stage);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			
		 });
		 
		 button2.setOnAction(e->{ // button 2 action 
			nextStage () ;
		
			 
	});
		
		 
		 Scene scene = new Scene (borderpane , 700 ,600);
		 stage.setScene(scene);
		 stage.show();
		 
		 return borderpane;
		
	}
	
	
	
	public Pane nextStage () {  // this method to show  interface 
   	    Stage stage = new Stage ();
	    BorderPane border = new BorderPane();
	    Button back = new Button ("Back");

	    back.setMinWidth(200);
	    back.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,20)); // set font to the label
	    

 	    TabPane tabPane = new TabPane(); // create a new Tab pane 
 	    Tab tab1 = new Tab("Location Screen"); // create tab 1
 	    Tab tab2 = new Tab("Martyrs Screen"); // create tab 2
 	    Tab tab3 = new Tab("Statistics Screen"); // create tab 3
 	    Tab tab4 = new Tab("Read Orders"); // create tab 3


 	  
 	    tabPane.getTabs().addAll( tab1 , tab2 ,tab3 , tab4); // add all tab to the tab pane 
		border.setTop(tabPane);	 // add the Tab pane to the border pane 
		border.setBottom(back);
		border.setStyle("-fx-background-color:lightcyan");
		//border.setStyle("-fx-background-color:Linear-gradient(#61D8DE , #E839F6) ");

		tab1.setContent(locationScrren());
		tab2.setContent(martyrsScrren());
		tab3.setContent(StatisticsScreen());
		tab4.setContent(file());
		
		Scene scene = new Scene(border , 700 ,600);


		
		back.setOnAction(e->{
			firstScrren ();
			});
		
		stage.setScene(scene);
		stage.setTitle("Admin Screen");
		stage.show();
		return border;
   	 }
	

	public Pane locationScrren()  // This method to do operation in Location 
	{
		 
   		GridPane gridPane = new GridPane(); // create a new GridPane
   		BorderPane pane = new BorderPane();
		Label label2 = new Label();
		Label label = new Label();
		Label labe3 = new Label();
		Label labe4 = new Label();


		TextField text = new TextField();
		TextField newText = new TextField();

		HBox hBox = new HBox(10);
		HBox hBox1 = new HBox(10);
		HBox hBox2 = new HBox(10);
		HBox hBox3 = new HBox(10);

		VBox vBox = new VBox(15);

		comboBox.setPrefWidth(150);
		
		
		  // Create a for Radio Button 
		Button button1 = new Button(" Add "); //  RadioButton 1 
		Button button2 = new Button("Delete"); // RadioButton 2
		Button button3 = new Button("Update"); //RadioButton 3
		Button button4 = new Button("Search"); // RadioButton 4
		Button Done = new Button("Done"); // RadioButton 4


				
	    button1.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,13));
		button2.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,13));
	    button3.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,13));
		button4.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,13));
		
		hBox.setAlignment(Pos.CENTER);
		hBox.getChildren().addAll(label , text , button1) ;
		
		hBox1.setAlignment(Pos.CENTER);
		hBox1.getChildren().addAll(labe3 , comboBox) ;
		
		hBox2.setAlignment(Pos.CENTER);
		hBox2.getChildren().addAll(button2, button3 , button4) ;
		
		hBox3.setAlignment(Pos.CENTER);
		hBox3.getChildren().addAll(labe4 ,newText , Done ) ;
		hBox3.setVisible(false);
		
		vBox.setAlignment(Pos.CENTER);
		vBox.getChildren().addAll(hBox , hBox1);
				
		label2.setText( "Please Select which Operations you want\r\n"); // add text to the label
		label2.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,20)); // set font to the label
		label2.setStyle("-fx-text-fill:blue");// set colour to the label font 
		
		label.setText( "New Location Name"); // add text to the label
		label.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15)); // set font to the label
		label.setStyle("-fx-text-fill:black");// set colour to the label font 
		
		labe3.setText( "Locations "); // add text to the label
		labe3.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15)); // set font to the label
		labe3.setStyle("-fx-text-fill:black");// set colour to the label font 
		
		labe4.setText( "New Name"); // add text to the label
		labe4.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,12)); // set font to the label
		labe4.setStyle("-fx-text-fill:red");// set colour to the label font 
		
		gridPane.setPadding(new Insets (5 ,5,5,5));
		gridPane.setHgap(20); //horizontal gap in pixels
		gridPane.setVgap(10); //vertical gap in pixels
		gridPane.add(label2, 0, 0);
		gridPane.add(vBox, 0, 1);
		gridPane.add(hBox2, 0, 8);
	//	gridPane.add(imageView, 0, 15);

		gridPane.add(hBox3, 0, 10);
		gridPane.setStyle("-fx-background-color: lightcyan ");
		//gridPane.setStyle("-fx-background-color:Linear-gradient(#61D8DE , #E839F6) ");

		pane.setBottom(gridPane);


		button1.setOnAction(e->{ // Add button for the location 
			
			if (circularList.search(new Location (text.getText())) == null) { // this statement to check if the location is exist before 
				circularList.insertSort(new Location (text.getText())); // add the new location to the double list
				comboBox.getItems().add(text.getText());
				comboBox2.getItems().add(text.getText());
			}
		});
		
		
		
		button2.setOnAction(e->{  // delete button 
			String selectBrand = (String) comboBox.getSelectionModel().getSelectedItem();
			location = new Location(selectBrand);
			
			if (comboBox.getSelectionModel().getSelectedItem() != null) {
				if (circularList.search(location).getData().getTree() == null) {
				circularList.delete(new Location(comboBox.getSelectionModel().getSelectedItem()));
				comboBox.getItems().remove(selectBrand);
				comboBox2.getItems().remove(selectBrand);
			}
			}
		});
		
		
		button3.setOnAction(e->{  // update button
			if (comboBox.getSelectionModel().getSelectedItem() != null) {
			hBox3.setVisible(true);
			
			
			}
			
		});
		
		Done.setOnAction(e->{      // update Button 
			String selectBrand = (String) comboBox.getSelectionModel().getSelectedItem();
			circularList.update(selectBrand, newText.getText()); // update a location name 
			comboBox.getItems().remove(selectBrand);
			comboBox2.getItems().remove(selectBrand);
			comboBox.getItems().add(newText.getText());
			comboBox2.getItems().add(newText.getText());


		});
		
		button4.setOnAction(e->{  // search button 
			String selectBrand = (String) comboBox.getSelectionModel().getSelectedItem();

			circularList.searchByLocation(new Location(selectBrand));
		});
		
		
		
   		 return gridPane ;
   	 }
	
	public Pane martyrsScrren()
	{
	    Stage stage = new Stage();
   		GridPane pane = new GridPane();
   	    HBox hBox = new HBox(10); // create a HBox
   		Label labe2 = new Label(" Selected which option you need to do "); // create a label 

   		


      	// Create a for Radio Button 
   		RadioButton button1 = new RadioButton("Insert a new martyr"); //  RadioButton 1 
   		RadioButton button2 = new RadioButton("Update a martyr"); // RadioButton 2
   		RadioButton button3 = new RadioButton("Delete a martyr"); //RadioButton 3
   		RadioButton button4 = new RadioButton("Search a martyr"); // RadioButton 4
   		
   		ToggleGroup group = new ToggleGroup(); // create ToggleGroup and put all button inside it 
   		button1.setToggleGroup(group);
   		button2.setToggleGroup(group);
   		button3.setToggleGroup(group);
   		button4.setToggleGroup(group);

   		button1.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15));
   		button2.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15));
   		button3.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15));
   		button4.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15));

   		labe2.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,25)); // set font to the label
   		labe2.setStyle("-fx-text-fill:blue");// set colour to the label font 
   		
    	hBox.setAlignment(Pos.CENTER); // put a hBox in the centre
        hBox.getChildren().addAll(button1 , button2 , button3 , button4 ); // add all button to the HBox
   		 
        pane.setHgap(5);
		pane.setVgap(10);
		pane.setAlignment(Pos.CENTER); // put the pan in the Centre 
		pane.add(labe2, 0, 0); // add the label to the pane  
		pane.add(hBox, 0, 7); // add the HBox to the pane 
		pane.setStyle("-fx-background-color: lightcyan ");
		
		
		button1.setOnAction(e->{

			if (button1.isSelected()) {
				Scene scene1 = new Scene(insertNewMartyrs() , 700 ,600);
				stage.setScene(scene1);
				stage.setTitle("Insert a new Martyrs ");
				stage.show();
			}
			
			
		});
		
		button3.setOnAction(e->{
			if (button3.isSelected()) {
				Scene scene1 = new Scene(deletetMartyrs(), 700 ,600);
				stage.setScene(scene1);
				stage.setTitle("Delete a new Martyrs ");
				stage.show();
			}
			
		});
		button2.setOnAction(e->{
			if (button2.isSelected()) {
				Scene scene1 = new Scene(updateNewMartyrs() , 700 ,600);
				stage.setScene(scene1);
				stage.setTitle("Update a new Martyrs ");
				stage.show();
			}
		});
		
		button4.setOnAction(e->{
			if (button4.isSelected()) {
				Scene scene1 = new Scene(SearchForMartyrsByName(), 700 ,600);
				stage.setScene(scene1);
				stage.setTitle("search ");
				stage.show();
			}
			
		});
   		 return pane ;
   		 
	}
	

   		 public Pane insertNewMartyrs() { // In this method the admin can insert new Cars
   	   		 GridPane pane = new GridPane ();
   	   		 Label label = new Label ("Location ");
   	   		 Label titl = new Label (" Insert Martyrs  : ");
   	   		 

   	   		ComboBox<String> genderBox = new ComboBox<>();
   	   		genderBox.getItems().add("F");
   	   		genderBox.getItems().add("M");
   	   		genderBox.setPrefWidth(150);
   	   		

   	   		 Label Name = new Label (" Name ");
   	   		 Label Age = new Label (" Age");
   	   		 Label Date = new Label (" Date ");
   	   		 Label Gender = new Label (" Gender");
   	   		 
   	   		 Button insert = new Button ("Insert");
   	   		 Button back = new Button ("Back");

   	   		 
   	 		TextField nametext = new TextField();
   			TextField agetext = new TextField();
   			TextField datetext = new TextField();
   			

   			titl.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,30)); // set font to the label
   			titl.setStyle("-fx-text-fill:blue");// set colour to the label font 
   	   		 


   			label.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,20)); // set font to the label
   			label.setStyle("-fx-text-fill:red");// set colour to the label font 
   	   		 
   			Name.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15)); // set font to the label
   			Name.setStyle("-fx-text-fill:black");// set colour to the label font 
   			Age.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15)); // set font to the label
   			Age.setStyle("-fx-text-fill:black");// set colour to the label font 
   			Date.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15)); // set font to the label
   			Date.setStyle("-fx-text-fill:black");// set colour to the label font 
   			Gender.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15)); // set font to the label
   			Gender.setStyle("-fx-text-fill:black");// set colour to the label font 
   	   		 

   	   		 HBox hBox = new HBox(20);
   	   		 
   	   		 hBox.setAlignment(Pos.CENTER);
   	   		 hBox.getChildren().addAll(insert , back);
   	   		 
   	   		pane.setHgap(30);
   	   		pane.setVgap(30);
   	   		pane.setPadding(new Insets (5,5,5,5));
   	   		pane.add(titl, 1, 0);   // add tilt to the pane 
   	   		pane.add(label, 0, 1);  // add label to the pane 
   	   		pane.add(Name, 0, 2);  // add model to the pane 
   	   		pane.add(Age, 0, 3);  // add colour to the pane 
   	   		pane.add(Date, 0, 4);   // add year to the pane 
   	   		pane.add(Gender, 0, 5);   // add price to the pane 
   	   		
   	   		pane.add(comboBox, 1, 1);    // add comboBox to the pane 
   	   		pane.add(nametext, 1, 2);     // add model text to the pane 
   	   		pane.add(agetext, 1, 3);    // add colour text to the pane 
   	   		pane.add(datetext, 1, 4);       // add year text to the pane 
   	   		pane.add(genderBox, 1, 5);     // add price text to the pane 
   	   		pane.add(hBox, 1, 6);


   	   		
   	   		 
   	   	back.setOnAction(e->{
   	   	//print ();
   	   	nextStage ();
   	   		
   	 //  	printStack();

   	   	});
   	   	
   	   	insert.setOnAction(e->{
   	   	if (comboBox.getSelectionModel().getSelectedItem() != null) {
		        String locationName  = (String) comboBox.getSelectionModel().getSelectedItem();
		        location = new Location(locationName);        
   	   	}
   	   	
   	  

		try {
			SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy"); // to convert string to date
			if (circularList.search(location) != null) {
				
				martyrs = new Martyrs(nametext.getText(),Integer.parseInt(agetext.getText()),format.parse(datetext.getText()),genderBox.getSelectionModel().getSelectedItem().charAt(0));
				dateStack = new DateStack(format.parse(datetext.getText()));
			// insert new Martyr to AVL 1 
				circularList.search(location).getData().getTree().insert(martyrs);
				
			// insert new Martyr to AVL 2 
				
				  if (circularList.search(location).getData().getTree2().find(dateStack) == null ) {
					   circularList.search(location).getData().getTree2().insert(dateStack);
					   circularList.search(location).getData().getTree2().find(dateStack).getData().getStack().push(martyrs);
				   }
				   else {
					   circularList.search(location).getData().getTree2().find(dateStack).getData().getStack().push(martyrs);

				   }
	
				
			}
		} catch (ParseException e1) {
			e1.printStackTrace();
		
		}
	
		        
   	   	});
   	   		 
   	   		 return pane ;
   	   	 
	}
   		 
   		public Pane deletetMartyrs(){  // This method to remove cars from single list
   	   	 GridPane pane = new GridPane ();  // create a Grid pane 
   			 Label label = new Label ("Location");  // Create new label 
   			 Label titl = new Label ("Delete  Martyrs : ");

   			 // create four label 

   	   		 Label Name = new Label (" Name ");
   	   		 Label Age = new Label (" Age");
   	   		 Label Date = new Label (" Date ");
   	   		 Label Gender = new Label (" Gender");
   	   		 
   			 Button Delete = new Button ("Delete");
   			 Button back = new Button ("Back");

   			 // create four text filed 
   			 
    	 		TextField nametext = new TextField();
    			TextField agetext = new TextField();
    			TextField datetext = new TextField();
   		
    			ComboBox<String> genderBox = new ComboBox<>();
       	   		genderBox.getItems().add("F");
       	   		genderBox.getItems().add("M");
       	   		genderBox.setPrefWidth(150);

   		titl.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,30)); // set font to the label
   		titl.setStyle("-fx-text-fill:blue");// set colour to the label font 
   			 

   		// set front and colour  for  the label 

   		label.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,20)); // set font to the label
   		label.setStyle("-fx-text-fill:red");// set colour to the label font 
   			 
   		// set front and colour  for all the label 
   		Name.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15)); // set font to the label
   		Name.setStyle("-fx-text-fill:black");// set colour to the label font 
   		Age.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15)); // set font to the label
   		Age.setStyle("-fx-text-fill:black");// set colour to the label font 
   		Date.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15)); // set font to the label
   		Date.setStyle("-fx-text-fill:black");// set colour to the label font 
   		Gender.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15)); // set font to the label
   		Gender.setStyle("-fx-text-fill:black");// set colour to the label font 
   			 

   			 HBox hBox = new HBox(20);
   			 
   			 hBox.setAlignment(Pos.CENTER);
   			 hBox.getChildren().addAll(Delete , back);
   			 
   			 // add All label and text Field to the pane 
   			pane.setHgap(30);
   			pane.setVgap(30);
   			pane.setPadding(new Insets (5,5,5,5));
   			pane.add(titl, 1, 0);
   			pane.add(label, 0, 1);
   			pane.add(Name, 0, 2);
   			pane.add(Age, 0, 3);
   			pane.add(Date, 0, 4);
   			pane.add(Gender, 0, 5);
   			
   			pane.add(comboBox, 1, 1);
   			pane.add(nametext, 1, 2);
   			pane.add(agetext, 1, 3);
   			pane.add(datetext, 1, 4);
   			pane.add(genderBox, 1, 5);
   			pane.add(hBox, 1, 6);


   			

   			back.setOnAction(e->{  // action for the back button to return to the before stage 
   				nextStage ();
   				//printStack() ;
   				//print ();
   			});
   			
   			Delete.setOnAction(e -> {
   			    if (comboBox.getSelectionModel().getSelectedItem() != null) {
   			        String locationName = (String) comboBox.getSelectionModel().getSelectedItem();
   			        location = new Location(locationName);
   			    }

   			    try {
   			        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
   			        if (circularList.search(location) != null) {
   			            String name = nametext.getText();
   			            int age = Integer.parseInt(agetext.getText());
   			            Date date = format.parse(datetext.getText());
   			            char gender = genderBox.getSelectionModel().getSelectedItem().charAt(0);
   			             martyrs = new Martyrs(name, age, date, gender);
   			             
   			             // delete from AVL 1: 
   			             circularList.search(location).getData().getTree().delete(martyrs);
   			             
   			       // delete from AVL 2:
   			          dateStack = new DateStack(date);
   			          AVL_Node2 = circularList.search(location).getData().getTree2().find(dateStack);

   			          if (AVL_Node2 != null) {
   			        	  
   			            while (!AVL_Node2.getData().getStack().isEmpty()) {
   			              martyrs = AVL_Node2.getData().getStack().pop();
   			              if (!martyrs.getName().trim().equalsIgnoreCase(name) || martyrs.getAge() != age || martyrs.getGender() != gender) {
   			                tempStack.push(martyrs);
   			              } 
   			              else {
   			                  break;
   			              }
   			            }

   			            while (!tempStack.isEmpty()) {
   			              AVL_Node2.getData().getStack().push(tempStack.pop());
   			            }
   			          }
   			          
   			          if (AVL_Node2.getData().getStack().isEmpty()) {
   			        	circularList.search(location).getData().getTree2().delete(dateStack);
   			        	  
   			          }
   			          }
   			    } catch (ParseException e1) {
   			        e1.printStackTrace();
   			    }
   			});

   			 
   			 
   			 return pane ;
   	   		
   	   	}
   	 public  Pane updateNewMartyrs() {   // this method to update care information 
		 GridPane pane = new GridPane ();   // create a new Grid pane 
		 Label label = new Label ("Location");   // Create  a new label 
		 Label titl = new Label ("Update Nartyrs  : "); // Create  a new label 

		 
		 // create 8 label for old data and new data
		 Label Name = new Label ("Name");
		 Label Age = new Label ("Age");
		 Label Date = new Label ("Date");
		 Label Gender = new Label ("Gender");
		 Label newName = new Label ("New Martyr name ");
		 Label newAge = new Label ("New Martyr age");
		 Label newDate = new Label ("New Martyr Date");
		 Label newGender = new Label ("New Martyr Gender");
		 
		 // create two button 
		 Button Updat = new Button ("Updat");
		 Button back = new Button ("Back");

		 // create 8 Text Field for old data and new data
	     TextField nametext = new TextField();
	     TextField agetext = new TextField();
	     TextField datetext = new TextField();
	
	TextField newNametext = new TextField();
	TextField newAgetext = new TextField();
	TextField newDatetext = new TextField();
	

  		ComboBox<String> genderBox = new ComboBox<>();
  		genderBox.getItems().add("F");
  		genderBox.getItems().add("M");
  		
  		ComboBox<String> genderBox2 = new ComboBox<>();
  		genderBox2.getItems().add("F");
  		genderBox2.getItems().add("M");

	

	titl.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,30)); // set font to the label
	titl.setStyle("-fx-text-fill:blue");// set colour to the label font 
		 

   // set font and colour to all labels
	label.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,20)); // set font to the label
	label.setStyle("-fx-text-fill:red");// set colour to the label font 
		 
	Name.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15)); // set font to the label
	Name.setStyle("-fx-text-fill:black");// set colour to the label font 
	Age.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15)); // set font to the label
	Age.setStyle("-fx-text-fill:black");// set colour to the label font 
	Date.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15)); // set font to the label
	Date.setStyle("-fx-text-fill:black");// set colour to the label font 
	Gender.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15)); // set font to the label
	Gender.setStyle("-fx-text-fill:black");// set colour to the label font 
	
	newName.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15)); // set font to the label
	newName.setStyle("-fx-text-fill:red");// set colour to the label font 
	newAge.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15)); // set font to the label
	newAge.setStyle("-fx-text-fill:red");// set colour to the label font 
	newDate.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15)); // set font to the label
	newDate.setStyle("-fx-text-fill:red");// set colour to the label font 
	newGender.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15)); // set font to the label
	newGender.setStyle("-fx-text-fill:red");// set colour to the label font 
	
		 

		 HBox hBox = new HBox(20);
		 
		 hBox.setAlignment(Pos.CENTER);
		 hBox.getChildren().addAll(Updat , back);
		 
		 // add all control to the pane 
		pane.setHgap(10);
		pane.setVgap(30);
		pane.setPadding(new Insets (5,5,5,5));
		pane.add(titl, 1, 0);
		pane.add(label, 0, 1);
		pane.add(Name, 0, 2);
		pane.add(Age, 0, 3);
		pane.add(Date, 0, 4);
		pane.add(Gender, 0, 5);
		
		pane.add(comboBox, 1, 1);
		pane.add(nametext, 1, 2);
		pane.add(agetext, 1, 3);
		pane.add(datetext, 1, 4);
		pane.add(genderBox, 1, 5);
		pane.add(hBox, 1, 6);
		
		pane.add(newName, 2, 2);
		pane.add(newAge, 2, 3);
		pane.add(newDate, 2, 4);
		pane.add(newGender, 2, 5);
		
		pane.add(newNametext, 3, 2);
		pane.add(newAgetext, 3, 3);
		pane.add(newDatetext, 3, 4);
		pane.add(genderBox2, 3, 5);
		
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy"); // to convert string to date

	
		
		Updat.setOnAction(e->{
			String N = nametext.getText();
			int A = Integer.parseInt(agetext.getText());
			char G = genderBox.getSelectionModel().getSelectedItem().charAt(0);
			
			String newN = newNametext.getText();
			int newA = Integer.parseInt(newAgetext.getText());
			char newG = genderBox2.getSelectionModel().getSelectedItem().charAt(0);
			
			
			if (comboBox.getSelectionModel().getSelectedItem() != null) {
		        String locationName  = (String) comboBox.getSelectionModel().getSelectedItem();
		        location = new Location(locationName);        
   	   	}
			
			try {
				Date date = format.parse(datetext.getText());
				dateStack = new DateStack(date);
			    AVL_Node2 = circularList.search(location).getData().getTree2().find(dateStack);
			    
			    // AVL:1
				circularList.search(location).getData().getTree().delete(new Martyrs(N,A,format.parse(datetext.getText()),G));
				circularList.search(location).getData().getTree().insert(new Martyrs(newN,newA,format.parse(newDatetext.getText()),newG));

				// AVL:2
				if (AVL_Node2 != null) {
					boolean temp = true ;
			            while (!AVL_Node2.getData().getStack().isEmpty()) {
			              martyrs = AVL_Node2.getData().getStack().pop();
			              if (!martyrs.getName().trim().equalsIgnoreCase(N) || martyrs.getAge() != A || martyrs.getGender() != G) {
			                tempStack.push(martyrs);
			                temp = false;
			              } 
			              else {
			            	  temp = true;
			                  break;
			              }
			            }

			            while (!tempStack.isEmpty()) {
			              AVL_Node2.getData().getStack().push(tempStack.pop());
			            }
			            
			            DateStack data = new DateStack(format.parse(newDatetext.getText()));
			           
			            	
			            if (temp == true ) {
			            	if (circularList.search(location).getData().getTree2().find(data) == null ) {
								   circularList.search(location).getData().getTree2().insert(data);
								   circularList.search(location).getData().getTree2().find(data).getData().getStack().push(new Martyrs(newN,newA,format.parse(newDatetext.getText()),newG));
							   }
							   else {
								   circularList.search(location).getData().getTree2().find(new DateStack(format.parse(newDatetext.getText()))).getData().getStack().push(new Martyrs(newN,newA,format.parse(newDatetext.getText()),newG));

							   }
			            }
			            	
			            
			          }
			          
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		});

		


		back.setOnAction(e->{  // action to return to the before screen 
			print ();
			//print2 ();
			//nextStage ();
		});
		
		 
		 
		 return pane ;
   		
	 }
 
	 
	 
   		
   		public Pane SearchForMartyrsByName() {
   			GridPane pane = new GridPane();
   			Label Name = new Label("Search for martyrs by part of name ");
	    	TextArea textArea = new TextArea(); // create a new TextArea
   			
   			Name.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,20)); // set font to the label
   	   		Name.setStyle("-fx-text-fill:red");// set colour to the label font
   	   		
   			Label locationLabel = new Label("Location");
   			Label NameLabel = new Label("Name");
   			
   			TextField text = new TextField();
   			
   			HBox box1 = new HBox(20);
   			HBox box2 = new HBox(20);
   			HBox box3 = new HBox(20);

   			Button search = new Button ("Search");
   			Button back = new Button("Back");
   			
   		    textArea.setPrefWidth(600);;
		    textArea.setPrefHeight(500);
   
   			box1.setAlignment(Pos.CENTER);
   			box2.setAlignment(Pos.CENTER);
   			box3.setAlignment(Pos.CENTER);
   			
   			box1.getChildren().addAll(locationLabel , comboBox);
   			box2.getChildren().addAll(NameLabel , text);
   			box3.getChildren().addAll(back , search);

   			
   			pane.setHgap(60);
   			pane.setVgap(40);
   			pane.setPadding(new Insets (5,5,5,5));
   			
   			pane.add(Name, 2, 2);   
   			pane.add(box1, 2, 3);
   			pane.add(box2, 2, 4);
   			pane.add(box3, 2, 5);
	 		pane.setStyle("-fx-background-color:Linear-gradient(#61D8DE , #E839F6) ");

   			
   			search.setOnAction(e->{
   				
   				if (comboBox.getSelectionModel().getSelectedItem() != null) {
   					Stage stage = new Stage();
   			        String locationName  = (String) comboBox.getSelectionModel().getSelectedItem();
   			        location = new Location(locationName);   
   			        GridPane p = new GridPane();
   			       textArea.appendText(circularList.search(location).getData().getTree().findNodesByName(text.getText()));
   			       p.setAlignment(Pos.CENTER);
   			       p.add(textArea, 0, 0);
   			       p.add(back, 7, 6);
   			       
		 		   p.setStyle("-fx-background-color:Linear-gradient(#61D8DE , #E839F6) ");


   			       Scene scen = new Scene(p ,700,600);
   			       stage.setScene(scen);
   			       stage.show();

   	   	   	}
   	   	   	
   	   	  

   				
   			});
   			
   			back.setOnAction(e->{
   				nextStage();
   			});
   		
   			
   			return pane ;
   		}
   		
   		
   		public Pane StatisticsScreen() {
   			
   			GridPane pane = new GridPane ();
   			Stage stage = new Stage ();
   			
   			
   			
   			Button S1 = new Button("The numbers of martyrs ");
   			Button S2 = new Button("Traverse the 1st AVL level-by-level");
   			Button S3 = new Button("The height of the 1st AVL tree");
   			Button S4 = new Button("Traverse the 2nd AVL backward");
   			Button S5 = new Button("Report the date that had the maximum number of martyrs");
   			Button S6 = new Button("The height of the 2nd AVL tree");
   			
   			
   			S1.setPrefWidth(500);
   			S2.setPrefWidth(500);
   			S3.setPrefWidth(500);
   			S4.setPrefWidth(500);
   			S5.setPrefWidth(500);
   			S6.setPrefWidth(500);

   			
   			S1.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15));
   			S1.setStyle("-fx-text-fill:red");  	
   			S2.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15));
   			S2.setStyle("-fx-text-fill:red");  	
   			S3.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15));
   			S3.setStyle("-fx-text-fill:red");  	
   			S4.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15));
   			S4.setStyle("-fx-text-fill:red");
   			S5.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15));
   			S5.setStyle("-fx-text-fill:red");  	
   			S6.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15));
   			S6.setStyle("-fx-text-fill:red");  	
   			
   			pane.setHgap(5);
   			pane.setVgap(40);
   			pane.setAlignment(Pos.CENTER); // put the pan in the Centre
   			pane.setPadding(new Insets (30,10,10,5));

   			pane.add(comboBox2, 0, 0);
   			pane.add(S1, 0, 1);
   			pane.add(S2, 0, 2);
   			pane.add(S3, 0, 3);
   			pane.add(S4, 0, 4);
   			pane.add(S5, 0, 5);
   			pane.add(S6, 0, 6);
   			

   			
   			
   			pane.setStyle("-fx-background-color: lightcyan ");

   			S1.setOnAction(e->{
   				numbersOfMartyrs();
   			
   			});
   			S3.setOnAction(e->{
   				TheHeightOfTree();
   			});
   			
   			S2.setOnAction(e->{
   	   			levelByLevelStage ();

   			});
   			S4.setOnAction(e->{
   	   			TraverseBackward () ;

   			});
   			S5.setOnAction(e->{
   	   			maxDate ();

   			});
   			
   			
   			
   			S6.setOnAction(e->{
   				//printStack();
   				print2 ();
   			});
   			
   			
   			return pane ;
}


   		
   		public void TheHeightOfTree() {
   			 Stage stage = new Stage(); // create a new stage 
	    	 TextArea textArea = new TextArea(); // create a new TextArea
	    	 GridPane pane = new GridPane (); 
			 StringBuilder stringBuilder = new StringBuilder(); // StringBuilder to save all information 
			 HBox hBox = new HBox (50);
			 
			 
			 Button back = new Button ("Back");  // this button to back to a before screen 
			 Button next = new Button ("Next");  // this button to back to a before screen 
			 Button previous = new Button ("Previous");  // this button to back to a before screen 
			 
			 next.setPrefWidth(150);
			 previous.setPrefWidth(150);
			 back.setPrefWidth(150);
			 
			 next.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15));
			 previous.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15));
			 back.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15));

			 Label lo = new Label();
			 Label titel = new Label("The height of the 1st and 2nd AVL tree");
			 titel.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,25));
			 titel.setStyle("-fx-text-fill:red"); 

			 


			 hBox.setAlignment(Pos.CENTER);
			 hBox.getChildren().addAll(previous , next , back);
			 
			 textArea.setPrefWidth(400);;
			 textArea.setPrefHeight(400);


	    	 pane.setAlignment(Pos.CENTER);
	    	 pane.setVgap(15);
	    	 pane.setHgap(15);
	    	 pane.add(titel, 0, 0);
	    	 pane.add(textArea, 0, 1);
	    	 pane.add(hBox, 0, 2);
	 		 pane.setStyle("-fx-background-color:Linear-gradient(#61D8DE , #E839F6) ");

	         Font newFont = ((Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15)));
	         
	         Scene scene = new Scene(pane , 700 , 600); // create a new scene 
			 stage.setScene(scene); // add the scene to the stage 
			 stage.setTitle("Report for Location "); // set a title to  the stage 
			 stage.show();
			 
			 Double_Node foundNode = circularList.search( new Location(comboBox2.getSelectionModel().getSelectedItem()));

			 if (foundNode != null ) {
				 lo.setText(foundNode.getData().getLocationName().toString());
				 stringBuilder.append(lo.getText()).append("\n\n");
			   //  stringBuilder.append(foundNode.getData().getTree().height());
			 } else {
			     stringBuilder.append("No height information available"); // or any other appropriate message
			 }

			 textArea.setText(stringBuilder.toString());
			 textArea.setFont(newFont);
			 
			 
   		
			 final Double_Node[] currentNode = { null };

			 next.setOnAction(e -> {
			     // Clear the stringBuilder and set an empty string to the text area
			     stringBuilder.setLength(0);
			     textArea.setText("");

			     if (currentNode[0] == null) {
			         Double_Node g = circularList.search(new Location(comboBox2.getSelectionModel().getSelectedItem()));
			         currentNode[0] = g;
			     } else {
			         // If currentNode is not null, get the next node
			         currentNode[0] = currentNode[0].getNext();
			     }

			     if (currentNode[0] != null) {
			         lo.setText(currentNode[0].getData().getLocationName().toString());
			         stringBuilder.append(lo.getText()).append(" ( First AVL )").append("\n\n");
			         stringBuilder.append(currentNode[0].getData().getTree().height()).append("\n\n");
			         stringBuilder.append("Second AVL").append("\n\n");
			         stringBuilder.append(currentNode[0].getData().getTree2().height()).append("\n\n");
			     } else {
			         stringBuilder.append("No height information available"); // or any other appropriate message
			     }

			     // Set the updated content to the text area
			     textArea.setText(stringBuilder.toString());
			     textArea.setFont(newFont);
			 });
			 
			 previous.setOnAction(e -> {
			     // Clear the stringBuilder and set an empty string to the text area
			     stringBuilder.setLength(0);
			     textArea.setText("");

			     if (currentNode[0] == null) {
			         Double_Node g = circularList.search(new Location(comboBox2.getSelectionModel().getSelectedItem()));
			         currentNode[0] = g;
			     } else {
			         // If currentNode is not null, get the next node
			         currentNode[0] = currentNode[0].getPrivous();
			     }

			     if (currentNode[0] != null) {
			         lo.setText(currentNode[0].getData().getLocationName().toString());
			         stringBuilder.append(lo.getText()).append(" ( First AVL )").append("\n\n");
			         stringBuilder.append(currentNode[0].getData().getTree().height()).append("\n\n");
			         stringBuilder.append("Second AVL").append("\n\n");
			         stringBuilder.append(currentNode[0].getData().getTree2().height()).append("\n\n");
			     } else {
			         stringBuilder.append("No height information available"); // or any other appropriate message
			     }

			     // Set the updated content to the text area
			     textArea.setText(stringBuilder.toString());
			     textArea.setFont(newFont);
			 });

			 back.setOnAction(e->{
				 nextStage();
			 });
   			
   		}

   		public void numbersOfMartyrs () {
  			 Stage stage = new Stage(); // create a new stage 
	    	 TextArea textArea = new TextArea(); // create a new TextArea
	    	 GridPane pane = new GridPane (); 
			 StringBuilder stringBuilder = new StringBuilder(); // StringBuilder to save all information 
			 HBox hBox = new HBox (50);
			 
			 
			 Button back = new Button ("Back");  // this button to back to a before screen 
			 Button next = new Button ("Next");  // this button to back to a before screen 
			 Button previous = new Button ("Previous");  // this button to back to a before screen 
			 
			 next.setPrefWidth(150);
			 previous.setPrefWidth(150);
			 back.setPrefWidth(150);
			 
			 next.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15));
			 previous.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15));
			 back.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15));

			 Label lo = new Label();
			 Label titel = new Label("The numbers of martyrs ");
			 titel.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,25));
			 titel.setStyle("-fx-text-fill:red"); 

			 


			 hBox.setAlignment(Pos.CENTER);
			 hBox.getChildren().addAll(previous , next , back);
			 
			 textArea.setPrefWidth(400);;
			 textArea.setPrefHeight(400);


	    	 pane.setAlignment(Pos.CENTER);
	    	 pane.setVgap(15);
	    	 pane.setHgap(15);
	    	 pane.add(titel, 0, 0);
	    	 pane.add(textArea, 0, 1);
	    	 pane.add(hBox, 0, 2);
	 		 pane.setStyle("-fx-background-color:Linear-gradient(#61D8DE , #E839F6) ");

	         Font newFont = ((Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15)));
	         
	         Scene scene = new Scene(pane , 700 , 600); // create a new scene 
			 stage.setScene(scene); // add the scene to the stage 
			 stage.setTitle("Report for Location "); // set a title to  the stage 
			 stage.show();
			 
			 Double_Node foundNode = circularList.search( new Location(comboBox2.getSelectionModel().getSelectedItem()));

			 if (foundNode != null ) {
				 lo.setText(foundNode.getData().getLocationName().toString());
				 stringBuilder.append(lo.getText()).append("\n\n");
			     //stringBuilder.append(foundNode.getData().getTree().countNodes());
			 } else {
			     stringBuilder.append("No height information available"); // or any other appropriate message
			 }

			 textArea.setText(stringBuilder.toString());
			 textArea.setFont(newFont);
			 
			 
  		
			 final Double_Node[] currentNode = { null };
			 
			 

			 next.setOnAction(e -> {
			     // Clear the stringBuilder and set an empty string to the text area
			     stringBuilder.setLength(0);
			     textArea.setText("");

			     if (currentNode[0] == null) {
			         Double_Node g = circularList.search(new Location(comboBox2.getSelectionModel().getSelectedItem()));
			         currentNode[0] = g;
			     } else {
			         // If currentNode is not null, get the next node
			         currentNode[0] = currentNode[0].getNext();
			     }

			     if (currentNode[0] != null) {
			         lo.setText(currentNode[0].getData().getLocationName().toString());
			         stringBuilder.append("The Number of martyrs in ").append(lo.getText()).append("\n\n");
			         stringBuilder.append(currentNode[0].getData().getTree().countNodes()).append("\n\n");
			         
			     } else {
			         stringBuilder.append("No height information available"); // or any other appropriate message
			     }

			     // Set the updated content to the text area
			     textArea.setText(stringBuilder.toString());
			     textArea.setFont(newFont);
			 });
			 
			 previous.setOnAction(e -> {
			     // Clear the stringBuilder and set an empty string to the text area
			     stringBuilder.setLength(0);
			     textArea.setText("");

			     if (currentNode[0] == null) {
			         Double_Node g = circularList.search(new Location(comboBox2.getSelectionModel().getSelectedItem()));
			         currentNode[0] = g;
			     } else {
			         // If currentNode is not null, get the next node
			         currentNode[0] = currentNode[0].getPrivous();
			     }

			     if (currentNode[0] != null) {
			    	 lo.setText(currentNode[0].getData().getLocationName().toString());
			         stringBuilder.append("The Number of martyrs in ").append(lo.getText()).append("\n\n");
			         stringBuilder.append(currentNode[0].getData().getTree().countNodes()).append("\n\n");
			         
			     } else {
			         stringBuilder.append("No height information available"); // or any other appropriate message
			     }

			     // Set the updated content to the text area
			     textArea.setText(stringBuilder.toString());
			     textArea.setFont(newFont);
			 });

			 back.setOnAction(e->{
				 nextStage();
			 });
  			
  		}
   		
   		public void levelByLevelStage () {
 			 Stage stage = new Stage(); // create a new stage 
	    	 TextArea textArea = new TextArea(); // create a new TextArea
	    	 GridPane pane = new GridPane (); 
			 StringBuilder stringBuilder = new StringBuilder(); // StringBuilder to save all information 
			 HBox hBox = new HBox (50);
			 
			 
			 Button back = new Button ("Back");  // this button to back to a before screen 
			 Button next = new Button ("Next");  // this button to back to a before screen 
			 Button previous = new Button ("Previous");  // this button to back to a before screen 
			 
			 next.setPrefWidth(150);
			 previous.setPrefWidth(150);
			 back.setPrefWidth(150);
			 
			 next.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15));
			 previous.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15));
			 back.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15));

			 Label lo = new Label();
			 Label titel = new Label(" Traverse the first AVL level-by-level ");
			 titel.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,25));
			 titel.setStyle("-fx-text-fill:red"); 

			 


			 hBox.setAlignment(Pos.CENTER);
			 hBox.getChildren().addAll(previous , next , back);
			 
			 textArea.setPrefWidth(400);;
			 textArea.setPrefHeight(400);


	    	 pane.setAlignment(Pos.CENTER);
	    	 pane.setVgap(15);
	    	 pane.setHgap(15);
	    	 pane.add(titel, 0, 0);
	    	 pane.add(textArea, 0, 1);
	    	 pane.add(hBox, 0, 2);
	 		 pane.setStyle("-fx-background-color:Linear-gradient(#61D8DE , #E839F6) ");

	         Font newFont = ((Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15)));
	         
	         Scene scene = new Scene(pane , 700 , 600); // create a new scene 
			 stage.setScene(scene); // add the scene to the stage 
			 stage.setTitle("Report for Location "); // set a title to  the stage 
			 stage.show();
			 
			 Double_Node foundNode = circularList.search( new Location(comboBox2.getSelectionModel().getSelectedItem()));

			 if (foundNode != null ) {
				 lo.setText(foundNode.getData().getLocationName().toString());
				 stringBuilder.append(lo.getText()).append("\n\n");
			    // stringBuilder.append(foundNode.getData().getTree().printLevelByLevel());
			 } else {
			     stringBuilder.append("No height information available"); // or any other appropriate message
			 }

			 textArea.setText(stringBuilder.toString());
			 textArea.setFont(newFont);
			 
			 
 		
			 final Double_Node[] currentNode = { null };

			 next.setOnAction(e -> {
			     // Clear the stringBuilder and set an empty string to the text area
			     stringBuilder.setLength(0);
			     textArea.setText("");

			     if (currentNode[0] == null) {
			         Double_Node g = circularList.search(new Location(comboBox2.getSelectionModel().getSelectedItem()));
			         currentNode[0] = g;
			     } else {
			         // If currentNode is not null, get the next node
			         currentNode[0] = currentNode[0].getNext();
			     }

			     if (currentNode[0] != null) {
			         lo.setText(currentNode[0].getData().getLocationName().toString());
			         stringBuilder.append(lo.getText()).append("\n");
			         stringBuilder.append(currentNode[0].getData().getTree().printLevelByLevel()).append("\n\n");
			         
			     } else {
			         stringBuilder.append("No height information available"); // or any other appropriate message
			     }

			     // Set the updated content to the text area
			     textArea.setText(stringBuilder.toString());
			     textArea.setFont(newFont);
			 });
			 
			 previous.setOnAction(e -> {
			     // Clear the stringBuilder and set an empty string to the text area
			     stringBuilder.setLength(0);
			     textArea.setText("");

			     if (currentNode[0] == null) {
			         Double_Node g = circularList.search(new Location(comboBox2.getSelectionModel().getSelectedItem()));
			         currentNode[0] = g;
			     } else {
			         // If currentNode is not null, get the next node
			         currentNode[0] = currentNode[0].getPrivous();
			     }

			     if (currentNode[0] != null) {
			    	 lo.setText(currentNode[0].getData().getLocationName().toString());
			         stringBuilder.append(lo.getText()).append("\n");

			         stringBuilder.append(currentNode[0].getData().getTree().printLevelByLevel()).append("\n\n");
			         
			     } else {
			         stringBuilder.append("No height information available"); // or any other appropriate message
			     }

			     // Set the updated content to the text area
			     textArea.setText(stringBuilder.toString());
			     textArea.setFont(newFont);
			 });

			 back.setOnAction(e->{
				 nextStage();
			 });
 			
 		}

   		public void TraverseBackward () {
			 Stage stage = new Stage(); // create a new stage 
	    	 TextArea textArea = new TextArea(); // create a new TextArea
	    	 GridPane pane = new GridPane (); 
			 StringBuilder stringBuilder = new StringBuilder(); // StringBuilder to save all information 
			 HBox hBox = new HBox (50);
			 
			 
			 Button back = new Button ("Back");  // this button to back to a before screen 
			 Button next = new Button ("Next");  // this button to back to a before screen 
			 Button previous = new Button ("Previous");  // this button to back to a before screen 
			 
			 next.setPrefWidth(150);
			 previous.setPrefWidth(150);
			 back.setPrefWidth(150);
			 
			 next.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15));
			 previous.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15));
			 back.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15));

			 Label lo = new Label();
			 Label titel = new Label(" Traverse the 2nd AVL backward ");
			 titel.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,25));
			 titel.setStyle("-fx-text-fill:red"); 

			 


			 hBox.setAlignment(Pos.CENTER);
			 hBox.getChildren().addAll(previous , next , back);
			 
			 textArea.setPrefWidth(400);;
			 textArea.setPrefHeight(400);


	    	 pane.setAlignment(Pos.CENTER);
	    	 pane.setVgap(15);
	    	 pane.setHgap(15);
	    	 pane.add(titel, 0, 0);
	    	 pane.add(textArea, 0, 1);
	    	 pane.add(hBox, 0, 2);
	 		 pane.setStyle("-fx-background-color:Linear-gradient(#61D8DE , #E839F6) ");

	         Font newFont = ((Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15)));
	         
	         Scene scene = new Scene(pane , 700 , 600); // create a new scene 
			 stage.setScene(scene); // add the scene to the stage 
			 stage.setTitle("Report for Location "); // set a title to  the stage 
			 stage.show();
			 
			 Double_Node foundNode = circularList.search( new Location(comboBox2.getSelectionModel().getSelectedItem()));

			 if (foundNode != null ) {
				 lo.setText(foundNode.getData().getLocationName().toString());
				 stringBuilder.append(lo.getText()).append("\n\n");
			  //   stringBuilder.append(foundNode.getData().getTree2().getInOrder());
			 } else {
			     stringBuilder.append("No height information available"); // or any other appropriate message
			 }

			 textArea.setText(stringBuilder.toString());
			 textArea.setFont(newFont);
			 
			 
		
			 final Double_Node[] currentNode = { null };

			 next.setOnAction(e -> {
			     // Clear the stringBuilder and set an empty string to the text area
			     stringBuilder.setLength(0);
			     textArea.setText("");

			     if (currentNode[0] == null) {
			         Double_Node g = circularList.search(new Location(comboBox2.getSelectionModel().getSelectedItem()));
			         currentNode[0] = g;
			     } else {
			         // If currentNode is not null, get the next node
			         currentNode[0] = currentNode[0].getNext();
			     }

			     if (currentNode[0] != null) {
			         lo.setText(currentNode[0].getData().getLocationName().toString());
			         stringBuilder.append(lo.getText()).append("\n\n");
				     stringBuilder.append(currentNode[0].getData().getTree2().getInOrder());
			         
			     } else {
			         stringBuilder.append("No height information available"); // or any other appropriate message
			     }

			     // Set the updated content to the text area
			     textArea.setText(stringBuilder.toString());
			     textArea.setFont(newFont);
			 });
			 
			 previous.setOnAction(e -> {
			     // Clear the stringBuilder and set an empty string to the text area
			     stringBuilder.setLength(0);
			     textArea.setText("");

			     if (currentNode[0] == null) {
			         Double_Node g = circularList.search(new Location(comboBox2.getSelectionModel().getSelectedItem()));
			         currentNode[0] = g;
			     } else {
			         // If currentNode is not null, get the next node
			         currentNode[0] = currentNode[0].getPrivous();
			     }

			     if (currentNode[0] != null) {
			    	 lo.setText(currentNode[0].getData().getLocationName().toString());
			         stringBuilder.append(lo.getText()).append("\n\n");

			         stringBuilder.append(currentNode[0].getData().getTree2().getInOrder()).append("\n\n");
			         
			     } else {
			         stringBuilder.append("No height information available"); // or any other appropriate message
			     }

			     // Set the updated content to the text area
			     textArea.setText(stringBuilder.toString());
			     textArea.setFont(newFont);
			 });

			 back.setOnAction(e->{
				 nextStage();
			 });
			
		}


   		public void maxDate () {
 			 Stage stage = new Stage(); // create a new stage 
	    	 TextArea textArea = new TextArea(); // create a new TextArea
	    	 GridPane pane = new GridPane (); 
			 StringBuilder stringBuilder = new StringBuilder(); // StringBuilder to save all information 
			 HBox hBox = new HBox (50);
			 
			 
			 Button back = new Button ("Back");  // this button to back to a before screen 
			 Button next = new Button ("Next");  // this button to back to a before screen 
			 Button previous = new Button ("Previous");  // this button to back to a before screen 
			 
			 next.setPrefWidth(150);
			 previous.setPrefWidth(150);
			 back.setPrefWidth(150);
			 
			 next.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15));
			 previous.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15));
			 back.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15));

			 Label lo = new Label();
			 Label titel = new Label("The Date with max number of martyrs ");
			 titel.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,25));
			 titel.setStyle("-fx-text-fill:red"); 

			 


			 hBox.setAlignment(Pos.CENTER);
			 hBox.getChildren().addAll(previous , next , back);
			 
			 textArea.setPrefWidth(400);;
			 textArea.setPrefHeight(400);


	    	 pane.setAlignment(Pos.CENTER);
	    	 pane.setVgap(15);
	    	 pane.setHgap(15);
	    	 pane.add(titel, 0, 0);
	    	 pane.add(textArea, 0, 1);
	    	 pane.add(hBox, 0, 2);
	 		 pane.setStyle("-fx-background-color:Linear-gradient(#61D8DE , #E839F6) ");

	         Font newFont = ((Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15)));
	         
	         Scene scene = new Scene(pane , 700 , 600); // create a new scene 
			 stage.setScene(scene); // add the scene to the stage 
			 stage.setTitle("Report for Location "); // set a title to  the stage 
			 stage.show();
			 
			 Double_Node foundNode = circularList.search( new Location(comboBox2.getSelectionModel().getSelectedItem()));

			 if (foundNode != null ) {
				 lo.setText(foundNode.getData().getLocationName().toString());
				 stringBuilder.append(lo.getText()).append("\n\n");
			     //stringBuilder.append(foundNode.getData().getTree2().findMaxMartyrsDate());
			 } else {
			     stringBuilder.append("No Date information available"); // or any other appropriate message
			 }

			 textArea.setText(stringBuilder.toString());
			 textArea.setFont(newFont);
			 
			 
 		
			 final Double_Node[] currentNode = { null };
			 
			 

			 next.setOnAction(e -> {
			     // Clear the stringBuilder and set an empty string to the text area
			     stringBuilder.setLength(0);
			     textArea.setText("");

			     if (currentNode[0] == null) {
			         Double_Node g = circularList.search(new Location(comboBox2.getSelectionModel().getSelectedItem()));
			         currentNode[0] = g;
			     } else {
			         // If currentNode is not null, get the next node
			         currentNode[0] = currentNode[0].getNext();
			     }

			     if (currentNode[0] != null) {
			         lo.setText(currentNode[0].getData().getLocationName().toString());
			         stringBuilder.append("The Max Date of martyrs in ").append(lo.getText()).append("\n\n");
			         stringBuilder.append(currentNode[0].getData().getTree2().findMaxMartyrsDate()).append("\n\n");
			         
			     } else {
			         stringBuilder.append("No Date information available"); // or any other appropriate message
			     }

			     // Set the updated content to the text area
			     textArea.setText(stringBuilder.toString());
			     textArea.setFont(newFont);
			 });
			 
			 previous.setOnAction(e -> {
			     // Clear the stringBuilder and set an empty string to the text area
			     stringBuilder.setLength(0);
			     textArea.setText("");

			     if (currentNode[0] == null) {
			         Double_Node g = circularList.search(new Location(comboBox2.getSelectionModel().getSelectedItem()));
			         currentNode[0] = g;
			     } else {
			         // If currentNode is not null, get the next node
			         currentNode[0] = currentNode[0].getPrivous();
			     }

			     if (currentNode[0] != null) {
			    	 lo.setText(currentNode[0].getData().getLocationName().toString());
			         stringBuilder.append("The Max Date of martyrs in ").append(lo.getText()).append("\n\n");
			         stringBuilder.append(currentNode[0].getData().getTree2().findMaxMartyrsDate()).append("\n\n");
			         
			     } else {
			         stringBuilder.append("No height information available"); // or any other appropriate message
			     }

			     // Set the updated content to the text area
			     textArea.setText(stringBuilder.toString());
			     textArea.setFont(newFont);
			 });

			 back.setOnAction(e->{
				 nextStage();
			 });
 			
 		}
   		
   		public Pane file() {
   			GridPane  pane = new GridPane();
   			Button read = new Button ("Read");
   			read.setPrefWidth(200);
   			
   		 pane.setAlignment(Pos.CENTER);
    	 pane.setVgap(15);
    	 pane.setHgap(15);
    	 pane.add(read, 4, 4);
    	 
    	 read.setOnAction(e->{
    		 Stage s = new Stage ();
    		 writeFile(s);
    	 });
   			
   			
   			return pane ;
   		}
   		
   		public void writeFile(Stage primaryStage) {
	    	 // choose the file to write the new Sorted data inside it 
	    	 FileChooser fileChooser = new FileChooser();
	    	 fileChooser.setTitle("Open Resource File ");
	    	 fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("*.txt", "*.*")); // show the file in stage
	    	 File file = fileChooser.showOpenDialog(primaryStage);
	    	 try {
				PrintWriter writer  = new PrintWriter(file); // create a Print Writer
				
				 Double_Node head = circularList.getHead();

				  
				  if (head == null) {
			            return;
			        }

			        Double_Node current = head;

			        while (true) {
			        	writer.write(current.getData().getTree().getPostOrder());
			        	writer.write(current.getData().getLocationName() + " ");

			            
			            current = current.getNext();
			            if (current == head) {
			                break;
			            }
			        }

			        System.out.println();
			    
				  
				
			
			     
			     writer.flush();
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	     }
	     
	
	
	  public void print ()
	  {
		  Double_Node head = circularList.getHead();
			AVL_Node curr2 ;; // single Node

		  
		  if (head == null) {
	            return;
	        }

	        Double_Node current = head;
	       // current.getData().tree.printLevelByLevel();

	        while (true) {
	            System.out.println(current.getData().getLocationName() + " ");
	            current.getData().getTree();
		          // current.getData().getTree().printLevelByLevel();;

	            System.out.println(current.getData().getTree().height());
	            System.out.println(current.getData().getTree().countNodes());

	            
	            current = current.getNext();
	            if (current == head) {
	                break;
	            }
	        }

	        System.out.println();
	    
		  
	    }
     
	  public void print2 ()
	  {
		  Double_Node head = circularList.getHead();
			AVL2_Node curr2 ;; // single Node

		  
		  if (head == null) {
	            return;
	        }

	        Double_Node current = head;

	       // while (true) {
	        
	        
	        
	        
	            System.out.println(current.getData().getLocationName() + " ");
	           circularList.search(new Location("Gaza")).getData().getTree2().printTree();
	            
	           
	           
	           
	           
	            current = current.getNext();
	           // if (current == head) {
	             //   break;
	           }
	       // }

	      //  System.out.println();
	    
		  
	    //}
	  
	  
	  public void printStack() {
		    String s = "12/27/2008"; // Corrected the date string format

		    try {
		        Date dateOfDeath = new SimpleDateFormat("MM/dd/yyyy").parse(s); // Parse the date string to a Date object

		        dateStack = new DateStack(dateOfDeath);
		        while (!circularList.search(new Location("Gaza")).getData().getTree2().find(dateStack).getData().getStack().isEmpty()) {
		            System.out.println(circularList.search(new Location("Gaza")).getData().getTree2().find(dateStack).getData().getStack().pop());
		           // System.out.println(circularList.search(new Location("Ramallah")).getData().getTree2().height());
		          //  System.out.println(circularList.search(new Location("Gaza")).getData().getTree2().findMaxMartyrsDate());


		            
		       }
		        
		    } catch (ParseException e) {
		        System.out.println("Error parsing the date string: " + s);
		        e.printStackTrace();
		    }
		}

    
	     
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	     
}