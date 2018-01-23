package fr.eni.clinique_veto.ihm;

import fr.eni.clinique_veto.bll.ConnexionManager;
import fr.eni.clinique_veto.bo.Personnel;
import fr.eni.clinique_veto.bo.PersonnelRole;
import fr.eni.clinique_veto.ihm.personnel.PersonnelController;

public class HomeController {
	public static HomeController instance;
	
	private Personnel user;
	private HomeFrame hframe;
	private MenuController selectedMenu;
	private MenuController personnel;
	
	
	private HomeController() {
		selectedMenu = null;
		personnel = PersonnelController.get();
	}
	
	public static HomeController get() {
		if(instance == null) {
			instance = new HomeController();
		}
		
		return instance;
	}
	
	public void createFrame(Personnel user) {
		hframe = new HomeFrame();
		String role = user.getRole();
		
		
		if(role.equals(PersonnelRole.ADMIN.getCode())) {
			hframe.addPersonnelMenu();
		} else if (role.equals(PersonnelRole.VETERINAIRE.getCode())) {
			// agenda
		} else { 
			hframe.addClientMenu();
		}
		
		hframe.setVisible(true);
	}
	
	public void closeFrame() {
		hframe.setVisible(false);
		hframe.dispose();
	}
	
	public void selectMenu(MenuController mc) {
		if(selectedMenu == null) {
			mc.show();
			selectedMenu = mc;
			return;
		}
		
		if(selectedMenu.equals(mc)) {
			mc.hide();
			selectedMenu = null;
		} else {
			selectedMenu.hide();
			mc.show();
			selectedMenu = mc;
		}
	}
	
	public void closeMenu(MenuController mc) {
		if(selectedMenu.equals(mc)) {
			mc.hide();
			selectedMenu = null;
		}
	}
	
	public Personnel getUser() {
		return user;
	}
	
	public void closeApp() {
		System.exit(1);
	}
	
	public void logOut() {
		ConnexionManager.get().logOutUser();
	}
}
