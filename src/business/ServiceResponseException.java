package business;

import java.io.Serializable;

public class ServiceResponseException extends Exception implements Serializable{
	
	public ServiceResponseException() {
		super();
	}
	public ServiceResponseException(String msg) {
		super(msg);
	}

}
