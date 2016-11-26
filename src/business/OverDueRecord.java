package business;

import java.time.LocalDate;

public class OverDueRecord 
{
	
	String isbn;
	String title;
	Integer copyNum;
	String memberId;
	LocalDate dueDate;
	
	public OverDueRecord(String isbn, String title, Integer copyNum, String memberId, LocalDate dueDate) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.copyNum = copyNum;
		this.memberId = memberId;
		this.dueDate = dueDate;
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
	
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public LocalDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public Integer getCopyNum() {
		return copyNum;
	}

	public void setCopyNum(Integer copyNum) {
		this.copyNum = copyNum;
	}
	
	
	
	
	

}
