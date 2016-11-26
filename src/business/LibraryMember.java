package business;

import java.io.Serializable;

public class LibraryMember extends Person implements Serializable
{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 176838974111618928L;
	String memberId;
	
	public LibraryMember(String id, String f, String l, String t, Address a) 
	{
		super(f, l, t, a);
		this.memberId = id;
		
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	@Override
	public String toString() {
		return "LibraryMember [memberId=" + memberId + ", FirstName=" + getFirstName() + ", LastName="
				+ getLastName() + "]";
	}

	

	
	

	
	

}
