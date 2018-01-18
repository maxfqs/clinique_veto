package fr.eni.clinique_veto.ihm.animal;

import fr.eni.clinique_veto.bo.Animal;
import fr.eni.clinique_veto.ihm.IHMException;

public class AnimalController {
	private static AnimalController instance;
	private AnimalDialog dialog;
	private Animal selectedAnimal;
	
	
	
	
	public static AnimalController get() {
		if(instance == null) {
			instance = new AnimalController();
		}
		return instance;
	}
	
	public void create(Animal a) throws IHMException {
		selectedAnimal = a;
		
		if(selectedAnimal == null) {
			throw new IHMException("Erreur, aucun animal selectionné");
		}
		
		if(dialog == null) {
			dialog = new AnimalDialog();
			displayAnimal();
			dialog.setVisible(true);
		}
	}

	public void destroy() {
		if(dialog != null) {
			dialog.setVisible(false);
			dialog.dispose();
			dialog = null;
			selectedAnimal = null;
		}
	}
	
	private void displayAnimal() {
		dialog.getNom().setText(selectedAnimal.getNomAnimal());
		dialog.getCouleur().setText(selectedAnimal.getCouleur());
		dialog.getTatoo().setText(selectedAnimal.getTatouage());
	}
	
	public void valid() {
		if(selectedAnimal == null || dialog == null) return;
		
		destroy();
	}

}
