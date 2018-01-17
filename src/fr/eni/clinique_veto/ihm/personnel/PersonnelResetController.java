package fr.eni.clinique_veto.ihm.personnel;

import fr.eni.clinique_veto.ihm.DialogController;

public class PersonnelResetController implements DialogController {
	private static PersonnelResetController instance;
	
	private PersonnelResetDialog prd;
	
	private PersonnelResetController() {
		
	}
	
	public static PersonnelResetController get() {
		if(instance == null) {
			instance = new PersonnelResetController();
		}
		
		return instance;
	}

	@Override
	public void create() {
		if(prd == null) {
			prd = new PersonnelResetDialog();
			prd.setVisible(true);
		}		
	}

	@Override
	public void destroy() {
		if(prd != null) {
			prd.setVisible(false);
			prd.dispose();
			prd = null;
		}		
	}
	
	
}
