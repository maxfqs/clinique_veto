package fr.eni.clinique_veto.ihm;

@SuppressWarnings("serial")
public class IHMException extends Exception {
	
	public IHMException() {
		super();
	}
	
	public IHMException(String message) {
		super(message);
	}
	
	public IHMException(String message, Throwable exception) {
		super(message, exception);
	}
	
	@Override
	public String getMessage() {
		StringBuffer sb = new StringBuffer("Couche IHM - ");
		sb.append(super.getMessage());
		
		return sb.toString() ;
	}
}
