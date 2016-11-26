package business;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import utils.AlertMessageUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class AddBook implements Initializable {
	
	ServiceResponseException serviceResponse;
	
	public String getIsbn() {
		return isbn.getText();
	}
	public String getTitle() {
		return title.getText();
	}
	public String getAuthors() {
		return authors.getText();
	}
	public String getMaxCheckoutLength() {
		return maxCheckoutLength.getText();
	}
	public String getCopies() {
		return copies.getText();
	}




	@FXML private  TextField isbn;
	@FXML private  TextField title;
	@FXML private  TextField authors;
	@FXML private  TextField maxCheckoutLength;
	@FXML private TextField copies;


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	public void addBook() throws ServiceResponseException{
		
	
		String[] authorNames=null;
		
		authorNames=getAuthors().split(";");
		
		
		
		
		if(validateForm())
		{
			int numberofCopies = Integer.parseInt(getCopies());
		int maxCheckoutlength = Integer.parseInt(getMaxCheckoutLength()); 
		Book book = new Book(getIsbn(), getTitle(), maxCheckoutlength, authorNames,numberofCopies);
		
		
		
		ControllerInterface controller = new SystemController();
		
		serviceResponse  =controller.addBook(book);
		
		setFields();
		
		}

		}
	
	private void setFields() {
		// TODO Auto-generated method stub
		
		isbn.setText("");
		title.setText("");
		authors.setText("");
		maxCheckoutLength.setText("");
		copies.setText("");
		
	}
	private Boolean validateForm() {
		if (getIsbn().isEmpty() || getTitle().isEmpty()
				|| getAuthors().isEmpty() || getMaxCheckoutLength().isEmpty()
				|| getCopies().isEmpty()) {
			AlertMessageUtil.showExceptionDialog("Please input all field");
			return false;
		}

		/*try {
			Integer.parseInt(getCopies());
		} catch (Exception e) {
			AlertMessageUtil.showExceptionDialog("Copies contains only digit");
			return false;
		}*/
		
		if(!getIsbn().matches("[0-9-]+")  )
		{
			AlertMessageUtil.showExceptionDialog("Please enter Valid isbn Number");
			return false;
		}
		
		if(!getMaxCheckoutLength().matches(".*[1-9].*")  )
		{
			AlertMessageUtil.showExceptionDialog("Please enter Valid max checkout length");
			return false;
		}
		
		if(!getCopies().matches(".*[1-9].*") )
		{
			AlertMessageUtil.showExceptionDialog("Please enter Valid copy number");
			return false;
		}
		
		
		
		
		return true;

		
		
		
	}


	
	
	
}
