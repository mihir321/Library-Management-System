package dataaccess;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

import business.Book;
import business.BookCopy;
import business.CheckOutRecord;
import business.LibraryMember;
import utils.AlertMessageUtil;





public class DataAccessFacade implements DataAccess {
	
	public enum StorageType { // i changed public 
		BOOKS, MEMBERS, USERS, CHECK_OUT_RECORDS;
	}
	
	public static final String OUTPUT_DIR = System.getProperty("user.dir") 
			+ "\\src\\dataaccess\\storage";
	public static final String DATE_PATTERN = "MM/dd/yyyy";
	

	
	////specialized lookup methods //vasanth added 
	public LibraryMember searchMember(String memberId) 
	{
		System.out.println("inside DB");
		
		HashMap<String, LibraryMember> membersMap = readMemberMap();
		LibraryMember libraryMember = membersMap.get(memberId);
		System.out.println("libraryMember in db search "+libraryMember);
		return libraryMember;
		
		
	}
	
	public Book searchBook(String isbn) {
		HashMap<String,Book> booksMap =  readBooksMap(); //line for read book 
		Book b = booksMap.get(isbn); //returns null if no key 
		return b;
	}
	
	public Auth login(String id, String pwd) {
		HashMap<String, User> userMap = readUserMap();
		if(!userMap.containsKey(id)) return null;
		User user = userMap.get(id);
		if(!pwd.equals(user.getPassword())) {
			return null;
		}
		return user.getAuthorization();
	}
	
	
	///////save methods
	//saveNewMember
	public void saveNewMember(LibraryMember member)  //all releated to libraries 
	{
		
	}
		
	
	public void updateMember(LibraryMember member) 
	{
		
	}
	
	//save new lendable item hari
	public void saveNewBook(Book book) { //add new book
		HashMap<String, Book> bookMap = readBooksMap();
		String isbn = book.getIsbn();
		System.out.println("Before book map stored "+ readBooksMap());
		bookMap.put(isbn, book);
		saveToStorage(StorageType.BOOKS, bookMap);	
		
		AlertMessageUtil.showSucessDialog("A new Book is added");
		System.out.println("after book map stored "+ readBooksMap());
	}
	

	
	//////read methods that return full maps
	
	
	@SuppressWarnings("unchecked")
	public  HashMap<String,Book> readBooksMap() {	
		return (HashMap<String,Book>) readFromStorage(StorageType.BOOKS);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public HashMap<String,CheckOutRecord> readCheckOutRecordMap() //reading data from storage 
	{
		
		return (HashMap<String,CheckOutRecord>) readFromStorage(StorageType.CHECK_OUT_RECORDS);
	}
	
	
	@SuppressWarnings("unchecked") //vasanth added
	public HashMap<String, LibraryMember> readMemberMap() 
	{
		System.out.println("Inside read map ");
		return (HashMap<String, LibraryMember>)readFromStorage(StorageType.MEMBERS);
	}
	
	
	@SuppressWarnings("unchecked")
	public HashMap<String, User> readUserMap() {
		return (HashMap<String, User>)readFromStorage(StorageType.USERS);
	}
	
	
	/////load methods - these place test data into the storage area
	///// - used just once at startup  
	//vasanth added 
	
	static void loadCheckOutRecordMap(List<CheckOutRecord> checkOutRecordList) {
		HashMap<String,CheckOutRecord> checkOutRecordsMap = new HashMap<String, CheckOutRecord>(); //check out record have multiple check out entries 
		checkOutRecordList.forEach(checkOutRecord -> checkOutRecordsMap.put(checkOutRecord.getMemberId(), checkOutRecord));
		saveToStorage(StorageType.CHECK_OUT_RECORDS, checkOutRecordsMap);
		
	}
	
	static void loadMemberMap(List<LibraryMember> memberList) {
		HashMap<String, LibraryMember> members = new HashMap<String, LibraryMember>();
		memberList.forEach(member -> members.put(member.getMemberId(), member));
		saveToStorage(StorageType.MEMBERS, members);
		
	}
		
	static void loadBookMap(List<Book> bookList) {
		HashMap<String, Book> books = new HashMap<String, Book>();
		bookList.forEach(book -> books.put(book.getIsbn(), book)); //storing books isbn as id in map 
		saveToStorage(StorageType.BOOKS, books);
	}
	static void loadUserMap(List<User> userList) {
		HashMap<String, User> users = new HashMap<String, User>();
		userList.forEach(user -> users.put(user.getId(), user)); //storing user ids 
		saveToStorage(StorageType.USERS, users);
	}
	
	public static void saveToStorage(StorageType type, Object ob) { // i changed public 
		ObjectOutputStream out = null;
		try {
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
			out = new ObjectOutputStream(Files.newOutputStream(path));
			out.writeObject(ob);
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if(out != null) {
				try {
					out.close();
				} catch(Exception e) {}
			}
		}
	}
	
	static Object readFromStorage(StorageType type) {
		System.out.println("inside object streame ");
		ObjectInputStream in = null;
		Object retVal = null;
		try {
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
			in = new ObjectInputStream(Files.newInputStream(path));
			retVal = in.readObject();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(in != null) {
				try {
					in.close();
				} catch(Exception e) {}
			}
		}
		return retVal;
	}
	
	
	
	final static class Pair<S,T> implements Serializable{
		
		S first;
		T second;
		Pair(S s, T t) {
			first = s;
			second = t;
		}
		@Override 
		public boolean equals(Object ob) {
			if(ob == null) return false;
			if(this == ob) return true;
			if(ob.getClass() != getClass()) return false;
			@SuppressWarnings("unchecked")
			Pair<S,T> p = (Pair<S,T>)ob;
			return p.first.equals(first) && p.second.equals(second);
		}
		
		@Override 
		public int hashCode() {
			return first.hashCode() + 5 * second.hashCode();
		}
		@Override
		public String toString() {
			return "(" + first.toString() + ", " + second.toString() + ")";
		}
		private static final long serialVersionUID = 5399827794066637059L;
	}



	
	
}
