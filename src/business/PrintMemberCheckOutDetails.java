package business;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class PrintMemberCheckOutDetails 
{

	@FXML public TextField MemberID;
	@FXML Text textArea;
	
	@FXML public void printMemberDetails()
	{
		List<CheckOutRecordEntry> checkOutRecordEntries = new ArrayList<CheckOutRecordEntry>();
		
		System.out.println("inside print ");
		SystemController controller = new SystemController();
		
		checkOutRecordEntries.clear();
		
		checkOutRecordEntries = controller.printCheckoutRecord(MemberID.getText().toString());
		
		if(checkOutRecordEntries != null)
		{
			
			
			System.out.println("\n\n\t CHECK OUT RECORD DETAILS FOR MEMBER "+MemberID.getText().toString() +"\n");
			//System.out.println("\t ISBN\t\t\t\tTITLE\t\t\t\tCOPY_NUM\t\t\t\tDUE_DATE\t\t\t\tCHECKOUT_DATE  ");
			
			String output = "";
			output +=String.format("%34s %34s %34s %34s %34s %n",
					"ISBN",
					"TITLE",
					"COPY_NUM",
					"DUE_DATE",
					"CHECKOUT_DATE");
			output += String.format("%34s %34s %34s %34s %34s %n",
							"_______________",
							"_______________",
							"_______________",
							"_______________",
							"_______________");
			
			System.out.println(output);
			
			String value = "";

			for (CheckOutRecordEntry checkOutRecordEntry : checkOutRecordEntries) 
			{
//				System.out.print("\t" + checkOutRecordEntry.getIsbn() +"\t\t"
//									+checkOutRecordEntry.getTitle() +"\t"+
//									checkOutRecordEntry.getCopyNum() +"\t\t\t\t"+
//									checkOutRecordEntry.getDueDate() +"\t\t\t\t"+
//									checkOutRecordEntry.getCheckOutdate() + "\n");
				value +=String.format("%34s %34s %34s %34s %34s %n",
						checkOutRecordEntry.getIsbn(),
						checkOutRecordEntry.getTitle(),
						checkOutRecordEntry.getCopyNum(),
						checkOutRecordEntry.getDueDate(),
						checkOutRecordEntry.getCheckOutdate());
				
			}
			System.out.println(value);
			
			textArea.setText("Records is been printed");
			
		}
		
		else
		{
			textArea.setText("There is no Records Available to print for the given member");
		}
		
	}
}
