package fr.eni.clinique_veto.ihm.personnel;

import fr.eni.clinique_veto.bll.BLLException;
import fr.eni.clinique_veto.bll.PersonnelManager;
import fr.eni.clinique_veto.bo.Personnel;
import fr.eni.clinique_veto.bo.PersonnelRole;
import fr.eni.clinique_veto.ihm.DialogController;
import fr.eni.clinique_veto.ihm.ErrorDialog;

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
	
	public void valider() {
		Personnel p = new Personnel();
		p.setNom(paf.getNameField().getText());
		p.setMdp(paf.getPasswordField().getText());
		p.setRole(paf.getSelectedRole().getCode());
		
		try {
			PersonnelManager.get().addPersonnel(p);
			destroy();
		} catch (BLLException e) {
			ErrorDialog.showError(e.getError(), paf);
		}		
	}
}
