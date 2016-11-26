package dataaccess;

import java.util.HashMap;

import utils.AlertMessageUtil;
import dataaccess.DataAccessFacade.StorageType;
import business.LibraryMember;

public class LibraryMemberDAOImpl implements LibraryMemberDAO {
	
	private static HashMap<String, LibraryMember> members;
	
	DataAccessFacade dataAccessFacade = new DataAccessFacade();

	@Override
	public void addLibraryMember(LibraryMember libraryMember) {
		// TODO Auto-generated method stub
		
		HashMap<String, LibraryMember> mems = readMemberMap(); 
		mems.put(libraryMember.getMemberId(), libraryMember);
		members = mems;
		System.out.println("before member record stored "+dataAccessFacade.readMemberMap());
		DataAccessFacade.saveToStorage(StorageType.MEMBERS, mems);
		//members.put(libraryMember.getMemberId(),libraryMember);
		
		System.out.println("After member record stored "+dataAccessFacade.readMemberMap());
		AlertMessageUtil.showSucessDialog("A new Memeber is added");
		
		
	}
	
	
	@SuppressWarnings("unchecked")
	public HashMap<String, LibraryMember> readMemberMap() {
		if(members == null) {
			try{
				if(DataAccessFacade.readFromStorage(StorageType.MEMBERS)!=null) 
					members = (HashMap<String, LibraryMember>)DataAccessFacade.readFromStorage(StorageType.MEMBERS);
				else 
					return members = new HashMap<String, LibraryMember>();
				
			} catch(Exception e){
				e.printStackTrace();
				//throw new ServiceResponse(false, e.getMessage());
				
			}
		}
		return members;
	}


}
