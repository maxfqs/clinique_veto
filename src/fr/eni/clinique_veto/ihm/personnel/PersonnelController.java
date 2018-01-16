package fr.eni.clinique_veto.ihm.personnel;

import java.util.ArrayList;
import java.util.List;

import fr.eni.clinique_veto.bo.Personnel;

public class PersonnelController {
	public static PersonnelController instance;
	private PersonnelFrame pf;
	
	
	private PersonnelController() {
		
	}
	
	public static PersonnelController get() {
		if(instance == null) {
			instance = new PersonnelController();
		}
		
		return instance;
	}
	
	public void createFrame() {
		// Temp
		List<Personnel> ps = new ArrayList<Personnel>();
		ps.add(new Personnel(1, "bob", "mdp", "VET", false));
		ps.add(new Personnel(2, "brian", "mdp", "VET", false));
		ps.add(new Personnel(3, "jack", "mdp", "VET", false));
		
		pf = new PersonnelFrame(ps);
		pf.setVisible(true);
	}
	
	public void show() {
		if(pf == null) {
			createFrame();
		} else {
			pf.setVisible(true);
		}
	}
	
	public void hide() {
		if(pf != null) pf.setVisible(false);
	}
	
	
	
	public void addPersonnel() {
		
	}
	
	public void deletePersonnel() {
		
	}
	
	public void resetPersonnel() {
		
	}
}
