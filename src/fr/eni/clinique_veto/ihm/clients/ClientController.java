package fr.eni.clinique_veto.ihm.clients;



public class ClientController {

	private static ClientController instance;
	
	public static ClientController get() {
		if(instance == null) {
			instance = new ClientController();
		}
		
		return instance;
	}
	
	public void rechercheClient(String string) {	
		RechercheFrame rframe = new RechercheFrame();
		rframe.setVisible(true);
		System.out.println("recherche client-> "+ string);
	}

	public void ajouterClient() {
		AjoutClientFrame aframe  =new AjoutClientFrame();
		aframe.setVisible(true);
		System.out.println("ajout client");
	}

	public void updateClient() {
		System.out.println("update client");
	}

	public void annuler() {
		System.out.println("annuler");
	}

	public void supprimerClient() {
		System.out.println("supprimer");
	}

	public void ajouterAnimal() {
		AnimalFrame aniframe  =new AnimalFrame();
		aniframe.setVisible(true);
		System.out.println("ajouter animal");
	}

	public void supprimerAnimal() {
		System.out.println("supprime animal");
	}

	public void editerAnimal() {
		System.out.println("éditer animal");
	}
	
	
	
}
