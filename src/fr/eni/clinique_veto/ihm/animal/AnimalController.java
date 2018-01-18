package fr.eni.clinique_veto.ihm.animal;

import fr.eni.clinique_veto.bo.Animal;
import fr.eni.clinique_veto.bo.client.Client;
import fr.eni.clinique_veto.ihm.IHMException;

public class AnimalController {
	private static AnimalController instance;
	private Client selectedClient;
	private Animal selectedAnimal;
	private AnimalDialog dialog;
	
	
	
	public static AnimalController get() {
		if(instance == null) {
			instance = new AnimalController();
		}
		return instance;
	}
	
	public void create(Client client, Animal animal) throws IHMException {
		if(client == null || animal == null) {
			throw new IHMException("Erreur à la création, params sont null");
		}
		
		selectedClient = client;
		selectedAnimal = animal;
		
		if(dialog == null) {
			dialog = new AnimalDialog();
			displayAnimal(animal);
			dialog.setVisible(true);
		}
	}

	public void destroy() {
		if(dialog != null) {
			dialog.setVisible(false);
			dialog.dispose();
			dialog = null;
			
			selectedClient = null;
			selectedAnimal = null;
		}
	}
	
	private void displayAnimal(Animal a) {
		dialog.getNom().setText(a.getNomAnimal());
		dialog.getCouleur().setText(a.getCouleur());
		dialog.getTatoo().setText(a.getTatouage());
	}
	
	private Animal getAnimal() {
		return new Animal(
			selectedAnimal.getCodeAnimal(), 
			dialog.getNom().getText(),
			selectedAnimal.getSexe(),
			dialog.getCouleur().getText(),
			selectedAnimal.getRace(), 
			selectedAnimal.getEspece(),
			selectedClient.getCodeClient(),
			dialog.getTatoo().getText(),
			selectedAnimal.getAntecedents(),
			false);
	}
	
	public void valid() {
		if(dialog == null) return;
		
		// Client doit retourner son animal manager
		
		destroy();
	}

}
