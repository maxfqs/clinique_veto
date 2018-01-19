package fr.eni.clinique_veto.ihm.animal;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import fr.eni.clinique_veto.bll.EspecesManager;
import fr.eni.clinique_veto.bo.Animal;
import fr.eni.clinique_veto.bo.client.Client;
import fr.eni.clinique_veto.ihm.IHMException;

public class AnimalController {
	private static AnimalController instance;
	private Animal selectedAnimal;
	private AnimalDialog dialog;
	
	
	public static AnimalController get() {
		if(instance == null) {
			instance = new AnimalController();
		}
		return instance;
	}
	
	public void create(Client client, Animal animal) throws IHMException {
		if(animal == null) {
			throw new IHMException("Erreur à la création, animal est null");
		}

		selectedAnimal = animal;
		
		if(dialog == null) {
			dialog = new AnimalDialog();
			
			initCbox();
			displayAnimal(animal);

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
	
	private void displayAnimal(Animal a) {
		dialog.getClient().setText("Pas encore implémenter");
		dialog.getNom().setText(a.getNomAnimal());
		dialog.getCouleur().setText(a.getCouleur());
		dialog.getTatoo().setText(a.getTatouage());
		dialog.getEspeces().setSelectedItem(a.getEspece());
		dialog.getRaces().setSelectedItem(a.getRace());
		dialog.getSexes().setSelectedItem(Character.toString(a.getSexe()));
	}
	
	private void initCbox() {
		JComboBox<String> especesCBox = dialog.getEspeces();
		JComboBox<String> racesCBox = dialog.getRaces();
		
		String[] eArray = EspecesManager.getEspeces();
		DefaultComboBoxModel<String> eModel = new DefaultComboBoxModel<String>(eArray);
		especesCBox.setModel(eModel);
		
		// Maj de cbox races quand especes change
		especesCBox.addItemListener(new ItemListener() {			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() != ItemEvent.SELECTED) return;
				
				String str = (String) especesCBox.getSelectedItem();				
				String[] rArray = EspecesManager.getRacesForEspece(str);
				DefaultComboBoxModel<String> rModel = new DefaultComboBoxModel<String>(rArray);
				
				racesCBox.setModel(rModel);
			}
		});
		
		// Sexes
		String[] sx = new String[Animal.SEXE.length];
		int i = 0;
		for(char c : Animal.SEXE) sx[i++] = Character.toString(c);
		
		DefaultComboBoxModel<String> sModel = new DefaultComboBoxModel<String>(sx);
		dialog.getSexes().setModel(sModel);
	}	
	
//	private Animal getAnimal() {
//		return new Animal(
//			selectedAnimal.getCodeAnimal(), 
//			dialog.getNom().getText(),
//			selectedAnimal.getSexe(),
//			dialog.getCouleur().getText(),
//			selectedAnimal.getRace(), 
//			selectedAnimal.getEspece(),
//			selectedClient.getCodeClient(),
//			dialog.getTatoo().getText(),
//			selectedAnimal.getAntecedents(),
//			false);
//		
//		return null;
//	}
	
	public void valid() {
		if(dialog == null) return;
		
		// Client doit retourner son animal manager
		
		destroy();
	}
	
	

}
