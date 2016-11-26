package business;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import utils.AlertMessageUtil;
import business.Address;
import business.LibraryMember;
import business.ServiceResponseException;
import business.SystemController;

public class AddMember implements Initializable {

	ServiceResponseException serviceResponse;
	
	

	@FXML private  TextField memberId;
	@FXML private  TextField firstName;
	@FXML private  TextField LastName;
	@FXML private  TextField phoneNumber;
	@FXML private TextField streetAddress;
	@FXML private  TextField cityName;
	@FXML private  TextField stateName;
	@FXML private TextField zipCode;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	public void addMemeber() throws ServiceResponseException{
		
		
		if (validateForm()){
		Address address = new Address(getStreetAddress(), getCityName(), getCityName(), getZipCode());
		
		LibraryMember member = new LibraryMember(getMemberId(),getFirstName(), getLastName(), getPhoneNumber(), address);
		
		
		
		ControllerInterface controller = new SystemController();
		
		serviceResponse  =controller.addNewMember(member);
		
		setFields();
		
		}

		}
	
	private void setFields() {
		// TODO Auto-generated method stub
		
		memberId.setText("");
		firstName.setText("");
		LastName.setText("");
		phoneNumber.setText("");
		streetAddress.setText("");
		cityName.setText("");
		stateName.setText("");
		zipCode.setText("");
		
		
		
	}

	private Boolean validateForm() {
		System.out.println(!(getStateName().matches("[A-Za-z]")));
	
		System.out.println("state name"+getStateName());
		System.out.println("state name length"+getStateName().length());
		
		
		if (getFirstName().isEmpty() || getLastName().isEmpty()
				|| getPhoneNumber().isEmpty() || getCityName().isEmpty()
				|| getStateName().isEmpty() || getStreetAddress().isEmpty()
				|| getZipCode().isEmpty()) {
			AlertMessageUtil.showExceptionDialog("Please input all field");
			return false;
		}

		/*try {
			Integer.parseInt(getZipCode());
			

		} catch (Exception e) {
			AlertMessageUtil.showExceptionDialog("Zip contains only digit");
			return false;
		}*/
		
		
		if(!getMemberId().matches("[0-9]+") )
		{
			AlertMessageUtil.showExceptionDialog("Please enter Valid memberId");
			return false;
		}
		
		
		
		if(!getPhoneNumber().matches("[0-9]+") || (getPhoneNumber().trim().length() != 10) )
		{
			AlertMessageUtil.showExceptionDialog("Please enter Valid Phone Number");
			return false;
		}
		
		if(!getZipCode().matches("[0-9]+") || (getZipCode().trim().length()!=5) )
		{
			AlertMessageUtil.showExceptionDialog("Please enter Valid Zip code");
			return false;
		}
		
		
		 if( (!getStateName().trim().toString().matches("[A-Za-z]+")) || (getStateName().toString().trim().length()>2) )
		 {
			 AlertMessageUtil.showExceptionDialog("Please enter Valid state name");
			 return false;
			 
		 }
		 if( (!getFirstName().matches("[A-Za-z]+")) || (!getLastName().trim().matches("[A-Za-z]+")))
		 {
			 AlertMessageUtil.showExceptionDialog("Please enter Valid first name and last name");
			 return false;
			 
		 }
		 if(!getCityName().matches("[A-Za-z]+")) 
		 {
			 AlertMessageUtil.showExceptionDialog("Please enter Valid city name");
			 return false;
			 
		 }
		
		 
		 
			
		return true;

		
		
		
	}
	
	public String getMemberId() {
		return memberId.getText();
	}

	public String getFirstName() {
		return firstName.getText();
	}

	public String getLastName() {
		return LastName.getText();
	}

	public String getPhoneNumber() {
		return phoneNumber.getText();
	}

	public String getStreetAddress() {
		return streetAddress.getText();
	}

	public String getCityName() {
		return cityName.getText();
	}

	public String getStateName() {
		return stateName.getText();
	}

	public String getZipCode() {
		return zipCode.getText();
	}


		
		
		
		
	

}
