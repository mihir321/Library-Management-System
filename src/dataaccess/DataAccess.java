package dataaccess;

import java.util.HashMap;

import business.Book;
import business.CheckOutRecord;
import business.LibraryMember;





public interface DataAccess {
	public LibraryMember searchMember(String memberId);
	public Book searchBook(String isbn);
	
	
	///////save methods
	public void saveNewMember(LibraryMember member);
	public void updateMember(LibraryMember member);
	
	//save new book
	public void saveNewBook(Book book);
	
	//////read methods 
	public HashMap<String,Book> readBooksMap();
	public HashMap<String,User> readUserMap();
	public HashMap<String, LibraryMember> readMemberMap();
	
	//added vasanth read checkoutRecord data
	public HashMap<String, CheckOutRecord> readCheckOutRecordMap();
	
}
