package fr.eni.clinique_veto.ihm;

import fr.eni.clinique_veto.bll.ConnexionObserver;
import fr.eni.clinique_veto.bll.ConnexionManager;
import fr.eni.clinique_veto.bo.Personnel;

/** Clinique Veto App
 * 	Class principale du ihm
 *  */
public class CVApp implements ConnexionObserver {
	public static final String APP_TITLE = "Ani'Forme";
	
	private LoginController loginController;
	private HomeController homeController;
	
	public CVApp() {
		loginController = LoginController.get();
		homeController = HomeController.get();		
		
		ConnexionManager.get().ajouterObservateur(this);
		
		loginController.createFrame();
	}
	
	public static void main(String[] args) {
		new CVApp();
	}
	
	@Override
	public void onUserLogIn(Personnel p) {
		LoginController.get().closeFrame();
		HomeController.get().createFrame();
	}
	
	@Override
	public void onUserLogOut() {
		HomeController.get().closeFrame();
		LoginController.get().createFrame();
	}
}
