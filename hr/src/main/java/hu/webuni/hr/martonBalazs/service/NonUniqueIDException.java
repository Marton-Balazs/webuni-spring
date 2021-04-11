package hu.webuni.hr.martonBalazs.service;

public class NonUniqueIDException extends RuntimeException{
	
	public NonUniqueIDException(long id) {
		super("Existing id: " + id);
	}

}
