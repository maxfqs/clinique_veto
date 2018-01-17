package fr.eni.clinique_veto.ihm;

import fr.eni.clinique_veto.bll.BLLException;
import fr.eni.clinique_veto.bll.ConnexionManager;

/** Singleton */
public class LoginController {
	private static LoginController instance;
	private LoginFrame lframe;
	
	private LoginController() {

	}
	
	public static LoginController get() {
		if(instance == null) {
			instance = new LoginController();
		}
		
		return instance;
	}
		
	public void createFrame() {
		lframe = new LoginFrame();
		lframe.setVisible(true);
	}
	
	public void closeFrame() {
		lframe.setVisible(false);
		lframe.dispose();
	}
	
	public void logUser() {
		String name = lframe.getUsernameField().getText().trim();
		String password = lframe.getPasswordField().getText().trim();
		
		try {
			ConnexionManager.get().logUser(name, password);
		} catch (BLLException e) {
			e.printStackTrace();
		}
	}
}
