package fr.eni.clinique_veto.ihm;

/** Clinique Veto App
 * 	Class principale du ihm
 *  */
public class CVApp {
	public static final String APP_TITLE = "Ani'Forme";
	private LoginController lc;
	
	public CVApp() {
//		lc = LoginController.get();		
//		lc.createFrame();
		
		HomeController.get().createFrame();
	}
	
	public static void main(String[] args) {
		new CVApp();
	}
}
