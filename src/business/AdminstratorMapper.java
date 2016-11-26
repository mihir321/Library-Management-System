package business;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class AdminstratorMapper implements Initializable
{
	
	@FXML TextField isbnNumber;
	@FXML Text textArea;
	
	@FXML public TableView<Book> copyTable;
	@FXML public TableColumn<Book, String> isbnColumn;
	@FXML public TableColumn<Book, String> titleColumn;
	@FXML public TableColumn<Book, Integer> copiesColumn;
	
	private ObservableList<Book> items = FXCollections.observableArrayList();
	
	@FXML public void addCopyOfbook() throws LibrarySystemException
	{
		System.out.println("inside came" + isbnNumber.getText().toString());
		SystemController controller =  new SystemController();
		
		boolean result = controller.addBookCopy(isbnNumber.getText().toString());
		if(result == false)
		{
			textArea.setText("Invalid ISBN Number");
			copyTable.setVisible(false);
		}
		
		else
		{
			//System.out.println("else loop ");
			displayCopyTable();
		}
		
	}
	
	


	private void displayCopyTable() {
		// TODO Auto-generated method stub
		
		//System.out.println("calling displaying table ");
		
		DataAccess da = new DataAccessFacade();
		HashMap<String,Book> hm = da.readBooksMap();
		
		//System.out.println("hm value "+hm);
		List<Book> listOfBooks = new ArrayList<Book>(hm.values());
		/*for (Book book : listOfBooks) {
			System.out.println(book.getIsbn() +" "+ book.getCopiesAvailable());
		}*/
		
		items.clear();
		
		items = FXCollections.observableArrayList(listOfBooks); //even collection will accept 

		
		copyTable.setItems(items);
		
		isbnColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("isbn"));
		titleColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("title"));
		copiesColumn.setCellValueFactory(new PropertyValueFactory<Book,Integer>("copiesAvailable"));
		
		copyTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		
		copyTable.setVisible(true);
		copyTable.setEditable(false);
		
		textArea.setText("");
		
	}




	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		System.out.println("calling initialize ");
		copyTable.setVisible(false);
		
	}
	
}
