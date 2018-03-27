package exceptions;

public class DuplicadoException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 620860571720760160L;
	
	private String error;
	
	public DuplicadoException(String e){
		this.error=e;
	}
	
	public String getError(){
		return error;
	}
}
