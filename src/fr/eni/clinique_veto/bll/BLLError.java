package fr.eni.clinique_veto.bll;

public enum BLLError {
	
	// 000 - Misc
	DATABASE_ERROR(1, "Une erreur est survenue lors de la connexion avec la base de données"),
	INVALID_REQUEST(2, "Les paramètres envoyés ne sont pas correct"),
	
	// 100 - Personnel
	INVALID_PERSONNEL_ROLE(100, "Le rôle est incorrect"),
	FAILED_PERSONNEL_ADD(101, "Erreur lors de l'ajout de ce personnel"),
	FAILED_PERSONNEL_UPDATE(102, "Erreur lors de la sauvegarde du personnel"),
	
	// 200 - Client
	FAILED_CLIENT_ADD(201, "Erreur lors de l'ajout de ce client"),
	FAILED_CLIENT_UPDATE(202, "Erreur lors de la sauvegarde du client"),
	FAILED_CLIENT_GET(203, "Erreur lors de la recherche du client"),
	
	// 300 - Animal
	INVALID_ANIMAL_ESPECES(300, "L'espèce est incorrect"),
	INVALID_ANIMAL_RACES(301, "La race est incorrect"),
	INVALID_ANIMAL_SEXES(302, "Le sexe est incorrect"),
	INVALID_ANIMAL_CLIENT_ID(303, "Le numéro de client est incorrect"),
	FAILED_ANIMAL_ADD(304, "Erreur lors de l'ajout de cet animal"),
	FAILED_ANIMAL_UPDATE(305, "Erreur lors de la sauvegarde de cet animal"),	
	
	// 400 - Rendez Vous
	FAILED_RDV_ADD(401, "Erreur lors de l'ajout de ce rendez-vous"),
	FAILED_RDV_DELETE(402, "Erreur lors de la suppression de ce rendez-vous"),
	;
	
	
	
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
