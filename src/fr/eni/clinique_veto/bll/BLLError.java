package fr.eni.clinique_veto.bll;

public enum BLLError {
	
	// 000 - Misc
	DATABASE_ERROR(1, "Une erreur est survenue lors de la connexion avec la base de données"),
	
	// 100 - Personnel
	INVALID_PERSONNEL_ROLE(100, "Le rôle est incorrect"),
	FAILED_PERSONNEL_ADD(101, "Erreur lors de l'ajout de ce personnel"),
	FAILED_PERSONNEL_UPDATE(102, "Erreur lors de la sauvegarde du personnel");
	
	
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
