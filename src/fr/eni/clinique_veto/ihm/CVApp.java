package fr.eni.clinique_veto.ihm;

import fr.eni.clinique_veto.bll.ConnexionObserver;
import fr.eni.clinique_veto.bll.LoginMger;
import fr.eni.clinique_veto.bo.Personnel;

/** Clinique Veto App
 * 	Class principale du ihm
 *  */
public class CVApp implements ConnexionObserver {
	public static final String APP_TITLE = "Ani'Forme";

	
	public CVApp() {
		LoginMger.get().ajouterObservateur(this);
		LoginController.get().createFrame();		
	}
	
	@Override
	public void connexionNotification(Personnel p) {
		LoginController.get().closeFrame();
		HomeController.get().createFrame();
	}
	
	
	public static void main(String[] args) {
		new CVApp();
	}


}
