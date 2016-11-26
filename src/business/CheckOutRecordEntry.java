package business;



import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class CheckOutRecordEntry implements Serializable


{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3573033514590180298L;
	LocalDate checkOutdate; //will get set in book
	LocalDate dueDate; // is add current  plus maximum date specified in particular book
	int  copyNum;
	String isbn;
	String title;
	
	
	
	
	public CheckOutRecordEntry(LocalDate checkOutdate, LocalDate dueDate, int copyNum, String isbn, String title) {
		super();
		this.checkOutdate = checkOutdate;
		this.dueDate = dueDate;
		this.copyNum = copyNum;
		this.isbn = isbn;
		this.title = title;
	}
	
	
	
	
	public LocalDate getCheckOutdate() {
		return checkOutdate;
	}




	public void setCheckOutdate(LocalDate checkOutdate) {
		this.checkOutdate = checkOutdate;
	}




	public LocalDate getDueDate() {
		return dueDate;
	}




	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}




	public int getCopyNum() {
		return copyNum;
	}
	public void setCopyNum(int copyNum) {
		this.copyNum = copyNum;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	
	

}
