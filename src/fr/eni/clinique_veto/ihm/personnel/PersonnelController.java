package fr.eni.clinique_veto.ihm.personnel;

import javax.swing.JPanel;

import fr.eni.clinique_veto.bll.ManagerListObserver;
import fr.eni.clinique_veto.bll.PersonnelManager;
import fr.eni.clinique_veto.bo.Personnel;
import fr.eni.clinique_veto.ihm.MenuController;

public class PersonnelController implements MenuController, ManagerListObserver {
	public static PersonnelController instance;
	private PersonnelFrame personnelFrame;
	private PersonnelAddController pAddController;
	private PersonnelResetController pResetController;
	private PersonnelDeleteController pDeleteController;
	private Personnel selectedPersonnel;
	
	private PersonnelController() {
		personnelFrame = new PersonnelFrame();
		pAddController = PersonnelAddController.get();
		pResetController = PersonnelResetController.get();
		pDeleteController = PersonnelDeleteController.get();
		
		
		PersonnelManager.get().registerObserver(this);
		hide();
	}
	
	public static PersonnelController get() {
		if(instance == null) {
			instance = new PersonnelController();
		}
		
		return instance;
	}
	
	public JPanel getPanel() {
		return personnelFrame;
	}
	
	public void show() {
		personnelFrame.setVisible(true);
	}
	
	public void hide() {
		personnelFrame.setVisible(false);
	}
	
	public void setSelectedPersonnel(Personnel p) {
		selectedPersonnel = p;
		personnelFrame.enableActionButtons(true);
	}
	
	public Personnel getSelectedPersonnel() {
		return selectedPersonnel;
	}
	
	public void openAddPersonnel() {
		pAddController.create();
	}
	
	public void openResetPersonnel() {
		pResetController.create();
	}
	
	public void openDeletePersonnel() {
		pDeleteController.create();
	}

	
	@Override
	public void onListUpdated() {
		selectedPersonnel = null;
		personnelFrame.enableActionButtons(false);
		personnelFrame.getPersonnelTable().getModel().fireTableDataChanged();		
	}

}
