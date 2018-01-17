package fr.eni.clinique_veto.ihm.personnel;

import javax.swing.JDialog;

import fr.eni.clinique_veto.bll.BLLException;
import fr.eni.clinique_veto.bll.PersonnelManager;
import fr.eni.clinique_veto.bo.Personnel;
import fr.eni.clinique_veto.ihm.DialogController;

public class PersonnelDeleteController implements DialogController {
	private static PersonnelDeleteController instance;
	
	private JDialog dialog;
	
	
	public static PersonnelDeleteController get() {
		if(instance == null) {
			instance = new PersonnelDeleteController();
		}
		
		return instance;
	}
	
	@Override
	public void create() {
		if(dialog == null) {
			dialog = new PersonnelDeleteDialog();
			dialog.setVisible(true);
		}		
	}

	@Override
	public void destroy() {
		if(dialog != null) {
			dialog.setVisible(false);
			dialog.dispose();
			dialog = null;
		}		
	}
	
	public void valider() {
		Personnel selected = PersonnelController.get().getSelectedPersonnel();
		
		try {
			PersonnelManager.get().archiver(selected);
		} catch (BLLException e) {
			e.printStackTrace();
		}
		
		destroy();
	}
}
