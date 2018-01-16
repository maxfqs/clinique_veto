package fr.eni.clinique_veto.ihm.personnel;

import java.util.ArrayList;
import java.util.List;

import fr.eni.clinique_veto.bo.Personnel;
import fr.eni.clinique_veto.ihm.MenuController;

public class PersonnelController implements MenuController {
	public static PersonnelController instance;
	private PersonnelFrame pf;
	
	
	private PersonnelController() {
		// Temp
		List<Personnel> ps = new ArrayList<Personnel>();
		ps.add(new Personnel(1, "bob", "mdp", "VET", false));
		ps.add(new Personnel(2, "brian", "mdp", "VET", false));
		ps.add(new Personnel(3, "jack", "mdp", "VET", false));
		
		pf = new PersonnelFrame(ps);
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
	
	
	
	public void addPersonnel() {
		
	}
	
	public void deletePersonnel() {
		
	}
	
	public void resetPersonnel() {
		
	}
}
