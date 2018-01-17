package fr.eni.clinique_veto.ihm.personnel;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import fr.eni.clinique_veto.bo.Personnel;
import fr.eni.clinique_veto.ihm.HomeController;
import fr.eni.clinique_veto.ihm.MenuController;

public class PersonnelController implements MenuController {
	public static PersonnelController instance;
	private PersonnelFrame pf;
	private PersonnelAddController pac;
	private PersonnelResetController prc;
	
	private PersonnelController() {
		// Temp
		List<Personnel> ps = new ArrayList<Personnel>();
		ps.add(new Personnel(1, "bob", "mdp", "VET", false));
		ps.add(new Personnel(2, "brian", "mdp", "VET", false));
		ps.add(new Personnel(3, "jack", "mdp", "VET", false));
		
		pf = new PersonnelFrame(ps);
		pac = PersonnelAddController.get();
		prc = PersonnelResetController.get();
		
		pf.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				HomeController.get().closeMenu(instance);
			}
		});
	}
	
	public static PersonnelController get() {
		if(instance == null) {
			instance = new PersonnelController();
		}
		
		return instance;
	}
	
	public void show() {
		pf.setVisible(true);
	}
	
	public void hide() {
		pf.setVisible(false);
	}
	
	public void openAddPersonnel() {
		pac.create();
	}
	
	public void openResetPersonnel() {
		prc.create();
	}
	
	public void addPersonnel() {
		
	}
	
	public void deletePersonnel() {
		
	}
	
	public void resetPersonnel() {
		
	}
}
