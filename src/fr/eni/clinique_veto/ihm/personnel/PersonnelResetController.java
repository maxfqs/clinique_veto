package fr.eni.clinique_veto.ihm.personnel;

import fr.eni.clinique_veto.bll.BLLException;
import fr.eni.clinique_veto.bll.PersonnelManager;
import fr.eni.clinique_veto.bo.Personnel;
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
	
	public void valider() {
		String password = prd.getPasswordField().getText();
		Personnel p = PersonnelController.get().getSelectedPersonnel();
		p.setMdp(password);
		
		try {
			PersonnelManager.get().updatePassword(p);
		} catch (BLLException e) {
			e.printStackTrace();
		}
		
		destroy();
	}
	
	
}
