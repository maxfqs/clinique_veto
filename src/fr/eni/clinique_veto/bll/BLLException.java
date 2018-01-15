package fr.eni.clinique_veto.bll;

public class BLLException extends Exception {
	private static final long serialVersionUID = -9178372175776897735L;

	public BLLException() {
		super();
	}
	
	public BLLException(String message) {
		super(message);
	}
}
