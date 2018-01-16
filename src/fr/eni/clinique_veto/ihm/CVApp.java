package fr.eni.clinique_veto.ihm;

import fr.eni.clinique_veto.bll.ConnexionObserver;
import fr.eni.clinique_veto.bll.LoginMger;
import fr.eni.clinique_veto.bo.Personnel;

/** Clinique Veto App
 * 	Class principale du ihm
 *  */
public class CVApp implements ConnexionObserver {
	public static final String APP_TITLE = "Ani'Forme";
	private LoginController lc;
	
	public CVApp() {
		LoginMger.get().ajouterObservateur(this);
		
		lc = LoginController.get();		
		lc.createFrame();
		
//		HomeController.get().createFrame();
	}
	
	@Override
	public void connexionNotification(Personnel p) {
		lc.closeFrame();
		HomeController.get().createFrame();
	}
	
	
	public static void main(String[] args) {
		new CVApp();
	}


}
