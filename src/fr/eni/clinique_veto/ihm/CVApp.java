package fr.eni.clinique_veto.ihm;

/** Clinique Veto App
 * 	Class principale du ihm
 *  */
public class CVApp {
	
	private LoginController lc;
	
	public CVApp() {
		lc = LoginController.get();		
		lc.createFrame();
	}
	
	public static void main(String[] args) {
		new CVApp();
	}
}
