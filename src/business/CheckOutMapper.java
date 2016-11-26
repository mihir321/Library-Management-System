package business;

import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
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

public class CheckOutMapper implements Initializable
{


	@FXML public TextField memberId;
	@FXML public TextField isbnNumber;
	@FXML public Text textId;
	
	@FXML public TableView<CheckOutRecordEntry> checkOutTable;
	@FXML public TableColumn<CheckOutRecordEntry, String> isbnId;
	@FXML public TableColumn<CheckOutRecordEntry, String> titleId;
	@FXML public TableColumn<CheckOutRecordEntry, LocalDate> check_out_date_id;
	@FXML public TableColumn<CheckOutRecordEntry, LocalDate> due_date_id;
	
	private ObservableList<CheckOutRecordEntry> items = FXCollections.observableArrayList();
	
	@FXML public void checkOutBook() throws LibrarySystemException
	{
		System.out.println("Inside check out book");
		SystemController controller =  new SystemController();
		System.out.println("memberId "+memberId.getText().toString() +" isbnNumber"+isbnNumber.getText().toString());

		if((memberId.getText() != null) && (isbnNumber.getText() != null))
		{
			int result = controller.checkoutBook(memberId.getText().toString(), isbnNumber.getText().toString());

			System.out.println("result value "+result);
			if(result == -1)
			{
				textId.setText("Invalid Member");
				checkOutTable.setVisible(false);
			}
			else if(result == -2)
			{
				textId.setText("Book is Not Available");
				checkOutTable.setVisible(false);
			}
			else if(result == -3)
			{
				textId.setText("Book Copy is Not  Available");
				checkOutTable.setVisible(false);
			}
			//display checkout records tabel
			else
			{
				displayCheckOutRecords();
			}

		}
		else
		{
			textId.setText("Please  Provide valid input data");
			checkOutTable.setVisible(false);
		}

	}


	private void displayCheckOutRecords() 
	{
		DataAccess da = new DataAccessFacade();
		HashMap<String,CheckOutRecord> hm = da.readCheckOutRecordMap();
		CheckOutRecord checkOutRecord = hm.get(memberId.getText().toString()); //pass string object  
		
		items.clear();
		items = FXCollections.observableArrayList(checkOutRecord.getCheckOutRecordEntries()); //getting array list 
		
		checkOutTable.setItems(items);
		
		isbnId.setCellValueFactory(new PropertyValueFactory<CheckOutRecordEntry,String>("isbn"));
		titleId.setCellValueFactory(new PropertyValueFactory<CheckOutRecordEntry,String>("title"));
		check_out_date_id.setCellValueFactory(new PropertyValueFactory<CheckOutRecordEntry,LocalDate>("checkOutdate"));
		due_date_id.setCellValueFactory(new PropertyValueFactory<CheckOutRecordEntry,LocalDate>("dueDate"));
		
		checkOutTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		checkOutTable.setVisible(true);
		checkOutTable.setEditable(false);
		
		textId.setText("");//
		
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		checkOutTable.setVisible(false);
	}


	
}
