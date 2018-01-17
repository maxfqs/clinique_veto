package fr.eni.clinique_veto.ihm.clients;

public class AnimalController {

	private static AnimalController instance;
	
	public static AnimalController get() {
		if(instance == null) {
			instance = new AnimalController();
		}	
		return instance;
	}



	public Object addAnimal() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
