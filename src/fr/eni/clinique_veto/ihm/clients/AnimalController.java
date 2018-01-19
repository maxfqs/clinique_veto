package fr.eni.clinique_veto.ihm.clients;


import fr.eni.clinique_veto.bll.AnimalManager;
import fr.eni.clinique_veto.bll.BLLException;
import fr.eni.clinique_veto.bll.ClientManager;
import fr.eni.clinique_veto.bo.Animal;

public class AnimalController {

	private static AnimalController instance;
	
	public static AnimalController get() {
		if(instance == null) {
			instance = new AnimalController();
		}	
		return instance;
	}



	public Object addAnimal(Animal a) {
		AnimalManager manager = ClientManager.get().getAnimalManager();
		try {
			manager.addAnimal(a);
			AnimalFrame.get().setVisible(false);
			AnimalFrame.get().resetFields();
		} catch (BLLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
