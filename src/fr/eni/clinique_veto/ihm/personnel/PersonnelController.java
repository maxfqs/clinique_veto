package fr.eni.clinique_veto.ihm.personnel;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import fr.eni.clinique_veto.bll.PersonnelManager;
import fr.eni.clinique_veto.bll.PersonnelObserver;
import fr.eni.clinique_veto.bo.Personnel;
import fr.eni.clinique_veto.ihm.HomeController;
import fr.eni.clinique_veto.ihm.MenuController;

public class PersonnelController implements MenuController, PersonnelObserver {
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
		
		personnelFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				HomeController.get().closeMenu(instance);
			}
		});
		
		PersonnelManager.get().registerObserver(this);
	}
	
	public static PersonnelController get() {
		if(instance == null) {
			instance = new PersonnelController();
		}
		
		return instance;
	}
	
	public void show() {
		personnelFrame.setVisible(true);
	}
	
	public void hide() {
		personnelFrame.setVisible(false);
	}
	
	public void setSelectedPersonnel(Personnel p) {
		selectedPersonnel = p;
		personnelFrame.enableActionButtons();
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
	
	public void addPersonnel() {
		
	}
	
	public void deletePersonnel() {
		
	}
	
	public void resetPersonnel() {
		
	}

	@Override
	public void onNewPersonnelAdded(Personnel p) {
		personnelFrame.getPersonnelTable().getModel().fireTableDataChanged();
	}
}
