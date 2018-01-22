package fr.eni.clinique_veto.bo;

public enum PersonnelRole {
	ADMIN("ADM", "Admin"),
	ASSISTANT("AST", "Assistant"),
	SECRETAIRE("SEC", "Secrétaire"),
	VETERINAIRE("VET", "Vétérinaire"),
	
	;
	
	private final String code;
	private final String description;
	
	private PersonnelRole(String code, String description) {
		this.code = code;
		this.description = description;
	}
	
	public static boolean isValidRole(String code) {
		boolean isValid = false;
		
		for(PersonnelRole pr : PersonnelRole.values()) {
			if(pr.getCode().equals(code)) isValid = true;
		}
		
		return isValid;
	}
	
	public String getCode() {
		return this.code;
	}
	
	public String getDescription() {
		return this.description;
	}
}
