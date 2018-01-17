package fr.eni.clinique_veto.ihm.clients;

public class AjoutClientController {

	private static AjoutClientController instance;
	
	public static AjoutClientController get() {
		if(instance == null) {
			instance = new AjoutClientController();
		}
		
		return instance;
	}

	public void ajouterClient() {
		System.out.println("ajout d un client");
	}
	
}
