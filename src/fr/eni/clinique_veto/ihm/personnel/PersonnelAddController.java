package fr.eni.clinique_veto.ihm.personnel;

import fr.eni.clinique_veto.ihm.DialogController;

public class PersonnelAddController implements DialogController {
	public static PersonnelAddController instance;
	
	private PersonnelAddDialog paf;
	
	private PersonnelAddController() {
		
	}
	
	public static PersonnelAddController get() {
		if(instance == null) {
			instance = new PersonnelAddController();
		}
		
		return instance;
	}
	
	public void create() {
		if(paf == null) {
			 paf = new PersonnelAddDialog();
			 paf.setVisible(true);
		}
	}
	
	public void destroy() {
		if(paf != null) {
			paf.setVisible(false);
			paf.dispose();
			paf = null;
		}
	}
}
