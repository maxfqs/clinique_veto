package fr.eni.clinique_veto.bll;

@SuppressWarnings("serial")
public class BLLException extends Exception {
	
	private BLLError error;
	
	public BLLException() {
		super();
	}
	
	public BLLException(String message) {
		super(message);
	}
	
	public BLLException(BLLError error) {
		this.error = error;
	}
	public BLLException(BLLError error, String message) {
		super(message);
		this.error = error;
	}
	
	public BLLError getError() {
		return this.error;
	}
	
	@Override
	public String getMessage() {
		StringBuffer sb = new StringBuffer("Couche BLL: ");
		String message = super.getMessage();
		
		sb.append(this.error);
		if(message != null) sb.append(message);
		
		return sb.toString() ;
	}
}
