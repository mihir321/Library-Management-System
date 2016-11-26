package business;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import dataaccess.Auth;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import dataaccess.User;
import dataaccess.DataAccessFacade.StorageType;
import dataaccess.LibraryMemberDAOImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utils.AlertMessageUtil;

public class SystemController implements ControllerInterface {
	public static Auth currentAuth = null;

	DataAccess da = new DataAccessFacade();




	public void login(String id, String password)  {
		DataAccess da = new DataAccessFacade();
		HashMap<String, User> map = da.readUserMap();
		if(!map.containsKey(id)) {
			return;
			//throw new LoginException("ID " + id + " not found");
		}
		String passwordFound = map.get(id).getPassword();
		if(!passwordFound.equals(password)) {
			return;
			//throw new LoginException("Passord does not match password on record");
		}
		currentAuth = map.get(id).getAuthorization();

	}
	/**
	 * This method checks if memberId already exists -- if so, it cannot be
	 * added as a new member, and an exception is thrown.
	 * If new, creates a new LibraryMember based on 
	 * input data and uses DataAccess to store it.
	 * 
	 */
	public ServiceResponseException addNewMember(LibraryMember libraryMember)
			throws ServiceResponseException {

		LibraryMemberDAOImpl libraryMemberDAOImpl = new LibraryMemberDAOImpl();

		try {

			LibraryMember lm = da.searchMember(libraryMember.getMemberId());

			if(lm == null)
			{
				libraryMemberDAOImpl.addLibraryMember(libraryMember);
				return new ServiceResponseException("Successfully added");
			}
			else
			{
				AlertMessageUtil.showExceptionDialog("Member ID already Exists");
				return new ServiceResponseException();
			}

			
			
		} catch (Exception e) {
			e.getMessage();
			return new ServiceResponseException(e.getMessage());
		}

	}


	/**
	 * Reads data store for a library member with specified id.
	 * Ids begin at 1001...
	 * Returns a LibraryMember if found, null otherwise
	 * 
	 */
	public LibraryMember search(String memberId) 
	{

		System.out.println("in search member  "+memberId);
		return da.searchMember(memberId); //return member detail or null 
	}


	/**
	 * Same as creating a new member (because of how data is stored)
	 */
	//	public void updateMemberInfo(String memberId, String firstName, String lastName,


	/**
	 * Looks up Book by isbn from data store. If not found, an exception is thrown.
	 * If no copies are available for checkout, an exception is thrown.
	 * If found and a copy is available, member's checkout record is
	 * updated and copy of this publication is set to "not available"
	 */
	//public void checkoutBook(String memberId, String isbn) throws LibrarySystemException {

	@Override
	public Book searchBook(String isbn) {

		return da.searchBook(isbn);
	}




	/**
	 * Looks up book by isbn to see if it exists, throw exceptioni.
	 * Else add the book to storage
	 */
	//	public boolean addBook(String isbn, String title, int maxCheckoutLength, List<Author> authors) 
	//			throws LibrarySystemException {


	public boolean addBookCopy(String isbn) throws LibrarySystemException {
		Book book = searchBook(isbn);
		if(book == null) 
		{
			return false;
			//throw new LibrarySystemException("No book with isbn " + isbn + " is in the library collection!");
		}

		HashMap<String,Book> booksMap =  da.readBooksMap(); // added for load book
		System.out.println(booksMap);
		Book bookToModify = booksMap.get(isbn);
		System.out.println(bookToModify.getCopies().length); //23-11451 use this copy
		bookToModify.addCopy(); //add a copy for that isbn number 


		DataAccessFacade.saveToStorage(StorageType.BOOKS, booksMap); //load data into file

		Book bookToModify1 = booksMap.get(isbn);
		System.out.println(bookToModify1.getCopies().length);

		System.out.println(booksMap);
		System.out.println("Data Stored ");

		return true;

	}


	public static void main(String[] args) throws LibrarySystemException {

	}


	/* return -1 for member not found
	 * return -2 for book not found
	 * return -3 for book copy not found
	 * return 1 for success
	 */

