package business;

import java.awt.MenuBar;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import utils.AlertMessageUtil;
import dataaccess.Auth;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainScreenAdminLibrarianMapper implements Initializable
{


	
	
	
@FXML public javafx.scene.control.MenuBar menuBar;
	
ServiceResponseException serviceResponse;

public String getMemberId() {
	return memberId.textProperty().get();
}

public String getFirstName() {
	return firstName.textProperty().get();
}

public String getLastName() {
	return LastName.textProperty().get();
}

public String getPhoneNumber() {
	return phoneNumber.textProperty().get();
}

public String getStreetAddress() {
	return streetAddress.textProperty().get();
}

public String getCityName() {
	return cityName.textProperty().get();
}

public String getStateName() {
	return stateName.textProperty().get();
}

public String getZipCode() {
	return zipCode.textProperty().get();
}

@FXML private Text memberId;
@FXML private Text firstName;
@FXML private Text LastName;
@FXML private Text phoneNumber;
@FXML private Text streetAddress;
@FXML private Text cityName;
@FXML private Text stateName;
@FXML private Text zipCode;

	@FXML public Menu librarianMenu;
	@FXML public Menu adminMenu;
	
	
	@FXML public void librarianCheckOut() //check out id not needed 
	{
		
		System.out.println("Inside CheckOut ");
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/ui/CheckOutBook.fxml")); //loading another screen 
			Stage stage = new Stage();
			stage.setTitle("CheckOut Book");
			stage.setScene(new Scene(root));
			stage.show();
		}
		 catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		 }
		
		
		
	}
	
	@FXML public void addCopyOfbook()
	{
		
		System.out.println("inside add copy ");
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/ui/AddCopyOfBookFxml.fxml")); //loading another screen 
			Stage stage = new Stage();
			stage.setTitle("Add Copy");
			stage.setScene(new Scene(root));
			stage.show();
		}
		 catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		 }
		
		
	}
	@FXML public void checkOverDue()
	{
		System.out.println("inside main check Over Due ");
		
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/ui/OverDueDetails.fxml")); //loading another screen 
			Stage stage = new Stage();
			stage.setTitle("Check Over Due Details for Book");
			stage.setScene(new Scene(root));
			stage.show();
		}
		 catch (IOException e) {
				// TODO Auto-generated catch blockC
				e.printStackTrace();
		 }
		
	}
	@FXML public void PrintMember()
	{
		System.out.println("inside main Print ");
		
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/ui/Print_MemberDetails.fxml")); //loading another screen 
			Stage stage = new Stage();
			stage.setTitle("Print Member Record CheckOut Details");
			stage.setScene(new Scene(root));
			stage.show();
		}
		 catch (IOException e) {
				// TODO Auto-generated catch blockC
				e.printStackTrace();
		 }
		
	}
	
	



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		System.out.println("Inside initialize");
		//create fxml loader each 
		if(SystemController.currentAuth.equals(Auth.LIBRARIAN))
		{
			
			
			adminMenu.setVisible(false);


		}
		else if(SystemController.currentAuth.equals(Auth.ADMIN))
		{
			
			librarianMenu.setVisible(false);
			
		}
		/*else if(SystemController.currentAuth.equals(Auth.BOTH))
		{
			
		}*/

	}
	
	@FXML
	public void addMemeber() throws ServiceResponseException{
		
	
		
		System.out.println("inside add memeber ");
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/ui/AddMember.fxml")); //loading another screen 
			Stage stage = new Stage();
			stage.setTitle("Add member");
			stage.setScene(new Scene(root));
			stage.show();
		}
		 catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		 }
		
		
	}
	
	@FXML
	public void addBook() throws ServiceResponseException{
		
	
		
		System.out.println("inside add Book ");
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/ui/AddBook.fxml")); //loading another screen 
			Stage stage = new Stage();
			stage.setTitle("Add Book");
			stage.setScene(new Scene(root));
			stage.show();
		}
		 catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		 }
		
		
	}
	
	


}
