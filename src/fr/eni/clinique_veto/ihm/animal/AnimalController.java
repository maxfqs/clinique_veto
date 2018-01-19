package fr.eni.clinique_veto.ihm.animal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ComboBoxModel;
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
	
	private JComboBox<String> especesCBox;
	private JComboBox<String> racesCBox;
	
	
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
			
			especesCBox = dialog.getEspeces();
			racesCBox = dialog.getRaces();
			
			initEspecesCBox();
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
		dialog.getNom().setText(a.getNomAnimal());
		dialog.getCouleur().setText(a.getCouleur());
		dialog.getTatoo().setText(a.getTatouage());
		dialog.getEspeces().setSelectedItem(a.getEspece());
		dialog.getRaces().setSelectedItem(a.getRace());
	}
	
	private void initEspecesCBox() {
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
	}

	
	
	private Animal getAnimal() {
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
		
		return null;
	}
	
	public void valid() {
		if(dialog == null) return;
		
		// Client doit retourner son animal manager
		
		destroy();
	}
	
	

}