	@Override
	public int checkoutBook(String memberId, String isbn)  // -1 - id not found
			throws LibrarySystemException { //member id is int in checkout record 

		System.out.println("controller member id "+memberId+" isbn "+ isbn );

		LibraryMember libraryMember = search(memberId);
		System.out.println(libraryMember);

		if(libraryMember != null)
		{

			HashMap<String,Book> booksMap =  da.readBooksMap(); //get that full book map 
			Book book = booksMap.get(isbn); //search book in local 


			if(book != null)
			{

				int copyNum = checkCopyAvailable(book);
				System.out.println("copy num "+copyNum);

				if(copyNum > 0) //copy number always greater than or equal to 1
				{

					//int  maximumCheckOutLength

					System.out.println("Books Map before update "+booksMap);


					BookCopy copy = new BookCopy(book, copyNum, false); //setting the avaiable value to false 
					System.out.println("copy num "+copy.getCopyNum() + "isbn number "+copy.getBook().getIsbn());

					book.updateCopies(copy); //updating copy 

					System.out.println("Books Map after update "+booksMap);

					DataAccessFacade.saveToStorage(StorageType.BOOKS, booksMap); //load data into file with update isAvaiablel field to false 

					//create check out record and checkoutentry record 
					LocalDate checkOutDate = LocalDate.now();
					LocalDate duedate = checkOutDate.plusDays(book.getMaxCheckoutLength());
					String title = book.getTitle();

					CheckOutRecordEntry checkOutRecordEntry = new CheckOutRecordEntry(checkOutDate, duedate, copyNum, isbn, title);
					//check for the member  in HashMap and update 
					//if no member avaiable then create new 
					HashMap<String,CheckOutRecord> checkOutRecordsMap = da.readCheckOutRecordMap();

					CheckOutRecord checkOutRecord = checkOutRecordsMap.get(memberId); //getting the existing check out record

					if(checkOutRecord == null)
					{
						CheckOutRecord newCheckOutRecord = new CheckOutRecord(memberId, checkOutRecordEntry); //library .getmemberId or memberId is ok 
						checkOutRecordsMap.put(memberId, newCheckOutRecord);
					}
					else
					{
						checkOutRecord.getCheckOutRecordEntries().add(checkOutRecordEntry); //apending the check out entries 
						checkOutRecordsMap.put(memberId, checkOutRecord); //updating the same key 
					}

					DataAccessFacade.saveToStorage(StorageType.CHECK_OUT_RECORDS, checkOutRecordsMap); //presists into DB

					return 1; //positive value to mapper


				} //end of copy

				else
					return -3;

			} //end of book 
			else
				return -2;
		} // end of member 

		else
			return -1;

	}

	private int checkCopyAvailable(Book book) 
	{
		System.out.println("book.getCopies() "+book.getCopies().length);

		for (BookCopy bookCopy : book.getCopies()) 
		{
			System.out.println("book copy available "+bookCopy.isAvailable());
			if(bookCopy.isAvailable())
			{
				return bookCopy.getCopyNum(); //return copy num 
			}

		}

		return -1;
	}

	/* -1 for book not  available
	 * -2 for no one taken book
	 */

	public List<OverDueRecord> searchISBN(String isbn) //make return type null
	{
		LocalDate todaysDate  = LocalDate.now().plusDays(22); // adding some days test
		System.out.println("inside search isbn ");
		System.out.println("todays date local date"+todaysDate);
		List<OverDueRecord> listOfOverDueRecordsForParticularIsbn = new ArrayList<OverDueRecord>();

		Book book = searchBook(isbn);
		if(book != null)
		{
			DataAccess da = new DataAccessFacade();
			HashMap<String,CheckOutRecord> hm = da.readCheckOutRecordMap(); //get the check out record details 

			if(hm != null)
			{
				System.out.println("if");
				for (String key : hm.keySet())  //key is member id  member iteration
				{
					System.out.println("for");
					String memberId = key;

					CheckOutRecord checkOutRecord = hm.get(key);
					List<CheckOutRecordEntry> checkOutRecordEntries = checkOutRecord.getCheckOutRecordEntries(); //get the check out record entry for particular member

					for (CheckOutRecordEntry checkOutRecordEntry : checkOutRecordEntries) //iterate all checkout entries  record iteration
					{
						System.out.println("for");
						if( (todaysDate.compareTo(checkOutRecordEntry.getDueDate()) > 0) && (checkOutRecordEntry.getIsbn().equals(isbn)) ) //return negative if less or positive if greater
						{
							System.out.println("if");

							OverDueRecord overDueRecord = new OverDueRecord(isbn, checkOutRecordEntry.getTitle(), 
									checkOutRecordEntry.getCopyNum(), memberId, checkOutRecordEntry.dueDate);

							listOfOverDueRecordsForParticularIsbn.add(overDueRecord);

						}
						/*else
						{
							return null;
						}
*/

					}


				}
				return listOfOverDueRecordsForParticularIsbn;

			}

			else
			{
				return null;
			}


		}
		else
			return null;

	}

	public List<CheckOutRecordEntry> printCheckoutRecord(String memberId) 
	{

		LibraryMember libraryMember = search(memberId);
		System.out.println(libraryMember);
		List<CheckOutRecordEntry> checkOutRecordEntries;


		if(libraryMember != null)
		{
			HashMap<String,CheckOutRecord> hm = da.readCheckOutRecordMap();

			if(hm != null)
			{
				CheckOutRecord checkOutRecord = hm.get(memberId);
				
				
				if(checkOutRecord != null)
				{
					checkOutRecordEntries = checkOutRecord.getCheckOutRecordEntries();
				}
				else
				{
					return null;
				}

			}
			else
				return null;


			return checkOutRecordEntries; //returning set of entries 
		}
		else
		{
			return null;
		}

	}


	public ServiceResponseException addBook(Book book)
			throws ServiceResponseException {

		DataAccessFacade dataAccessFacade = new DataAccessFacade();


		try {

			Book b = dataAccessFacade.searchBook(book.getIsbn());
			System.out.println("b value "+b);
			if(b == null)
			{
				dataAccessFacade.saveNewBook(book);
				return new ServiceResponseException("Successfully added");
			}
			else
			{
				AlertMessageUtil.showExceptionDialog("Book already Exists");
				return new ServiceResponseException();
			}

			
		} catch (Exception e) {
			e.printStackTrace();
			return new ServiceResponseException();
		}

	}


}









