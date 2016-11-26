package business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CheckOutRecord implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 566058012692793557L;

	String memberId;
	
	List<CheckOutRecordEntry> checkOutRecordEntries = new ArrayList<CheckOutRecordEntry>();
	
	
	
	
	public CheckOutRecord(String memberId, CheckOutRecordEntry checkOutRecordEntry) {
		super();
		this.memberId = memberId;
		this.checkOutRecordEntries.add(checkOutRecordEntry); //adding one by one list
	}
	
	
	public CheckOutRecord() {
		// TODO Auto-generated constructor stub
	}


	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public List<CheckOutRecordEntry> getCheckOutRecordEntries() {
		return checkOutRecordEntries;
	}
	public void setCheckOutRecordEntries(List<CheckOutRecordEntry> checkOutRecordEntries) {
		this.checkOutRecordEntries = checkOutRecordEntries;
	}
	
	
	//added vasanth
	public int getCheckOutRecordSize() {
		return this.checkOutRecordEntries.size();
	}
	
	
	
}
