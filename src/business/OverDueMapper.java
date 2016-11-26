package business;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class OverDueMapper implements Initializable
{
	
	
	@FXML public TextField isbnNumber;
	@FXML Text textArea;
	
	@FXML public TableView<OverDueRecord> overDueTable;
	
	@FXML public TableColumn<OverDueRecord, String> ISBNColumn;
	@FXML public TableColumn<OverDueRecord, String> TitleColumn;
	@FXML public TableColumn<OverDueRecord, Integer> Copy_NO_Column;
	@FXML public TableColumn<OverDueRecord, String> MemberColumn; //memberId Column 
	@FXML public TableColumn<OverDueRecord, LocalDate> Due_Date_Column;
	
	private ObservableList<OverDueRecord> items = FXCollections.observableArrayList();
	
	
	@FXML public void checkOverDueDetails()
	{
		System.out.println("Inside over due mapper");
		SystemController controller= new SystemController();
		
		List<OverDueRecord> listOfOverDuesRecord = controller.searchISBN(isbnNumber.getText().toString());
		System.out.println("listOfOverDuesRecord"+listOfOverDuesRecord);
		
		if(listOfOverDuesRecord != null)
		{
			textArea.setText("");
			
			System.out.println("Inside if ");
			
			items.clear();
			items = FXCollections.observableArrayList(listOfOverDuesRecord);
			
			overDueTable.setItems(items);
			
			ISBNColumn.setCellValueFactory(new PropertyValueFactory<OverDueRecord,String>("isbn"));
			TitleColumn.setCellValueFactory(new PropertyValueFactory<OverDueRecord,String>("title"));
			Copy_NO_Column.setCellValueFactory(new PropertyValueFactory<OverDueRecord,Integer>("copyNum"));
			MemberColumn.setCellValueFactory(new PropertyValueFactory<OverDueRecord,String>("memberId"));
			Due_Date_Column.setCellValueFactory(new PropertyValueFactory<OverDueRecord,LocalDate>("dueDate"));
			
			overDueTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
			
			overDueTable.setVisible(true);
			overDueTable.setEditable(false);
			
		}
		
		else
		{
			System.out.println("main else");
			textArea.setText("No Records Avaiable For given ISBN");
			overDueTable.setVisible(false);
		}
		
		
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		
		overDueTable.setVisible(false);
		
	}

}
