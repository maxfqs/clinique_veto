package fr.eni.clinique_veto.ihm.clients;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import fr.eni.clinique_veto.bll.BLLException;
import fr.eni.clinique_veto.bll.ClientManager;
import fr.eni.clinique_veto.bll.EspecesManager;
import fr.eni.clinique_veto.bo.Animal;
import fr.eni.clinique_veto.ihm.ErrorDialog;

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
	
	public void create() {
		dialog = new AnimalDialog(true);
		initCbox();
		initDisplay(null);
		dialog.setVisible(true);
	}
	
	public void create(Animal a) {
		selectedAnimal = a;
		dialog = new AnimalDialog(false);
		initCbox();
		initDisplay(a);
		dialog.setVisible(true);
	}


	public void destroy() {
		if(dialog != null) {
			dialog.setVisible(false);
			dialog.dispose();
			dialog = null;
			selectedAnimal = null;
		}
	}
	
	private void initDisplay(Animal a) {
		dialog.getClient().setText(
				ClientManager.get().getDisplayedClient().getNomClient()
		);
		
		dialog.getSexes().setSelectedIndex(1);
		dialog.getEspeces().setSelectedIndex(1);
		dialog.getRaces().setSelectedIndex(1);
		
		if(a != null) {
			dialog.getNom().setText(a.getNomAnimal());
			dialog.getCouleur().setText(a.getCouleur());
			dialog.getTatoo().setText(a.getTatouage());
			dialog.getEspeces().setSelectedItem(a.getEspece());
			dialog.getRaces().setSelectedItem(a.getRace());
			dialog.getSexes().setSelectedItem(Character.toString(a.getSexe()));
		}
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
	
	private Animal getAnimal() {
		Animal a = new Animal();
		
		a.setCodeClient(
			ClientManager.get().getDisplayedClient().getCodeClient()
		);
		
		a.setNomAnimal(dialog.getNom().getText());
		a.setCouleur(dialog.getCouleur().getText());
		a.setTatouage(dialog.getTatoo().getText());
		a.setEspece((String) dialog.getEspeces().getSelectedItem());
		a.setRace((String) dialog.getRaces().getSelectedItem());
		a.setSexe(
			((String) dialog.getSexes().getSelectedItem()).charAt(0)
		);
		
		if(selectedAnimal != null) {
			a.setCodeAnimal(selectedAnimal.getCodeAnimal());
			a.setAntecedents(selectedAnimal.getAntecedents());
		}
		
		return a;
	}
	
	
	public void addAnimal() {
		Animal a = getAnimal();
		try {
			ClientManager.get().getAnimalManager().addAnimal(a);
		} catch (BLLException e) {
			ErrorDialog.showError(e.getMessage());
		}
		destroy();
	}
	
	public void updateAnimal() {
		Animal a = getAnimal();
		try {
			ClientManager.get().getAnimalManager().update(a);
		} catch (BLLException e) {
			ErrorDialog.showError(e.getMessage());
		}
		destroy();
	}
	
	

}
