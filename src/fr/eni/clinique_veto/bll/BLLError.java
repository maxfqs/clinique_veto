package fr.eni.clinique_veto.bll;

public enum BLLError {
	
	// 100 - Personnel
	INVALID_PERSONNEL_ROLE(100, "Le rôle est incorrect");
	
	
	private final int code;
	private final String description;
	
	private BLLError(int code, String description) {
		this.code = code;
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public int getCode() {
		return this.code;
	}
	
	@Override
	public String toString() {
		return "Error " + code + ": " + description;
	}
}
