package fr.eni.clinique_veto.ihm.clients;

public class RechercheClientController {

	private static RechercheClientController instance;
	
	public static RechercheClientController get() {
		if(instance == null) {
			instance = new RechercheClientController();
		}
		
		return instance;
	}

	public void chercherClient(String nomClient) {
		System.out.println("recherche du client -> "+ nomClient);
	}
	
}
